package seedu.ultistudent.model.cap;

import static seedu.ultistudent.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.ultistudent.model.modulecode.ModuleCode;
import seedu.ultistudent.model.tag.Tag;

/** Represents a CAP entry for the CAP Manager in the ultistudent book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class CapEntry {

    // Static fields
    private static double capScore;
    private static int totalScore;
    private static int totalModuleCredits;
    // Identity fields
    private ModuleCode moduleCode;
    private ModuleGrade moduleGrade;
    private ModuleCredits moduleCredits;
    private ModuleSemester moduleSemester;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public CapEntry(ModuleCode moduleCode, ModuleGrade moduleGrade, ModuleCredits moduleCredits,
                    ModuleSemester moduleSemester, Set<Tag> tags) {
        requireAllNonNull(moduleCode, moduleGrade, moduleCredits, moduleSemester, tags);
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
        this.moduleCredits = moduleCredits;
        this.moduleSemester = moduleSemester;
        updateCap(moduleGrade, moduleCredits);
        this.tags.addAll(tags);
    }

    private void updateCap(ModuleGrade moduleGrade, ModuleCredits moduleCredits) {
        this.totalScore += moduleGrade.getScore();
        this.totalModuleCredits += moduleCredits.getValue();
        this.capScore = this.totalScore / this.totalModuleCredits;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public ModuleGrade getModuleGrade() {
        return moduleGrade;
    }

    public ModuleCredits getModuleCredits() {
        return moduleCredits;
    }

    public ModuleSemester getModuleSemester() {
        return moduleSemester;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both cap entries of the same module code have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameCapEntry(CapEntry otherCapEntry) {
        if (otherCapEntry == this) {
            return true;
        }

        return otherCapEntry != null
                && otherCapEntry.getModuleCode().equals(getModuleCode());
//                && (otherCapEntry.getModuleGrade().equals(getModuleGrade())
//                || otherCapEntry.getModuleCredits().equals(getModuleGrade()));
    }

    /**
     * Returns true if both capEntry have the same identity and data fields.
     * This defines a stronger notion of equality between two capEntry.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CapEntry)) {
            return false;
        }

        CapEntry otherCapEntry = (CapEntry) other;
        return otherCapEntry.getModuleCode().equals(getModuleCode())
                && otherCapEntry.getModuleGrade().equals(getModuleGrade())
                && otherCapEntry.getModuleCredits().equals(getModuleCredits())
                && otherCapEntry.getModuleSemester().equals(getModuleSemester())
                && otherCapEntry.getTags().equals(getTags());
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(moduleCode)
                .append("; ")
                .append(moduleGrade)
                .append("; ")
                .append(moduleCredits)
                .append("MC; ")
                .append(moduleSemester)
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(moduleCode, moduleGrade, moduleCredits, moduleSemester, tags);
    }
}
