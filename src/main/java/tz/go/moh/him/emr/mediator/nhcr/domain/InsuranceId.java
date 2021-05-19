package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an insurance id.
 */
public class InsuranceId {

    /**
     * The id.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The name.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Initializes a new instance of the {@link InsuranceId} class.
     */
    public InsuranceId() {
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
     * Gets the name.
     *
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name The name to set,
     */
    public void setName(String name) {
        this.name = name;
    }
}
