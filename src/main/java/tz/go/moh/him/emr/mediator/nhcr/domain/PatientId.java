package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a patient id.
 */
public class PatientId {

    /**
     * The id.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The value.
     */
    @JsonProperty("type")
    private String type;

    /**
     * Initializes a new instance of the {@link PatientId} class.
     */
    public PatientId() {
    }

    /**
     * Initializes a new instance of the {@link PatientId} class.
     *
     * @param type The type.
     * @param id   The id value.
     */
    public PatientId(String type, String id) {
        this();
        this.setType(type);
        this.setId(id);
    }

    /**
     * Gets the id.
     *
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the type.
     *
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
}
