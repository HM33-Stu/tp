package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Remark;
import seedu.address.model.internship.Role;

/**
 * Jackson-friendly version of {@link Internship}.
 */

public class JsonAdaptedInternship {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Internship's %s field is missing!";

    private final String companyName;
    private final String contactName;
    private final String contactEmail;
    private final String contactNumber;
    private final String applicationStatus;
    private final String location;
    private final String description;
    private final String role;
    private final String remark;

    /**
     * Constructs a {@code JsonAdaptedInternship} with the given internship details.
     */
    @JsonCreator
    public JsonAdaptedInternship(@JsonProperty("companyName") String companyName,
                                 @JsonProperty("contactName") String contactName,
                                 @JsonProperty("contactEmail") String contactEmail,
                                 @JsonProperty("contactNumber") String contactNumber,
                                 @JsonProperty("location") String location,
                                 @JsonProperty("status") String applicationStatus,
                                 @JsonProperty("description") String description,
                                 @JsonProperty("role") String role,
                                 @JsonProperty("remark") String remark) {
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactNumber = contactNumber;
        this.applicationStatus = applicationStatus;
        this.location = location;
        this.description = description;
        this.role = role;
        this.remark = remark;
    }

    /**
     * Converts a given {@code Internship} into this class for Jackson use.
     */
    public JsonAdaptedInternship(Internship source) {

        // Mandatory fields
        companyName = source.getCompanyName().companyName;
        contactName = source.getContactName().contactName;
        contactEmail = source.getContactEmail().value;
        contactNumber = source.getContactNumber().value;
        applicationStatus = source.getApplicationStatus().toString();
        description = source.getDescription().description;

        // Handle optional fields
        location = source.getLocation().map(Location::toString).orElse(null);
        role = source.getRole().map(Role::toString).orElse(null);

        // Remark field
        remark = source.getRemark().toString();
    }


    /**
     * Converts this Jackson-friendly adapted internship object into the model's {@code Internship} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted internship.
     */
    public Internship toModelType() throws IllegalValueException {
        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidCompanyName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

        if (contactName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactName.class.getSimpleName()));
        }
        if (!ContactName.isValidContactName(contactName)) {
            throw new IllegalValueException(ContactName.MESSAGE_CONSTRAINTS);
        }
        final ContactName modelContactName = new ContactName(contactName);

        if (contactEmail == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactEmail.class.getSimpleName()));
        }
        if (!ContactEmail.isValidContactEmail(contactEmail)) {
            throw new IllegalValueException(ContactEmail.MESSAGE_CONSTRAINTS);
        }
        final ContactEmail modelContactEmail = new ContactEmail(contactEmail);

        if (contactNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ContactNumber.class.getSimpleName()));
        }
        if (!ContactNumber.isValidContactNumber(contactNumber)) {
            throw new IllegalValueException(ContactNumber.MESSAGE_CONSTRAINTS);
        }
        final ContactNumber modelContactNumber = new ContactNumber(contactNumber);

        if (applicationStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ApplicationStatus.class.getSimpleName()));
        }
        if (!ApplicationStatus.isValidApplicationStatus(applicationStatus)) {
            throw new IllegalValueException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        final ApplicationStatus modelApplicationStatus = new ApplicationStatus(applicationStatus);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        final Role modelRole;
        if (role == null) {
            modelRole = null;
        } else if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        } else {
            modelRole = new Role(role);
        }

        final Location modelLocation;
        if (location == null) {
            modelLocation = null;
        } else if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        } else {
            modelLocation = new Location(location);
        }

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        return new Internship(modelCompanyName, modelContactName, modelContactEmail, modelContactNumber,
                modelLocation, modelApplicationStatus, modelDescription, modelRole, modelRemark);
    }


}
