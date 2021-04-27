package tz.go.moh.him.emr.mediator.nhcr.mock;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.openhim.mediator.engine.messages.MediatorHTTPRequest;
import org.openhim.mediator.engine.testing.MockHTTPConnector;
import tz.go.moh.him.emr.mediator.nhcr.domain.EmrResponse;
import tz.go.moh.him.emr.mediator.nhcr.orchestrator.DefaultOrchestratorTest;
import tz.go.moh.him.mediator.core.serialization.JsonSerializer;

import java.io.IOException;
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
        InputStream stream = MockEmr.class.getClassLoader().getResourceAsStream("emr_response_success.json");

        Assert.assertNotNull(stream);

        try {
            return IOUtils.toString(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        InputStream stream = DefaultOrchestratorTest.class.getClassLoader().getResourceAsStream("emr_response_success.json");

        Assert.assertNotNull(stream);

        JsonSerializer serializer = new JsonSerializer();
        try {
            EmrResponse response = serializer.deserialize(IOUtils.toString(stream), EmrResponse.class);

            Assert.assertEquals("Success", response.getStatus());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

