package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULEGRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCapEntryCommand;
import seedu.address.logic.commands.EditCapEntryCommand.EditCapEntryDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCapEntryCommand object
 */
public class EditCapEntryCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCapEntryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULECODE, PREFIX_MODULEGRADE,
                PREFIX_MODULECREDITS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditCapEntryCommand.MESSAGE_USAGE), pe);
        }

        EditCapEntryDescriptor editCapEntryDescriptor = new EditCapEntryDescriptor();
        if (argMultimap.getValue(PREFIX_MODULECODE).isPresent()) {
            editCapEntryDescriptor.setModuleCode(ParserUtil.parseModuleCode(argMultimap
                    .getValue(PREFIX_MODULECODE).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULEGRADE).isPresent()) {
            editCapEntryDescriptor.setModuleGrade(ParserUtil.parseModuleGrade(argMultimap
                    .getValue(PREFIX_MODULEGRADE).get()));
        }
        if (argMultimap.getValue(PREFIX_MODULECREDITS).isPresent()) {
            editCapEntryDescriptor.setModuleCredits(ParserUtil.parseModuleCredits(argMultimap
                    .getValue(PREFIX_MODULECREDITS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editCapEntryDescriptor::setTags);

        if (!editCapEntryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCapEntryCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCapEntryCommand(index, editCapEntryDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
