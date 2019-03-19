package seedu.address.model.cap;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.cap.exceptions.DuplicateCapEntryException;
import seedu.address.model.cap.exceptions.CapEntryNotFoundException;

/**
 * A list of capEntry that enforces uniqueness between its elements and does not allow nulls.
 * A capEntry is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */

public class UniqueCapEntryList implements Iterable<CapEntry> {

    private final ObservableList<CapEntry> internalList = FXCollections.observableArrayList();
    private final ObservableList<CapEntry> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(CapEntry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameCapEntry);
    }

    /**
     * Adds a capEntry to the list.
     * The capEntry must not already exist in the list.
     */
    public void add(CapEntry toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCapEntryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setCapEntry(CapEntry target, CapEntry editedCapEntry) {
        requireAllNonNull(target, editedCapEntry);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CapEntryNotFoundException();
        }

        if (!target.isSameCapEntry(editedCapEntry) && contains(editedCapEntry)) {
            throw new DuplicateCapEntryException();
        }

        internalList.set(index, editedCapEntry);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(CapEntry toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CapEntryNotFoundException();
        }
    }

    public void setCapEntryList(UniqueCapEntryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code capEntries}.
     * {@code capEntries} must not contain duplicate persons.
     */
    public void setCapEntryList(List<CapEntry> capEntryList) {
        requireAllNonNull(capEntryList);
        if (!capEntryListIsUnique(capEntryList)) {
            throw new DuplicateCapEntryException();
        }

        internalList.setAll(capEntryList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<CapEntry> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<CapEntry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueCapEntryList // instanceof handles nulls
                && internalList.equals(((UniqueCapEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean capEntryListIsUnique(List<CapEntry> capEntryList) {
        for (int i = 0; i < capEntryList.size() - 1; i++) {
            for (int j = i + 1; j < capEntryList.size(); j++) {
                if (capEntryList.get(i).isSameCapEntry(capEntryList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

