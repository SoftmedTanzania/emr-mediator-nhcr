package tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.segment;

import ca.uhn.hl7v2.HL7Exception;
import org.junit.Assert;
import org.junit.Test;
import tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.message.ZXT_A39;
import tz.go.moh.him.emr.mediator.nhcr.utils.HL7v2MessageBuilderUtils;
import tz.go.moh.him.emr.mediator.nhcr.utils.TestUtils;

import java.io.IOException;

/**
 * Contains tests for the {@link ZXT} class.
 */
public class ZxtTest {

    /**
     * Test the ZXT class.
     *
     * @throws IOException  if an IO exception occurs
     * @throws HL7Exception if an HL7 exception occurs
     */
    @Test
    public void testZXT_A39() throws IOException, HL7Exception {

        ZXT_A39 a39 = HL7v2MessageBuilderUtils.parseZxtA39(TestUtils.getHL7TestMessage());
        ZXT zxt = new ZXT(a39.getParent(), a39.getModelClassFactory());

        Assert.assertNotNull(zxt);
    }
}
