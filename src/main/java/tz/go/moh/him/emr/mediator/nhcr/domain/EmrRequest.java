package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an EMR message.
 */
public class EmrRequest {

    /**
     * The country code.
     */
    @JsonProperty("countryCode")
    private String countryCode;

    /**
     * The date of birth.
     */
    @JsonProperty("dob")
    private String dateOfBirth;

    /**
     * The family linkage.
     */
    @JsonProperty("familyLinkages")
    private Linkage familyLinkage;

    /**
     * The first name.
     */
    @JsonProperty("firstName")
    private String firstName;

    /**
     * The last name.
     */
    @JsonProperty("lastName")
    private String lastName;

    /**
     * The patient ids.
     */
    @JsonProperty("ids")
    private List<PatientId> ids;

    /**
     * The insurance id.
     */
    @JsonProperty("insurance")
    private InsuranceId insuranceId;

    /**
     * The medical record number.
     */
    @JsonProperty("mrn")
    private String medicalRecordNumber;

    /**
     * The merged records.
     */
    @JsonProperty("mergedRecords")
    private List<MergedRecord> mergedRecords;

    /**
     * The middle name.
     */
    @JsonProperty("middleName")
    private String middleName;

    /**
     * The other linkage.
     */
    @JsonProperty("otherLinkages")
    private Linkage otherLinkage;

    /**
     * The other name.
     */
    @JsonProperty("otherName")
    private String otherName;

    /**
     * The permanent address.
     */
    @JsonProperty("permanentAddress")
    private Address permanentAddress;

    /**
     * The phone number.
     */
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    /**
     * The place encountered.
     */
    @JsonProperty("placeEncountered")
    private String placeEncounteredCode;

    /**
     * The place of birth.
     */
    @JsonProperty("placeOfBirth")
    private Address placeOfBirth;

    /**
     * The post or update option.
     */
    @JsonProperty("postOrUpdate")
    private String postOrUpdate;

    /**
     * The program ids.
     */
    @JsonProperty("programs")
    private List<ProgramId> programIds;

    /**
     * The residential address.
     */
    @JsonProperty("residentialAddress")
    private Address residentialAddress;

    /**
     * The sex.
     */
    @JsonProperty("sex")
    private String sex;

    /**
     * The universal lifetime number.
     */
    @JsonProperty("uln")
    private String universalLifetimeNumber;

    /**
     * Initializes a new instance of the {@link EmrRequest} class.
     */
    public EmrRequest() {
        setIds(new ArrayList<>());
        setProgramIds(new ArrayList<>());
        setMergedRecords(new ArrayList<>());
    }

    /**
     * Gets the family linkage.
     *
     * @return Returns the family linkage.
     */
    public Linkage getFamilyLinkage() {
        return familyLinkage;
    }

    /**
     * Sets the family linkage.
     *
     * @param familyLinkage The family linkage to set.
     */
    public void setFamilyLinkage(Linkage familyLinkage) {
        this.familyLinkage = familyLinkage;
    }

    /**
     * Gets the other linkage.
     *
     * @return Returns the other linkage.
     */
    public Linkage getOtherLinkage() {
        return otherLinkage;
    }

    /**
     * Sets the other linkage.
     *
     * @param otherLinkage The other linkage to set.
     */
    public void setOtherLinkage(Linkage otherLinkage) {
        this.otherLinkage = otherLinkage;
    }

    /**
     * Gets the merged records.
     *
     * @return Returns the merged records.
     */
    public List<MergedRecord> getMergedRecords() {
        return mergedRecords;
    }

    /**
     * Sets the merged records.
     *
     * @param mergedRecords The merged records to set.
     */
    public void setMergedRecords(List<MergedRecord> mergedRecords) {
        this.mergedRecords = mergedRecords;
    }

    /**
     * Gets the country code.
     *
     * @return Returns the country code.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the country code.
     *
     * @param countryCode The country code to set.
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Gets the date of birth.
     *
     * @return Returns the date of birth.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth.
     *
     * @param dateOfBirth The date of birth to set.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the first name.
     *
     * @return Returns the first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return Returns the last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the patient ids.
     *
     * @return Returns the patient ids.
     */
    public List<PatientId> getIds() {
        return ids;
    }

    /**
     * Sets the patient ids.
     *
     * @param ids The patient ids to set.
     */
    public void setIds(List<PatientId> ids) {
        this.ids = ids;
    }

    /**
     * Gets the insurance id.
     *
     * @return Returns the insurance id.
     */
    public InsuranceId getInsuranceId() {
        return insuranceId;
    }

    /**
     * Sets the insurance id.
     *
     * @param insuranceId The insurance id to set.
     */
    public void setInsuranceId(InsuranceId insuranceId) {
        this.insuranceId = insuranceId;
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

    /**
     * Gets the middle name.
     *
     * @return Returns the middle name.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName The middle name to set.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the other name.
     *
     * @return Returns the other name.
     */
    public String getOtherName() {
        return otherName;
    }

    /**
     * Sets the other name.
     *
     * @param otherName The other name to set.
     */
    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    /**
     * Gets the permanent address.
     *
     * @return Returns the permanent address.
     */
    public Address getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * Sets the permanent address.
     *
     * @param permanentAddress The permanent address to set.
     */
    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    /**
     * Gets the phone number.
     *
     * @return Returns the phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber The phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the place encountered.
     *
     * @return Returns the place encountered.
     */
    public String getPlaceEncounteredCode() {
        return placeEncounteredCode;
    }

    /**
     * Sets the place encountered.
     *
     * @param placeEncounteredCode The place encountered to set.
     */
    public void setPlaceEncounteredCode(String placeEncounteredCode) {
        this.placeEncounteredCode = placeEncounteredCode;
    }

    /**
     * Gets the place of birth.
     *
     * @return Returns the place of birth.
     */
    public Address getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * Sets the place of birth.
     *
     * @param placeOfBirth The place of birth to set.
     */
    public void setPlaceOfBirth(Address placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    /**
     * Gets the post or update option.
     *
     * @return Returns the post or update option.
     */
    public String getPostOrUpdate() {
        return postOrUpdate;
    }

    /**
     * Sets the post or update option.
     *
     * @param postOrUpdate The post or update option to set.
     */
    public void setPostOrUpdate(String postOrUpdate) {
        this.postOrUpdate = postOrUpdate;
    }

    /**
     * Gets the program ids.
     *
     * @return Returns the program ids.
     */
    public List<ProgramId> getProgramIds() {
        return programIds;
    }

    /**
     * Sets the program ids.
     *
     * @param programIds The program ids to set.
     */
    public void setProgramIds(List<ProgramId> programIds) {
        this.programIds = programIds;
    }

    /**
     * Gets the residential address.
     *
     * @return Returns the residential address.
     */
    public Address getResidentialAddress() {
        return residentialAddress;
    }

    /**
     * Sets the residential address.
     *
     * @param residentialAddress The residential address to set.
     */
    public void setResidentialAddress(Address residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    /**
     * Gets the sex.
     *
     * @return Returns the sex.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the sex.
     *
     * @param sex The sex to set.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets the universal lifetime number.
     *
     * @return Returns the universal lifetime number.
     */
    public String getUniversalLifetimeNumber() {
        return universalLifetimeNumber;
    }

    /**
     * Sets the universal lifetime number.
     *
     * @param universalLifetimeNumber The universal lifetime number to set.
     */
    public void setUniversalLifetimeNumber(String universalLifetimeNumber) {
        this.universalLifetimeNumber = universalLifetimeNumber;
    }
}
