package tz.go.moh.him.emr.mediator.nhcr.utils;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import org.json.JSONObject;
import org.openhim.mediator.engine.MediatorConfig;

import java.io.IOException;

/**
 * Represents an MLLP utility.
 */
public class MllpUtils {

    /**
     * Sends an HL7 message.
     *
     * @param message    The message to send.
     * @param config     The mediator configuration.
     * @param context    The HAPI context.
     * @param connection The connection.
     * @return Returns the response message.
     * @throws HL7Exception if an HL7 exception occurs
     */
    public static String sendMessage(Message message, MediatorConfig config, HapiContext context, Connection connection) throws HL7Exception {
        String host;
        int port;
        boolean useTls;

        if (config.getDynamicConfig().isEmpty()) {
            host = config.getProperty("destination.host");
            port = Integer.parseInt(config.getProperty("destination.port"));
            useTls = !config.getProperty("destination.scheme").equalsIgnoreCase("llp");
        } else {
            JSONObject connectionProperties = new JSONObject(config.getDynamicConfig()).getJSONObject("destinationConnectionProperties");

            host = connectionProperties.getString("destinationHost");
            port = connectionProperties.getInt("destinationPort");
            useTls = !connectionProperties.getString("destinationScheme").equalsIgnoreCase("llp");
        }

        if (connection == null) {
            connection = context.newClient(host, port, useTls);
        }

        String responseMessage = null;

        try {
            Message response = connection.getInitiator().sendAndReceive(message);
            responseMessage = response.encode();
        } catch (IOException | LLPException e) {
            e.printStackTrace();

            connection.close();
        }

        return responseMessage;
    }
}
