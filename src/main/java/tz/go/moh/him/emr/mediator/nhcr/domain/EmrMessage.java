package tz.go.moh.him.emr.mediator.nhcr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an EMR message.
 */
public class EmrMessage {

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("dob")
    private String dateOfBirth;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("ids")
    private List<GenericId> ids;

    @JsonProperty("insurance")
    private InsuranceId insuranceId;

    @JsonProperty("mrn")
    private String medicalRecordNumber;

    @JsonProperty("mergedRecords")
    private List<MergedRecord> mergedRecords;

    @JsonProperty("middleName")
    private String middleName;

    @JsonProperty("otherName")
    private String otherName;

    @JsonProperty("permanentAddress")
    private Address permanentAddress;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("placeEncountered")
    private String placeEncounteredCode;

    @JsonProperty("placeOfBirth")
    private Address placeOfBirth;

    @JsonProperty("postOrUpdate")
    private String postOrUpdate;

    @JsonProperty("programs")
    private List<ProgramId> programIds;

    @JsonProperty("residentialAddress")
    private Address residentialAddress;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("uln")
    private String universalLifetimeNumber;

    public EmrMessage() {
        setIds(new ArrayList<>());
        setProgramIds(new ArrayList<>());
        setMergedRecords(new ArrayList<>());
    }

    public List<MergedRecord> getMergedRecords() {
        return mergedRecords;
    }

    public void setMergedRecords(List<MergedRecord> mergedRecords) {
        this.mergedRecords = mergedRecords;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<GenericId> getIds() {
        return ids;
    }

    public void setIds(List<GenericId> ids) {
        this.ids = ids;
    }

    public InsuranceId getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(InsuranceId insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlaceEncounteredCode() {
        return placeEncounteredCode;
    }

    public void setPlaceEncounteredCode(String placeEncounteredCode) {
        this.placeEncounteredCode = placeEncounteredCode;
    }

    public Address getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(Address placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPostOrUpdate() {
        return postOrUpdate;
    }

    public void setPostOrUpdate(String postOrUpdate) {
        this.postOrUpdate = postOrUpdate;
    }

    public List<ProgramId> getProgramIds() {
        return programIds;
    }

    public void setProgramIds(List<ProgramId> programIds) {
        this.programIds = programIds;
    }

    public Address getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(Address residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUniversalLifetimeNumber() {
        return universalLifetimeNumber;
    }

    public void setUniversalLifetimeNumber(String universalLifetimeNumber) {
        this.universalLifetimeNumber = universalLifetimeNumber;
    }
}
