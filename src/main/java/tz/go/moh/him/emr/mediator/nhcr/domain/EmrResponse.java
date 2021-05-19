package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an EMR response message.
 */
public class EmrResponse {

    /**
     * The status.
     */
    @JsonProperty("status")
    private String status;

    /**
     * Initializes a new instance of the {@link EmrResponse} class.
     */
    public EmrResponse() {
    }

    /**
     * Gets the status.
     *
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
