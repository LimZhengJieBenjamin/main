package seedu.ultistudent.model.modulecode;

import static java.util.Objects.requireNonNull;
import static seedu.ultistudent.commons.util.AppUtil.checkArgument;

/**
 * Represents modulecode in the UltiStudent which is used in Homework entry and the CapEntry.
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS = "Module codes should begin with at least two capital letters "
            + "followed by four digits. May end with an optional capital letter at the back";
    public static final String VALIDATION_REGEX = "([a-zA-Z]{2,3})(\\d{4})([a-zA-Z]{1})?";
    public final String value;

    /**
     * Constructs a {@code ModuleCode}.
     * @param moduleCode
     */
    public ModuleCode (String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        value = moduleCode.toUpperCase();
    }

    /**
     * Returns true if it is a valid ModuleCode.
     */
    public static boolean isValidModuleCode(String test) {
        return test.toUpperCase().matches(VALIDATION_REGEX);
    }

    /**
     * Returns the value of the ModuleCode.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && value.equals(((ModuleCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
