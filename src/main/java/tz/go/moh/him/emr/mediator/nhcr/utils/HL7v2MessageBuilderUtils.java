package tz.go.moh.him.emr.mediator.nhcr.utils;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
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
     * @param a40 The HL7v2 message.
     * @return Returns the converted {@link EmrRequest}.
     */
    public static EmrRequest convertToEmrMessage(ZXT_A39 a40) throws ParseException {
        EmrRequest emrRequest = new EmrRequest();

        // assume PUT
        // since we are trying to notify the source system that a patient has been merged
        // hence UPDATE
        emrRequest.setPostOrUpdate("U");

        int reps = a40.getPATIENTReps();

        for (int i = 0; i < reps; i++) {
            PID pid = a40.getPATIENT(i).getPID();

            if (pid == null) {
                continue;
            }

            List<String> mergeIds = new ArrayList<>();

            // get the merge ids
            MRG mrg = a40.getPATIENT(i).getMRG();

            // build a list of ids from the MRG segment
            for (int k = 0; k < mrg.getMrg1_PriorPatientIdentifierListReps(); k++) {
                String value = mrg.getMrg1_PriorPatientIdentifierList(k).getCx1_IDNumber().getValue();
                mergeIds.add(value);
                emrRequest.getMergedRecords().add(new MergedRecord(value));
            }

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

            // Address
            if (pid.getPatientAddress(0) != null) {
                Address address = new Address();
                address.setRegion(pid.getPatientAddress(0).getCity().getValue());

                // parse the other designation parts
                if (pid.getPatientAddress(0).getOtherDesignation().getValue() != null) {
                    String[] designation = pid.getPatientAddress(0).getOtherDesignation().getValue().split("\\*");
                    address.setCouncil(designation[0]);
                    address.setWard(designation[1]);
                    address.setVillage(designation[2]);
                }

                emrRequest.setPermanentAddress(address);
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


            // set the national id
            emrRequest.getIds().addAll(Arrays.stream(pid.getCitizenship()).map(c -> new PatientId(NATIONAL_ID, c.getIdentifier().getValue())).collect(Collectors.toList()));

            // set the voters id
            emrRequest.getIds().add(new PatientId(VOTERS_ID, a40.getZXT().getVotersId().getValue()));

            // set the drivers license id
            emrRequest.getIds().add(new PatientId(DRIVERS_LICENSE_ID, pid.getDriverSLicenseNumberPatient().getDln1_LicenseNumber().getValue()));

            // set the rita id
            emrRequest.getIds().add(new PatientId(RITA_ID, a40.getZXT().getRitaId().getId().getValue()));

            // set the insurance id
            InsuranceId insuranceId = new InsuranceId();

            insuranceId.setId(a40.getIN1().getIn11_SetIDIN1().getValue());

            if (a40.getIN1().getIn14_InsuranceCompanyName(0) != null) {
                insuranceId.setName(a40.getIN1().getIn14_InsuranceCompanyName(0).getOrganizationName().getValue());
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
