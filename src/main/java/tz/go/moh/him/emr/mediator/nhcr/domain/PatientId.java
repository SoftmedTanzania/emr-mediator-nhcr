package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a patient id.
 */
public class PatientId extends GenericId {

    /**
     * The value.
     */
    @JsonProperty("type")
    private String value;

    /**
     * Initializes a new instance of the {@link PatientId} class.
     */
    public PatientId() {
    }

    /**
     * Initializes a new instance of the {@link PatientId} class.
     *
     * @param key   The key.
     * @param value The value.
     */
    public PatientId(String key, String value) {
        super(key);
        this.setValue(value);
    }

    /**
     * Gets the value.
     *
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
