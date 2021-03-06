package tz.go.moh.him.emr.mediator.nhcr.utils;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.v25.datatype.XAD;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.segment.MRG;
import ca.uhn.hl7v2.model.v25.segment.PID;
import ca.uhn.hl7v2.parser.CustomModelClassFactory;
import ca.uhn.hl7v2.parser.Parser;
import tz.go.moh.him.emr.mediator.nhcr.domain.*;
import tz.go.moh.him.emr.mediator.nhcr.hl7v2.v25.message.ZXT_A39;
import tz.go.moh.him.mediator.core.exceptions.ArgumentNullException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents an HL7v2 message builder utility.
 */
public class HL7v2MessageBuilderUtils {

    /**
     * The EMR date format.
     */
    private static final SimpleDateFormat EMR_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * The NHCR date format.
     */
    private static final SimpleDateFormat NHCR_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    /**
     * The national id.
     */
    private static final String NATIONAL_ID = "NATIONAL_ID";

    /**
     * The voters id.
     */
    private static final String VOTERS_ID = "VOTERS_ID";

    /**
     * The drivers license id.
     */
    private static final String DRIVERS_LICENSE_ID = "DRIVERS_LICENSE_ID";

    /**
     * The Rita id.
     */
    private static final String RITA_ID = "RITA_ID";

    /**
     * Converts an {@link ZXT_A39} instance to an {@link EmrRequest} instance.
     *
     * @param a39 The HL7v2 message.
     * @return Returns the converted {@link EmrRequest}.
     * @throws ParseException if a parse exception is thrown
     */
    public static EmrRequest convertToEmrMessage(ZXT_A39 a39) throws ParseException {
        EmrRequest emrRequest = new EmrRequest();

        // assume PUT
        // since we are trying to notify the source system that a patient has been merged
        // hence UPDATE
        emrRequest.setPostOrUpdate("U");

        int reps = a39.getPATIENTReps();

        for (int i = 0; i < reps; i++) {
            PID pid = a39.getPATIENT(i).getPID();

            // get the merge ids
            MRG mrg = a39.getPATIENT(i).getMRG();

            // build a list of ids from the MRG segment
            List<String> mergeIds = Arrays.stream(mrg.getMrg1_PriorPatientIdentifierList()).map(c -> {
                emrRequest.getMergedRecords().add(new MergedRecord(c.getCx1_IDNumber().getValue()));
                return c.getCx1_IDNumber().getValue();
            }).collect(Collectors.toList());

            // set the MRN, we assume the first id in the merge segment is the ID of the surviving record
            emrRequest.setMedicalRecordNumber(mrg.getMrg1_PriorPatientIdentifierList(0).getCx1_IDNumber().getValue());

            // Identifiers
            int ids = pid.getPatientIdentifierListReps();

            if (ids > 0) {

                List<ProgramId> programs = new ArrayList<>();

                for (int j = 0; j < ids; j++) {
                    ProgramId program = new ProgramId();

                    // skip the merge id
                    if (mergeIds.contains(pid.getPatientIdentifierList(j).getCx1_IDNumber().getValue())) {
                        continue;
                    }

                    program.setId(pid.getPatientIdentifierList(j).getCx1_IDNumber().getValue());
                    program.setAssigningAuthority(pid.getPatientIdentifierList(j).getAssigningAuthority().getNamespaceID().getValue());
                    program.setAssigningFacility(pid.getPatientIdentifierList(j).getAssigningFacility().getNamespaceID().getValue());

                    programs.add(program);
                }

                emrRequest.setProgramIds(programs);
            }

            // Name
            if (pid.getPatientName(0) != null) {
                emrRequest.setFirstName(pid.getPatientName(0).getGivenName().getValue());
                emrRequest.setMiddleName(pid.getPatientName(0).getSecondAndFurtherGivenNamesOrInitialsThereof().getValue());
                emrRequest.setLastName(pid.getPatientName(0).getFamilyName().getSurname().getValue());
            }

            // Sex
            if (pid.getAdministrativeSex() != null) {
                emrRequest.setSex(pid.getAdministrativeSex().getValue());
            }

            XAD permanentAddress = Arrays.stream(pid.getPatientAddress()).filter(c -> "H".equals(c.getAddressType().getValue())).findFirst().orElse(null);
            XAD residentialAddress = Arrays.stream(pid.getPatientAddress()).filter(c -> "C".equals(c.getAddressType().getValue())).findFirst().orElse(null);
            XAD birthAddress = Arrays.stream(pid.getPatientAddress()).filter(c -> "BR".equals(c.getAddressType().getValue())).findFirst().orElse(null);

            if (permanentAddress != null) {
                emrRequest.setPermanentAddress(mapAddress(permanentAddress));
            }

            if (residentialAddress != null) {
                emrRequest.setResidentialAddress(mapAddress(residentialAddress));
            }

            if (birthAddress != null) {
                emrRequest.setPlaceOfBirth(mapAddress(birthAddress));
            }

            // Date of Birth
            if (pid.getDateTimeOfBirth() != null) {
                // HACK: parse and re-format the date into the date format the EMR is expecting
                emrRequest.setDateOfBirth(EMR_DATE_FORMAT.format(NHCR_DATE_FORMAT.parse(pid.getDateTimeOfBirth().getTime().getValue())));
            }

            // set the uln
            emrRequest.setUniversalLifetimeNumber(pid.getSSNNumberPatient().getValue());

            // set the other name
            if (pid.getPatientAlias(0) != null) {
                emrRequest.setOtherName(pid.getPatientAlias(0).getGivenName().getValue());
            }

            // set the phone country code and phone number
            emrRequest.setCountryCode(pid.getPhoneNumberHome(0).getXtn5_CountryCode().getValue());
            emrRequest.setPhoneNumber(pid.getPhoneNumberHome(0).getXtn7_LocalNumber().getValue());

            // set the national id
//            emrRequest.getIds().addAll(Arrays.stream(pid.getNationality().getIdentifier().getValue()).map(c -> new PatientId(NATIONAL_ID, c.getIdentifier().getValue())).collect(Collectors.toList()));

            // set the national id
            emrRequest.getIds().add(new PatientId(NATIONAL_ID, pid.getNationality().getIdentifier().getValue()));

            // set the voters id
            emrRequest.getIds().add(new PatientId(VOTERS_ID, a39.getZXT().getVotersId().getValue()));

            // set the drivers license id
            emrRequest.getIds().add(new PatientId(DRIVERS_LICENSE_ID, pid.getDriverSLicenseNumberPatient().getDln1_LicenseNumber().getValue()));

            // set the rita id
            emrRequest.getIds().add(new PatientId(RITA_ID, a39.getZXT().getRitaId().getId().getValue()));

            // set the insurance id
            InsuranceId insuranceId = new InsuranceId();

            insuranceId.setId(a39.getIN1().getIn11_SetIDIN1().getValue());

            if (a39.getIN1().getIn14_InsuranceCompanyName(0) != null) {
                insuranceId.setName(a39.getIN1().getIn14_InsuranceCompanyName(0).getOrganizationName().getValue());
            }

            emrRequest.setInsuranceId(insuranceId);
        }

        return emrRequest;
    }

    /**
     * Creates an ACK message.
     *
     * @param incomingMessageId The incoming message id.
     * @return Returns the created ACK message.
     * @throws HL7Exception if an HL7 exception occurs
     * @throws IOException  if an IO exception occurs
     */
    public static ACK createAck(String incomingMessageId) throws HL7Exception, IOException {
        ACK ack = new ACK();

        ack.initQuickstart("ACK", "A01", "P");

        ack.getMSH().getDateTimeOfMessage().getTime().setValue(Calendar.getInstance());
        ack.getMSH().getMessageControlID().setValue(UUID.randomUUID().toString());
        ack.getMSA().getMsa2_MessageControlID().setValue(incomingMessageId);

        return ack;
    }

    /**
     * Creates an ACK message.
     *
     * @param incomingMessageId  The incoming message id.
     * @param successfulResponse If the response was successful.
     * @return Returns the created ACK message.
     * @throws HL7Exception if an HL7 exception occurs
     * @throws IOException  if an IO exception occurs
     */
    public static ACK createAck(String incomingMessageId, boolean successfulResponse) throws HL7Exception, IOException {
        ACK ack = createAck(incomingMessageId);

        ack.getMSA().getMsa1_AcknowledgmentCode().setValue(successfulResponse ? "AA" : "AE");

        return ack;
    }

    /**
     * Maps an address.
     *
     * @param xad The address to map.
     * @return Returns the mapped address.
     */
    private static Address mapAddress(XAD xad) {
        Address address = new Address();

        address.setRegion(xad.getCity().getValue());

        // parse the other designation parts
        if (xad.getOtherDesignation().getValue() != null) {
            String[] designation = xad.getOtherDesignation().getValue().split("\\*");
            address.setCouncil(designation[0]);
            address.setWard(designation[1]);
            address.setVillage(designation[2]);
        }

        return address;
    }

    /**
     * Parses an HL7v2 message.
     *
     * @param message The message to parse.
     * @return Returns the parsed message.
     * @throws HL7Exception if an HL7 exception occurs
     */
    public static ZXT_A39 parseZxtA39(String message) throws HL7Exception {
        if (message == null || "".equals(message)) {
            throw new ArgumentNullException("message - Value cannot be null");
        }

        HapiContext context = new DefaultHapiContext();
        Parser parser = context.getPipeParser();

        context.setModelClassFactory(new CustomModelClassFactory("tz.go.moh.him.emr.mediator.nhcr.hl7v2"));

        // fix MSH-10
        message = message.replace("ADT^A40^ADT_A40", "ZXT^A39^ZXT_A39");

        // fix MSH-12
        message = message.replace("|2.3.1|", "|2.5|");

        return (ZXT_A39) parser.parse(message);
    }
}
