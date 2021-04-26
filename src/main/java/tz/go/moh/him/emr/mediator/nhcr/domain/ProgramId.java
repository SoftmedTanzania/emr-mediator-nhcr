package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a program id.
 */
public class ProgramId extends GenericId {

    /**
     * The value.
     */
    @JsonProperty("name")
    private String value;

    /**
     * Initializes a new instance of the {@link ProgramId} class.
     */
    public ProgramId() {
    }

    /**
     * Initializes a new instance of the {@link ProgramId} class.
     *
     * @param key   The key.
     * @param value The value.
     */
    public ProgramId(String key, String value) {
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
