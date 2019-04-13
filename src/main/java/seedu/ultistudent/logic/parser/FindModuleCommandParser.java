package seedu.ultistudent.logic.parser;

import static seedu.ultistudent.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.ultistudent.logic.commands.FindModuleCommand;
import seedu.ultistudent.logic.parser.exceptions.ParseException;
import seedu.ultistudent.model.homework.ModuleCodeContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object.
 */
public class FindModuleCommandParser implements Parser<FindModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindModuleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindModuleCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
