package tz.go.moh.him.emr.mediator.nhcr.mock;

import org.junit.Assert;
import org.openhim.mediator.engine.messages.MediatorHTTPRequest;
import org.openhim.mediator.engine.testing.MockHTTPConnector;
import tz.go.moh.him.emr.mediator.nhcr.DefaultOrchestratorTest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a mock destination.
 */
public class MockEmr extends MockHTTPConnector {

    /**
     * Initializes a new instance of the {@link MockEmr} class.
     */
    public MockEmr() {
    }

    /**
     * Gets the response.
     *
     * @return Returns the response.
     */
    @Override
    public String getResponse() {
        return null;
    }

    /**
     * Gets the status code.
     *
     * @return Returns the status code.
     */
    @Override
    public Integer getStatus() {
        return 200;
    }

    /**
     * Gets the HTTP headers.
     *
     * @return Returns the HTTP headers.
     */
    @Override
    public Map<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<>();

        headers.put("X-Request-Id", "8d21c445-24e0-497c-9f96-da2a0a5a65b7");

        return headers;
    }

    /**
     * Handles the message.
     *
     * @param msg The message.
     */
    @Override
    public void executeOnReceive(MediatorHTTPRequest msg) {

        InputStream stream = DefaultOrchestratorTest.class.getClassLoader().getResourceAsStream("success_response.json");

        Assert.assertNotNull(stream);
    }
}
