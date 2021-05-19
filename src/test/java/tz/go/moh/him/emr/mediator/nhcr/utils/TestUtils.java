package tz.go.moh.him.emr.mediator.nhcr.utils;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * Represents a collection of testing utilities.
 */
public class TestUtils {

    /**
     * Gets a HL7 message for testing purposes.
     *
     * @return Returns the message.
     * @throws IOException if an IO exception occurs
     */
    public static String getHL7TestMessage() throws IOException {
        InputStream stream = HL7v2MessageUtilsTest.class.getClassLoader().getResourceAsStream("ZXT_A39.hl7");

        Assert.assertNotNull(stream);

        return IOUtils.toString(stream);
    }
}
