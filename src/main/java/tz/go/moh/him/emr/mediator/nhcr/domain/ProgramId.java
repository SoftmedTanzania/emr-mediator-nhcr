package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a program id.
 */
public class ProgramId {

    /**
     * The assigning authority.
     */
    @JsonProperty("assigningAuthority")
    private String assigningAuthority;

    /**
     * The assigning facility.
     */
    @JsonProperty("assigningFacility")
    private String assigningFacility;

    /**
     * The id.
     */
    @JsonProperty("id")
    private String id;

    /**
     * Initializes a new instance of the {@link ProgramId} class.
     */
    public ProgramId() {
    }

    /**
     * Gets the assigning authority.
     *
     * @return Returns the assigning authority.
     */
    public String getAssigningAuthority() {
        return assigningAuthority;
    }

    /**
     * Sets the assigning authority.
     *
     * @param assigningAuthority The assigning authority to set.
     */
    public void setAssigningAuthority(String assigningAuthority) {
        this.assigningAuthority = assigningAuthority;
    }

    /**
     * Gets the assigning facility.
     *
     * @return Returns the assigning facility.
     */
    public String getAssigningFacility() {
        return assigningFacility;
    }

    /**
     * Sets the assigning facility.
     *
     * @param assigningFacility The assigning facility to set.
     */
    public void setAssigningFacility(String assigningFacility) {
        this.assigningFacility = assigningFacility;
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
}
