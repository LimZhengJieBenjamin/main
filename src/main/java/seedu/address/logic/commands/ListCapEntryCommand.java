package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CAP_ENTRIES;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Lists all cap entries in the Cap Manager to the user.
 */
public class ListCapEntryCommand extends Command {

    public static final String COMMAND_WORD = "listCapEntry";

    public static final String MESSAGE_SUCCESS = "Listed all cap entries";


    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredCapEntryList(PREDICATE_SHOW_ALL_CAP_ENTRIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
