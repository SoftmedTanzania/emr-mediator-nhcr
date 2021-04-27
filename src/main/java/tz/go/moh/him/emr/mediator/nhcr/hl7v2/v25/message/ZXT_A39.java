package tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.message;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.ADT_A39;
import ca.uhn.hl7v2.model.v25.segment.IN1;
import ca.uhn.hl7v2.parser.DefaultModelClassFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.segment.ZXT;

import java.util.Arrays;

/**
 * Represents an HL7v2 message which is a derivative of ADT_A39, representing a patient merge.
 */
public class ZXT_A39 extends ADT_A39 {
    /**
     * The constructor
     *
     * @throws HL7Exception The thrown exception
     */
    public ZXT_A39() throws HL7Exception {
        this(new DefaultModelClassFactory());
    }

    /**
     * Constructor
     * <p>
     * We always have to have a constructor with this one argument
     *
     * @param factory The Model factory used
     * @throws HL7Exception The exception thrown
     */
    public ZXT_A39(ModelClassFactory factory) throws HL7Exception {
        super(factory);

        // Now, let's add the ZXT segment at the right spot
        String[] segmentNames = getNames();
        int indexOfPidPd1MrgPv1 = Arrays.asList(segmentNames).indexOf("PIDPD1MRGPV1");

        // Put the ZXT segment right after the ADT_A40_PIDPD1MRGPV1 segment
        int index = indexOfPidPd1MrgPv1 + 1;

        this.add(ZXT.class, false, false, index);
        this.add(IN1.class, false, false, index);
    }

    /**
     * Add an accessor for the IN1 segment
     *
     * @return The IN1 segment
     * @throws HL7Exception The exception thrown
     */
    public IN1 getIN1() throws HL7Exception {
        return (IN1) get("IN1");
    }

    /**
     * Add an accessor for the ZXT segment
     *
     * @return The ZXT segment
     * @throws HL7Exception The exception thrown
     */
    public ZXT getZXT() throws HL7Exception {
        return (ZXT) get("ZXT");
    }

}
