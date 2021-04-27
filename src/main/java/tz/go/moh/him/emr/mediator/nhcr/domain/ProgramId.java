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

    public String getAssigningAuthority() {
        return assigningAuthority;
    }

    public void setAssigningAuthority(String assigningAuthority) {
        this.assigningAuthority = assigningAuthority;
    }

    public String getAssigningFacility() {
        return assigningFacility;
    }

    public void setAssigningFacility(String assigningFacility) {
        this.assigningFacility = assigningFacility;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
