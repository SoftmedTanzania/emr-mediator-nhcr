package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;


/**
 * Represents a generic id type.
 */
public abstract class GenericId {

    /**
     * The key.
     */
    @JsonProperty("id")
    private String key;

    /**
     * Initializes a new instance of the {@link GenericId} class.
     */
    public GenericId() {
    }

    /**
     * Initializes a new instance of the {@link GenericId} class.
     *
     * @param key The key.
     */
    public GenericId(String key) {
        this.key = key;
    }

    /**
     * Gets the key.
     *
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }
}
