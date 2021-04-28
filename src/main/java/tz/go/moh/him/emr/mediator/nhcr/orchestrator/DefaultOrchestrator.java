package tz.go.moh.him.emr.mediator.nhcr.orchestrator;

import akka.actor.ActorSelection;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.ACK;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpHeaders;
import org.openhim.mediator.engine.MediatorConfig;
import org.openhim.mediator.engine.connectors.MLLPConnector;
import org.openhim.mediator.engine.messages.MediatorHTTPRequest;
import org.openhim.mediator.engine.messages.MediatorHTTPResponse;
import org.openhim.mediator.engine.messages.MediatorSocketRequest;
import tz.go.moh.him.emr.mediator.nhcr.domain.EmrRequest;
import tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.message.ZXT_A39;
import tz.go.moh.him.emr.mediator.nhcr.utils.HL7v2MessageBuilderUtils;
import tz.go.moh.him.emr.mediator.nhcr.utils.MllpUtils;
import tz.go.moh.him.mediator.core.serialization.JsonSerializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Represents a default orchestrator.
 */
public class DefaultOrchestrator extends MLLPConnector {

    /**
     * The logger instance.
     */
    protected final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    /**
     * The mediator configuration.
     */
    private final MediatorConfig config;

    /**
     * The working request.
     */
    private MediatorSocketRequest workingRequest;

    /**
     * Initializes a new instance of the {@link DefaultOrchestrator} class.
     *
     * @param config The configuration.
     */
    public DefaultOrchestrator(MediatorConfig config) {
        this.config = config;
    }

    /**
     * Handles the received message
     *
     * @param msg The received message.
     * @throws HL7Exception if an HL7 exception occurs
     * @throws IOException  if an IO exception occurs
     */
    @Override
    public void onReceive(Object msg) throws HL7Exception, IOException {
        if (msg instanceof MediatorSocketRequest) {

            workingRequest = (MediatorSocketRequest) msg;

            log.info("Received socket request: Correlation Id: " + workingRequest.getCorrelationId() + ", Host:" + workingRequest.getHost() + ", Port: " + workingRequest.getPort() + ", Secure: " + workingRequest.isSecure());

            handleSocketRequest(workingRequest);
        } else if (msg instanceof MediatorHTTPResponse) {

            MediatorHTTPResponse response = (MediatorHTTPResponse) msg;

            log.info("Received HTTP response: Status Code " + response.getStatusCode() + ", correlates to request: " + response.getOriginalRequest().getCorrelationId());
            this.handleHttpResponse(response);
        } else {
            unhandled(msg);
        }
    }

    /**
     * Handles a mediator socket request.
     *
     * @param request The request.
     * @throws HL7Exception if an HL7 exception occurs
     * @throws IOException  if an IO exception occurs
     */
    private void handleSocketRequest(MediatorSocketRequest request) throws HL7Exception, IOException {
        try {

            Map<String, String> headers = new HashMap<>();

            headers.put(HttpHeaders.CONTENT_TYPE, "application/json");

            List<Pair<String, String>> parameters = new ArrayList<>();

            // parse message
            ZXT_A39 a40 = HL7v2MessageBuilderUtils.parseZxtA39(request.getBody());

            JsonSerializer serializer = new JsonSerializer();

            // build the EMR message
            EmrRequest emrRequest = HL7v2MessageBuilderUtils.convertToEmrMessage(a40);

            String url = a40.getSFT(0).getSft1_SoftwareVendorOrganization().getXon1_OrganizationName().getValue();
            String username = a40.getSFT(0).getSft3_SoftwareProductName().getValue();
            String password = a40.getSFT(0).getSft5_SoftwareProductInformation().getValue();

            byte[] encodedAuth = Base64.encodeBase64((username + ":" + password).getBytes(StandardCharsets.ISO_8859_1));
            String authHeader = "Basic " + new String(encodedAuth);

            headers.put(HttpHeaders.AUTHORIZATION, authHeader);
            headers.put("X-Request-Id", a40.getMSH().getMessageControlID().getValue());

//            host = scheme + "://" + host + ":" + port + path;

            MediatorHTTPRequest requestToEmr = new MediatorHTTPRequest(workingRequest.getRequestHandler(), getSelf(), url, "POST", url, serializer.serializeToString(emrRequest), headers, parameters);

            ActorSelection httpConnector = getContext().actorSelection(config.userPathFor("http-connector"));
            httpConnector.tell(requestToEmr, getSelf());
        } catch (Exception e) {

            // in the event of an exception, we need to create a generic ACK
            // and respond to the NHCR, indicating a failure
            ACK ack = HL7v2MessageBuilderUtils.createAck(UUID.randomUUID().toString(), false);

            MllpUtils.sendMessage(ack, config, new DefaultHapiContext(), null);

            log.error("Unable to process incoming request: %s", ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * Handles a mediator HTTP response.
     *
     * @param response The response
     * @throws HL7Exception if an HL7 exception occurs
     * @throws IOException  if an IO exception occurs
     */
    private void handleHttpResponse(MediatorHTTPResponse response) throws HL7Exception, IOException {
        ACK ack = null;
        boolean success = false;

        try {
            // if the response status code is in the success status range
            // we can consider the response from the destination system a success
            success = response.getStatusCode() >= 200 && response.getStatusCode() <= 299;

            String originalRequestId = response.getHeaders().get("X-Request-Id");

            ack = HL7v2MessageBuilderUtils.createAck(originalRequestId, success);

        } catch (Exception e) {
            // in the event of an exception, we need to create a generic ACK
            // and respond to the NHCR, indicating a failure
            ack = HL7v2MessageBuilderUtils.createAck(UUID.randomUUID().toString(), success);
            log.error("Unable to process incoming response: " + ExceptionUtils.getStackTrace(e));
        } finally {
            MllpUtils.sendMessage(ack, config, new DefaultHapiContext(), null);
            workingRequest.getRequestHandler().tell(response.toFinishRequest(), getSelf());
        }
    }
}
