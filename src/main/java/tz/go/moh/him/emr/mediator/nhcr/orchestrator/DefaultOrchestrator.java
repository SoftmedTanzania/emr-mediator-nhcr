package tz.go.moh.him.emr.mediator.nhcr.orchestrator;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.ACK;
import org.openhim.mediator.engine.MediatorConfig;
import org.openhim.mediator.engine.connectors.MLLPConnector;
import org.openhim.mediator.engine.messages.FinishRequest;
import org.openhim.mediator.engine.messages.MediatorSocketRequest;
import tz.go.moh.him.emr.mediator.nhcr.domain.EmrMessage;
import tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.message.ZXT_A39;
import tz.go.moh.him.emr.mediator.nhcr.utils.HL7v2MessageBuilderUtils;
import tz.go.moh.him.emr.mediator.nhcr.utils.MllpUtils;

import java.io.IOException;
import java.util.UUID;

/**
 * Represents a default orchestrator.
 */
public class DefaultOrchestrator extends MLLPConnector {

    /**
     * The mediator configuration.
     */
    private final MediatorConfig config;

    /**
     * Initializes a new instance of the {@link DefaultOrchestrator} class.
     *
     * @param config The configuration.
     */
    public DefaultOrchestrator(MediatorConfig config) {
        this.config = config;
    }

    /**
     * Handles the received message.
     *
     * @param msg The received message.
     */
    @Override
    public void onReceive(Object msg) throws HL7Exception, IOException {
        if (msg instanceof MediatorSocketRequest) {
            FinishRequest finishRequest = null;
            ACK ack = null;
            try {

                // parse message
                ZXT_A39 a40 = HL7v2MessageBuilderUtils.parseZxtA39(((MediatorSocketRequest) msg).getBody());

                // build the EMR message
                EmrMessage emrMessage = HL7v2MessageBuilderUtils.convertToEmrMessage(a40);

                boolean success = false;


//                host = scheme + "://" + host + ":" + port + path;
//
//                MediatorHTTPRequest request = new MediatorHTTPRequest(workingRequest.getRequestHandler(), getSelf(), host, "POST",
//                        host, mapper.writeValueAsString(hfrRequest), headers, parameters);

//                ActorSelection httpConnector = getContext().actorSelection(config.userPathFor("http-connector"));
//                httpConnector.tell(request, getSelf());


                // create the ACK to send back to the NHCR
                ack = HL7v2MessageBuilderUtils.createAck(a40.getMSH().getMessageControlID().getValue(), success);

            } catch (Exception e) {

                // in the event of an exception, we need to create a generic ACK
                // and respond to the NHCR, indicating a failure
                ack = HL7v2MessageBuilderUtils.createAck(UUID.randomUUID().toString(), false);
            } finally {
                MllpUtils.sendMessage(ack, this.config, new DefaultHapiContext(), null);

                finishRequest = new FinishRequest(ack.encode(), "", 500);
                ((MediatorSocketRequest) msg).getRequestHandler().tell(finishRequest, getSelf());
            }
        } else {
            unhandled(msg);
        }
    }
}
