package seedu.address.model.homework;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a homework entry in the address book.
 */
public class Homework {

    public static final String MESSAGE_CONSTRAINTS = "Homework should be inputted as MODULECODE; "
            + "HOMEWORKNAME; DEADLINE";

    private ModuleCode moduleCode;
    private HomeworkName homeworkName;
    private Date deadline;

    public Homework (ModuleCode moduleCode, HomeworkName homeworkName, Date deadline) {
        requireAllNonNull(moduleCode, homeworkName, deadline);

        this.moduleCode = moduleCode;
        this.homeworkName = homeworkName;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return moduleCode + "; " + homeworkName + "; " + deadline + "; ";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Homework // instanceof handles nulls
                && moduleCode.equals(((Homework) other).moduleCode)
                && homeworkName.equals(((Homework) other).homeworkName)
                && deadline.equals(((Homework) other).deadline));
    }

    @Override
    public int hashCode() {
        String value = moduleCode + " " + homeworkName + " " + deadline;
        return value.hashCode();
    }

}
