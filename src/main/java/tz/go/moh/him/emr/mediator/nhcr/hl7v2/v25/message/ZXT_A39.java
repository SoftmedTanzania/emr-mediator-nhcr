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

        int indexOfPatient = Arrays.asList(this.getNames()).indexOf("PATIENT");

        int index = indexOfPatient + 1;

        // put the IN1 segment after the ADT_A39_PATIENT segment
        this.add(IN1.class, false, false, index);

        // Put the ZXT segment right after the IN1 segment
        this.add(ZXT.class, false, false, index + 1);

    }

    /**
     * Add an accessor for the IN1 segment
     *
     * @return Returns the IN1 segment.
     */
    public IN1 getIN1() {
        return getTyped("IN1", IN1.class);
    }

    /**
     * Add an accessor for the ZXT segment
     *
     * @return Returns the ZXT segment.
     */
    public ZXT getZXT() {
        return getTyped("ZXT", ZXT.class);
    }

}
