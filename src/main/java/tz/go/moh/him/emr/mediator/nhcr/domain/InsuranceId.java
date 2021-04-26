package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an insurance id.
 */
public class InsuranceId extends GenericId {
    /**
     * The value.
     */
    @JsonProperty("name")
    private String value;

    /**
     * Initializes a new instance of the {@link InsuranceId} class.
     */
    public InsuranceId() {
    }

    /**
     * Initializes a new instance of the {@link InsuranceId} class.
     *
     * @param key   The key.
     * @param value The value.
     */
    public InsuranceId(String key, String value) {
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
