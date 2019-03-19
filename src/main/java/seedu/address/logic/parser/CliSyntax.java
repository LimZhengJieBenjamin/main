package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_MODULECODE = new Prefix("mc/");
    public static final Prefix PREFIX_MODULENAME = new Prefix("mn/");
    public static final Prefix PREFIX_HOMEWORK = new Prefix("hw/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("p/");

    public static final Prefix PREFIX_MODULEGRADE = new Prefix("g/");
    public static final Prefix PREFIX_MODULARCREDITS = new Prefix("mc/");

}
