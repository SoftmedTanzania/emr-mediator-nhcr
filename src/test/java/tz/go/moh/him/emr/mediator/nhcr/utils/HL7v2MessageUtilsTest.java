package tz.go.moh.him.emr.mediator.nhcr.utils;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v25.message.ADT_A39;
import ca.uhn.hl7v2.parser.CustomModelClassFactory;
import ca.uhn.hl7v2.parser.PipeParser;
import org.junit.Assert;
import org.junit.Test;
import tz.go.moh.him.emr.mediator.nhcr.domain.EmrRequest;
import tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.message.ZXT_A39;
import tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.segment.ZXT;
import tz.go.moh.him.mediator.core.exceptions.ArgumentNullException;
import tz.go.moh.him.mediator.core.serialization.JsonSerializer;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * Contains tests for the {@link HL7v2MessageBuilderUtils} class.
 */
public class HL7v2MessageUtilsTest {

    /**
     * Tests the conversion of an HL7 message to an EMR message.
     *
     * @throws IOException  if an IO exception occurs
     * @throws HL7Exception if an HL7 exception occurs
     */
    @Test
    public void testConvertToEmrMessage() throws IOException, HL7Exception {
        String message = TestUtils.getHL7TestMessage().replaceAll("\\n", "\r");

        ZXT_A39 a39 = HL7v2MessageBuilderUtils.parseZxtA39(message);

        Assert.assertEquals("NHCR", a39.getMSH().getMsh3_SendingApplication().getNamespaceID().getValue());
        Assert.assertEquals("NHCR", a39.getMSH().getMsh4_SendingFacility().getNamespaceID().getValue());
        Assert.assertEquals("CTC", a39.getMSH().getMsh5_ReceivingApplication().getNamespaceID().getValue());
        Assert.assertEquals("HIM", a39.getMSH().getMsh6_ReceivingFacility().getNamespaceID().getValue());

        Assert.assertEquals("A40", a39.getEVN().getEventTypeCode().getValue());

        Assert.assertEquals("https://example.com", a39.getSFT().getSft1_SoftwareVendorOrganization().getOrganizationName().getValue());
        Assert.assertEquals("username", a39.getSFT().getSft3_SoftwareProductName().getValue());
        Assert.assertEquals("password", a39.getSFT().getSft5_SoftwareProductInformation().getValue());

        Assert.assertNotNull(a39);

        EmrRequest actual = HL7v2MessageBuilderUtils.convertToEmrMessage(a39);

        Assert.assertNotNull(actual);

        JsonSerializer serializer = new JsonSerializer();
        System.out.println(serializer.serializeToString(actual));
    }

    /**
     * Test the parsing of an ZXT_A39 message.
     *
     * @throws IOException  if an IO exception occurs
     * @throws HL7Exception if an HL7 exception occurs
     */
    @Test
    public void testParseZxtA39() throws IOException, HL7Exception {
        String message = TestUtils.getHL7TestMessage().replaceAll("\\n", "\r");

        System.out.println(message);

        ZXT_A39 a39 = HL7v2MessageBuilderUtils.parseZxtA39(message);

        Assert.assertEquals("12341", a39.getZXT().getRitaId().getId().getValue());
        Assert.assertEquals("12342", a39.getZXT().getVotersId().getValue());
        Assert.assertEquals("BTH_CRT", a39.getZXT().getRitaId().getIdType().getValue());
        Assert.assertEquals("Tanzania", a39.getZXT().getRitaId().getCountryName().getValue());
        Assert.assertEquals("TZA", a39.getZXT().getRitaId().getCountryCode().getValue());

        Assert.assertEquals("NHCR", a39.getMSH().getMsh3_SendingApplication().getNamespaceID().getValue());
        Assert.assertEquals("NHCR", a39.getMSH().getMsh4_SendingFacility().getNamespaceID().getValue());
        Assert.assertEquals("CTC", a39.getMSH().getMsh5_ReceivingApplication().getNamespaceID().getValue());
        Assert.assertEquals("HIM", a39.getMSH().getMsh6_ReceivingFacility().getNamespaceID().getValue());

        Assert.assertEquals("A40", a39.getEVN().getEventTypeCode().getValue());

        Assert.assertEquals("https://example.com", a39.getSFT().getSft1_SoftwareVendorOrganization().getOrganizationName().getValue());
        Assert.assertEquals("1.4", a39.getSFT().getSft2_SoftwareCertifiedVersionOrReleaseNumber().getValue());
        Assert.assertEquals("username", a39.getSFT().getSft3_SoftwareProductName().getValue());
        Assert.assertEquals("binary id", a39.getSFT().getSft4_SoftwareBinaryID().getValue());
        Assert.assertEquals("password", a39.getSFT().getSft5_SoftwareProductInformation().getValue());

        Assert.assertNotNull(a39);
    }
//
//    @Test
//    public void testAdtA39() throws IOException, HL7Exception {
//
//        String message = TestUtils.getStandardHL7TestMessage();
//
//        System.out.println(message);
//
//        ADT_A39 a39 = HL7v2MessageBuilderUtils.parseAdtA39(message);
//
//        Assert.assertEquals("NHCR", a39.getMSH().getMsh3_SendingApplication().getNamespaceID().getValue());
//        Assert.assertEquals("NHCR", a39.getMSH().getMsh4_SendingFacility().getNamespaceID().getValue());
//        Assert.assertEquals("CTC", a39.getMSH().getMsh5_ReceivingApplication().getNamespaceID().getValue());
//        Assert.assertEquals("HIM", a39.getMSH().getMsh6_ReceivingFacility().getNamespaceID().getValue());
//
//        Assert.assertEquals("A40", a39.getEVN().getEventTypeCode().getValue());
//
//        Assert.assertEquals("https://example.com", a39.getSFT().getSft1_SoftwareVendorOrganization().getOrganizationName().getValue());
//        Assert.assertEquals("1.4", a39.getSFT().getSft2_SoftwareCertifiedVersionOrReleaseNumber().getValue());
//        Assert.assertEquals("username", a39.getSFT().getSft3_SoftwareProductName().getValue());
//        Assert.assertEquals("binary id", a39.getSFT().getSft4_SoftwareBinaryID().getValue());
//        Assert.assertEquals("password", a39.getSFT().getSft5_SoftwareProductInformation().getValue());
//    }

    /**
     * Test the parsing of an ZXT_A39 message.
     */
    @Test(expected = ArgumentNullException.class)
    public void testParseZxtA39EmptyMessage() throws HL7Exception {
        HL7v2MessageBuilderUtils.parseZxtA39("");
    }

    /**
     * Test the parsing of an ZXT_A39 message.
     */
    @Test(expected = HL7Exception.class)
    public void testParseZxtA39InvalidMessage() throws HL7Exception {
        HL7v2MessageBuilderUtils.parseZxtA39("MSH|^~\\&|NHCR|NHCR|CTC|HIM|20210210103700|");
    }

    /**
     * Test the parsing of an ZXT_A39 message.
     */
    @Test(expected = ArgumentNullException.class)
    public void testParseZxtA39NullMessage() throws HL7Exception {
        HL7v2MessageBuilderUtils.parseZxtA39(null);
    }
}
