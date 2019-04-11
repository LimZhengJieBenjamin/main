package seedu.ultistudent.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.ultistudent.model.AddressBook;
import seedu.ultistudent.model.ReadOnlyAddressBook;
import seedu.ultistudent.model.cap.CapEntry;
import seedu.ultistudent.model.cap.ModuleCredits;
import seedu.ultistudent.model.cap.ModuleGrade;
import seedu.ultistudent.model.cap.ModuleSemester;
import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.model.person.Address;
import seedu.ultistudent.model.person.Email;
import seedu.ultistudent.model.person.Name;
import seedu.ultistudent.model.person.Person;
import seedu.ultistudent.model.person.Phone;
import seedu.ultistudent.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static CapEntry[] getSampleCapEntryList() {
        return new CapEntry[]{
            new CapEntry(new ModuleCode("CS1010"), new ModuleGrade("B+"), new ModuleCredits("4"),
                new ModuleSemester("Y1S1")),
            new CapEntry(new ModuleCode("CS1231"), new ModuleGrade("A-"), new ModuleCredits("4"),
                    new ModuleSemester("Y1S2")),
            new CapEntry(new ModuleCode("GER1000"), new ModuleGrade("A-"), new ModuleCredits("4"),
                    new ModuleSemester("Y1S2")),
            new CapEntry(new ModuleCode("CS2030"), new ModuleGrade("B+"), new ModuleCredits("4"),
                new ModuleSemester("Y1S2")),
            new CapEntry(new ModuleCode("CS2101"), new ModuleGrade("B+"), new ModuleCredits("4"),
                new ModuleSemester("Y2S1"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (CapEntry sampleCapEntry : getSampleCapEntryList()) {
            sampleAb.addCapEntry(sampleCapEntry);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
