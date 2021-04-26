package tz.go.moh.him.emr.mediator.nhcr.utils;

import ca.uhn.hl7v2.HL7Exception;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import tz.go.moh.him.emr.mediator.nhcr.domain.EmrMessage;
import tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.message.ZXT_A39;
import tz.go.moh.him.mediator.core.serialization.JsonSerializer;

import java.io.IOException;
import java.io.InputStream;

public class HL7v2MessageUtilsTest {

    @Test
    public void testParseZxtA39() throws IOException, HL7Exception {
        InputStream stream = HL7v2MessageUtilsTest.class.getClassLoader().getResourceAsStream("ZXT_A40.hl7");

        Assert.assertNotNull(stream);

        String message = IOUtils.toString(stream);

        ZXT_A39 a39 = HL7v2MessageBuilderUtils.parseZxtA39(message);

        Assert.assertEquals("https://pos1.com", a39.getSFT().getSft1_SoftwareVendorOrganization().getOrganizationName().getValue());
        Assert.assertEquals("username", a39.getSFT().getSft3_SoftwareProductName().getValue());
        Assert.assertEquals("password", a39.getSFT().getSft5_SoftwareProductInformation().getValue());

        Assert.assertNotNull(a39);

        JsonSerializer serializer = new JsonSerializer();

        EmrMessage actual = HL7v2MessageBuilderUtils.convertToEmrMessage(a39);

        System.out.println(serializer.serializeToString(actual));
    }

}
