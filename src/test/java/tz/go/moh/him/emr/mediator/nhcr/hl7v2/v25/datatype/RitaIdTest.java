package tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.datatype;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import org.junit.Assert;
import org.junit.Test;
import tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.message.ZXT_A39;
import tz.go.moh.him.emr.mediator.nhcr.utils.HL7v2MessageBuilderUtils;
import tz.go.moh.him.emr.mediator.nhcr.utils.TestUtils;

import java.io.IOException;

/**
 * Contains test for the {@link RitaId} class.
 */
public class RitaIdTest {

    /**
     * Test the RitaID class.
     *
     * @throws IOException  if an IO exception occurs
     * @throws HL7Exception if an HL7 exception occurs
     */
    @Test
    public void testRitaId() throws IOException, HL7Exception {
        ZXT_A39 a39 = HL7v2MessageBuilderUtils.parseZxtA39(TestUtils.getHL7TestMessage());

        Assert.assertNotNull(a39);

        RitaId ritaId = new RitaId(a39);

        Assert.assertNotNull(ritaId);

        Assert.assertNull(ritaId.getOther().getValue());
    }

    /**
     * Test the RitaID class.
     *
     * @throws IOException  if an IO exception occurs
     * @throws HL7Exception if an HL7 exception occurs
     */
    @Test(expected = DataTypeException.class)
    public void testRitaIdException() throws IOException, HL7Exception {
        ZXT_A39 a39 = HL7v2MessageBuilderUtils.parseZxtA39(TestUtils.getHL7TestMessage());

        Assert.assertNotNull(a39);

        RitaId ritaId = new RitaId(a39);

        Assert.assertNotNull(ritaId);

        ritaId.getComponent(5);
    }
}
