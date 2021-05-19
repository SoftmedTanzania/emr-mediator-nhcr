package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a linkage.
 */
public class Linkage {

    /**
     * The id.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The source of the id.
     */
    @JsonProperty("sourceOfId")
    private String sourceOfId;

    /**
     * The type of linkage.
     */
    @JsonProperty("typeOfLinkage")
    private String typeOfLinkage;

    /**
     * Initializes a new instance of the {@link Linkage} class.
     */
    public Linkage() {
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
     * Gets the source of the id.
     *
     * @return Returns the source of the id.
     */
    public String getSourceOfId() {
        return sourceOfId;
    }

    /**
     * Sets the source of the id.
     *
     * @param sourceOfId The source of the id to set.
     */
    public void setSourceOfId(String sourceOfId) {
        this.sourceOfId = sourceOfId;
    }

    /**
     * Gets the type of linkage.
     *
     * @return Returns the type of linkage.
     */
    public String getTypeOfLinkage() {
        return typeOfLinkage;
    }

    /**
     * Sets the type of linkage.
     *
     * @param typeOfLinkage The type of linkage to set.
     */
    public void setTypeOfLinkage(String typeOfLinkage) {
        this.typeOfLinkage = typeOfLinkage;
    }
}
