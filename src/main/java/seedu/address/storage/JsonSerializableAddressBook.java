package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;

import seedu.address.model.cap.CapEntry;
import seedu.address.model.homework.Homework;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_CAP_ENTRY = "Cap Entry list contains duplicate cap entry(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedCapEntry> capEntryList = new ArrayList<>();
    private final List<JsonAdaptedHomeworkList> homeworkList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
        this.homeworkList.addAll(homeworkList);
    }

    //    /**
    //     * Constructs a {@code JsonSerializableAddressBook} with the given capEntryList and persons.
    //     */
    //    @JsonCreator
    //    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
    //                                       @JsonProperty("capEntryList") List<JsonAdaptedCapEntry> capEntryList) {
    //        this.persons.addAll(persons);
    //        this.capEntryList.addAll(capEntryList);
    //    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        capEntryList.addAll(source.getCapEntryList().stream().map(JsonAdaptedCapEntry::new)
                .collect(Collectors.toList()));
        homeworkList.addAll(source.getHomeworkList().stream().map(JsonAdaptedHomeworkList::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this UltiStudent into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }

        for (JsonAdaptedCapEntry jsonAdaptedCapEntry : capEntryList) {
            CapEntry capEntry = jsonAdaptedCapEntry.toModelType();
            if (addressBook.hasCapEntry(capEntry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CAP_ENTRY);
            }
            addressBook.addCapEntry(capEntry);
        }

        for (JsonAdaptedHomeworkList jsonAdaptedHomeworkList : homeworkList) {
            Homework homework = jsonAdaptedHomeworkList.toModelType();
            if (addressBook.hasHomework(homework)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addHomework(homework);

        }
        return addressBook;
    }

}
