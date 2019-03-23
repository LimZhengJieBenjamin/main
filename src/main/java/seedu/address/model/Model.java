package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.cap.CapEntry;
import seedu.address.model.homework.Homework;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<CapEntry> PREDICATE_SHOW_ALL_CAP_ENTRIES = unused -> true;
    Predicate<Homework> PREDICATE_SHOW_ALL_HOMEWORK = unused -> true;


    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' UltiStudent file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' UltiStudent file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces UltiStudent data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the UltiStudent.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the UltiStudent.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the UltiStudent.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the UltiStudent.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the UltiStudent.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if the model has previous UltiStudent states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone UltiStudent states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's UltiStudent to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's UltiStudent to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current UltiStudent state for undo/redo.
     */
    void commitAddressBook();

    /**
     * Selected person in the filtered person list.
     * null if no person is selected.
     */
    ReadOnlyProperty<Person> selectedPersonProperty();

    /**
     * Returns the selected person in the filtered person list.
     * null if no person is selected.
     */
    Person getSelectedPerson();

    /**
     * Sets the selected person in the filtered person list.
     */
    void setSelectedPerson(Person person);

    //=========== Cap Entry ===========================================================================
    /**
     * Returns true if a cap entry with the same identity as {@code capEntry} exists in the UltiStudent.
     */
    boolean hasCapEntry(CapEntry capEntry);

    /**
     * Deletes the given cap entry.
     * The cap entry must exist in the UltiStudent.
     */
    void deleteCapEntry(CapEntry target);

    /**
     * Adds the given cap entry.
     * {@code codeEntry} must not already exist in the UltiStudent.
     */
    void addCapEntry(CapEntry capEntry);

    /**
     * Replaces the given cap entry {@code target} with {@code editedCapEntry}.
     * {@code target} must exist in the UltiStudent.
     * The cap entry identity of {@code editedCapEntry} must not be the same as another existing cap entry
     * in the UltiStudent.
     */
    void setCapEntry(CapEntry target, CapEntry editedCapEntry);

    /** Returns an unmodifiable view of the filtered cap entry list */
    ObservableList<CapEntry> getFilteredCapEntryList();

    /**
     * Updates the filter of the filtered cap entry list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCapEntryList(Predicate<CapEntry> predicate);

    /**
     * Selected cap entry in the filtered cap entry list.
     * null if no cap entry is selected.
     */
    ReadOnlyProperty<CapEntry> selectedCapEntryProperty();

    /**
     * Returns the selected cap entry in the filtered cap entry list.
     * null if no cap entry is selected.
     */
    CapEntry getSelectedCapEntry();

    /**
     * Sets the selected cap entry in the filtered cap entry list.
     */
    void setSelectedCapEntry(CapEntry capEntry);

    //=========== Homework ===========================================================================
    /**
     * Returns true if a homework with the same identity as {@code homework} exists in the UltiStudent.
     */
    boolean hasHomework(Homework homework);

    /**
     * Deletes the given homework.
     * The homework must exist in the UltiStudent.
     */
    void deleteHomework(Homework target);

    /**
     * Adds the given homework.
     * {@code homework} must not already exist in the UltiStudent.
     */
    void addHomework(Homework homework);

    /**
     * Replaces the given cap entry {@code target} with {@code editedHomework}.
     * {@code target} must exist in the UltiStudent.
     * The homework identity of {@code editedHomework} must not be the same as another existing cap entry
     * in the UltiStudent.
     */
    void setHomework(Homework target, Homework editedHomework);

    /** Returns an unmodifiable view of the filtered homework list */
    ObservableList<Homework> getFilteredHomeworkList();

    /**
     * Updates the filter of the filtered homework list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredHomeworkList(Predicate<Homework> predicate);

    /**
     * Selected homework in the filtered cap entry list.
     * null if no homework is selected.
     */
    ReadOnlyProperty<Homework> selectedHomeworkProperty();

    /**
     * Returns the selected cap entry in the filtered homework list.
     * null if no homework is selected.
     */
    Homework getSelectedHomework();

    /**
     * Sets the selected homework in the filtered homework list.
     */
    void setSelectedHomework(Homework homework);
}
