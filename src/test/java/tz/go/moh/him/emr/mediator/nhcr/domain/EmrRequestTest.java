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
public class EmrRequestTest {

    @Test
    public void testDeserializeEmrRequest() throws Exception {
        InputStream stream = DefaultOrchestratorTest.class.getClassLoader().getResourceAsStream("emr_request.json");

        Assert.assertNotNull(stream);

        JsonSerializer serializer = new JsonSerializer();

        EmrRequest actual = serializer.deserialize(IOUtils.toString(stream), EmrRequest.class);

//        Assert.assertEquals("Success", actual.getStatus());
    }

    @Test
    public void testSerializeEmrRequest() {
        EmrRequest request = new EmrRequest();

//        request.setStatus("Success");

        JsonSerializer serializer = new JsonSerializer();

        String actual = serializer.serializeToString(request);

//        Assert.assertTrue(actual.contains("Success"));
    }
}
