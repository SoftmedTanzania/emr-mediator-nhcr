package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a merged record.
 */
public class MergedRecord {

    /**
     * The medical record number.
     */
    @JsonProperty("mrn")
    private String medicalRecordNumber;

    /**
     * Initializes a new instance of the {@link MergedRecord} class.
     */
    public MergedRecord() {
    }

    /**
     * Initializes a new instance of the {@link MergedRecord} class.
     *
     * @param medicalRecordNumber The medical record number.
     */
    public MergedRecord(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    /**
     * Gets the medical record number.
     *
     * @return Returns the medical record number.
     */
    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }


    /**
     * Sets the medical record number.
     *
     * @param medicalRecordNumber The medical record number to set.
     */
    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }
}
