package tz.go.moh.him.emr.mediator.nhcr.domain;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import tz.go.moh.him.emr.mediator.nhcr.orchestrator.DefaultOrchestratorTest;
import tz.go.moh.him.mediator.core.serialization.JsonSerializer;

import java.io.InputStream;

/**
 * Contains tests for the {@link EmrResponse} class.
 */
public class EmrResponseTest {

    @Test
    public void testDeserializeEmrResponse() throws Exception {
        InputStream stream = DefaultOrchestratorTest.class.getClassLoader().getResourceAsStream("emr_response_success.json");

        Assert.assertNotNull(stream);

        JsonSerializer serializer = new JsonSerializer();

        EmrResponse actual = serializer.deserialize(IOUtils.toString(stream), EmrResponse.class);

        Assert.assertEquals("Success", actual.getStatus());
    }

    @Test
    public void testSerializeEmrResponse() {
        EmrResponse response = new EmrResponse();

        response.setStatus("Success");

        JsonSerializer serializer = new JsonSerializer();

        String actual = serializer.serializeToString(response);

        Assert.assertTrue(actual.contains("Success"));
    }
}
