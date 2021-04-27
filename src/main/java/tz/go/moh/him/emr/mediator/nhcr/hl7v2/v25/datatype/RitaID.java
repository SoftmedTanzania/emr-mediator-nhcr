package tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.datatype;

import ca.uhn.hl7v2.model.AbstractComposite;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.v231.datatype.IS;
import ca.uhn.hl7v2.model.v25.datatype.ST;

/**
 * Represents a Rita ID.
 */
public class RitaID extends AbstractComposite {

    /**
     * The data.
     */
    private Type[] data;

    /**
     * Initializes a new instance of the {@link RitaID} class.
     *
     * @param message The message.
     */
    public RitaID(Message message) {
        super(message);
        this.init();
    }

    /**
     * Initializes this instance.
     */
    private void init() {
        this.data = new Type[5];
        this.data[0] = new ST(this.getMessage());
        this.data[1] = new IS(this.getMessage());
        this.data[2] = new IS(this.getMessage());
        this.data[3] = new ST(this.getMessage());
        this.data[4] = new ST(this.getMessage());
    }

    /**
     * Gets the components.
     *
     * @return Returns the components.
     */
    public Type[] getComponents() {
        return this.data;
    }

    /**
     * Gets a component.
     *
     * @param number The component number.
     * @return Returns the type.
     * @throws DataTypeException if a data type exception occurs
     */
    public Type getComponent(int number) throws DataTypeException {
        try {
            return this.data[number];
        } catch (ArrayIndexOutOfBoundsException var3) {
            throw new DataTypeException("Element " + number + " doesn't exist (Type " + this.getClass().getName() + " has only " + this.data.length + " components)");
        }
    }

    /**
     * Gets the id.
     *
     * @return Returns the id.
     */
    public ST getId() {
        return this.getTyped(0, ST.class);
    }

    /**
     * Gets the country code.
     *
     * @return Returns the country code.
     */
    public IS getCountryCode() {
        return this.getTyped(1, IS.class);
    }

    /**
     * Gets the id type.
     *
     * @return Returns the id type.
     */
    public IS getIdType() {
        return this.getTyped(2, IS.class);
    }

    /**
     * Gets the other data.
     *
     * @return Returns the other data.
     */
    public ST getOther() {
        return this.getTyped(3, ST.class);
    }

    /**
     * Gets the country name.
     *
     * @return Returns the country name.
     */
    public ST getCountryName() {
        return this.getTyped(4, ST.class);
    }

}
