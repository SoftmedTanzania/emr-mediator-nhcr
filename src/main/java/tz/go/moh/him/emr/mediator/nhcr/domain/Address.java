package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an address.
 */
public class Address {

    /**
     * The region.
     */
    @JsonProperty("region")
    private String region;

    /**
     * The council.
     */
    @JsonProperty("council")
    private String council;

    /**
     * The ward.
     */
    @JsonProperty("ward")
    private String ward;

    /**
     * The village.
     */
    @JsonProperty("village")
    private String village;

    /**
     * Initializes a new instance of the {@link Address} class.
     */
    public Address() {
    }

    /**
     * Initializes a new instance of the {@link Address} class.
     *
     * @param region  The region.
     * @param council The council.
     * @param ward    The ward.
     * @param village The village.
     */
    public Address(String region, String council, String ward, String village) {
        this.region = region;
        this.council = council;
        this.ward = ward;
        this.village = village;
    }

    /**
     * Gets the region.
     *
     * @return Returns the region.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region.
     *
     * @param region The region to set.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets the council.
     *
     * @return Returns the council.
     */
    public String getCouncil() {
        return council;
    }

    /**
     * Sets the council.
     *
     * @param council The council to set.
     */
    public void setCouncil(String council) {
        this.council = council;
    }

    /**
     * Gets the ward.
     *
     * @return Returns the ward.
     */
    public String getWard() {
        return ward;
    }

    /**
     * Sets the ward.
     *
     * @param ward The ward to set.
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /**
     * Gets the village.
     *
     * @return Returns the village.
     */
    public String getVillage() {
        return village;
    }

    /**
     * Sets the village.
     *
     * @param village The village to set.
     */
    public void setVillage(String village) {
        this.village = village;
    }
}
