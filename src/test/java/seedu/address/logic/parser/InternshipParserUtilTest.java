package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.Remark;
import seedu.address.model.internship.Role;


class InternshipParserUtilTest {

    @Test
    void parseRemark_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> InternshipParserUtil.parseRemark(null));
    }

    @Test
    void parseRemarks_validValue_success() {
        // whitespace only
        assertEquals(new Remark(""), InternshipParserUtil.parseRemark(" "));

        // removes leading and trailing whitespaces
        assertEquals(new Remark("This is a remark"), InternshipParserUtil.parseRemark(" This is a remark "));

        // multiple words
        assertEquals(new Remark("This is a remark"), InternshipParserUtil.parseRemark("This is a remark"));
    }

    @Test
    public void parseOptionalRole_emptyOptional_returnsUnknown() throws ParseException {
        assertEquals(new Role("Unknown Role"), InternshipParserUtil.parseOptionalRole(Optional.empty()));
    }

    @Test
    public void parseOptionalRole_presentOptionalBlank_returnsUnknown() throws ParseException {
        assertEquals(new Role("Unknown Role"), InternshipParserUtil.parseOptionalRole(Optional.empty()));
    }

    @Test
    public void parseOptionalLocation_emptyOptional_returnsUnknown() throws ParseException {
        assertEquals(new Location("UNKNOWN"), InternshipParserUtil.parseOptionalLocation(Optional.empty()));
    }

    @Test
    public void parseOptionalLocation_presentOptionalBlank_returnsUnknown() throws ParseException {
        assertEquals(new Location("UNKNOWN"), InternshipParserUtil.parseOptionalLocation(Optional.empty()));
    }

    @Test
    void anyPrefixesPresent_noPrefixesPresent_false() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = "No prefixes here, just a regular string";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertFalse(InternshipParserUtil.anyPrefixesPresent(argMultimap, prefixes));
    }

    @Test
    void anyPrefixesPresent_onePrefixPresentWithValue_true() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = " " + PREFIX_COMPANY + " Google random string random string Software Engineer";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertTrue(InternshipParserUtil.anyPrefixesPresent(argMultimap, prefixes));
    }

    @Test
    void anyPrefixesPresent_onePrefixPresentWithoutValue_true() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = " " + PREFIX_COMPANY;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertTrue(InternshipParserUtil.anyPrefixesPresent(argMultimap, prefixes));
    }

    @Test
    void prefixesPresentAreNotEmpty_noPrefixesPresent_true() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = "No prefixes here, just a regular string";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertTrue(InternshipParserUtil.prefixesPresentAreNotEmpty(argMultimap, prefixes));
    }

    @Test
    void prefixesPresentAreNotEmpty_onePrefixPresentWithValue_true() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = " " + PREFIX_COMPANY + " Google random string random string Software Engineer";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertTrue(InternshipParserUtil.prefixesPresentAreNotEmpty(argMultimap, prefixes));
    }

    @Test
    void prefixesPresentAreNotEmpty_onePrefixPresentWithNoValue_false() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = " " + PREFIX_COMPANY;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertFalse(InternshipParserUtil.prefixesPresentAreNotEmpty(argMultimap, prefixes));
    }

    @Test
    void prefixesPresentAreNotEmpty_onePrefixPresentWithEmptyValue_false() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = " " + PREFIX_COMPANY + " ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertFalse(InternshipParserUtil.prefixesPresentAreNotEmpty(argMultimap, prefixes));
    }

    @Test
    void prefixesPresentAreNotEmpty_onePrefixPresentWithValueOnePrefixPresentWithEmptyValue_false() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = " " + PREFIX_COMPANY + " Google " + PREFIX_ROLE + " ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertFalse(InternshipParserUtil.prefixesPresentAreNotEmpty(argMultimap, prefixes));
    }

    @Test
    void arePrefixesPresent_noPrefixesPresent_false() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = "No prefixes here, just a regular string";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertFalse(InternshipParserUtil.arePrefixesPresent(argMultimap, prefixes));
    }

    @Test
    void arePrefixesPresent_somePrefixesPresent_false() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = " " + PREFIX_COMPANY + " Google";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertFalse(InternshipParserUtil.arePrefixesPresent(argMultimap, prefixes));
    }

    @Test
    void arePrefixesPresent_allPrefixesPresent_true() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = " " + PREFIX_COMPANY + " Google " + PREFIX_ROLE + " Software Engineer";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertTrue(InternshipParserUtil.arePrefixesPresent(argMultimap, prefixes));
    }

    @Test
    void arePrefixesPresent_prefixesPresentButEmpty_true() {
        Prefix[] prefixes = {PREFIX_COMPANY, PREFIX_ROLE};
        String argString = " " + PREFIX_COMPANY + " " + PREFIX_ROLE + " ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argString, prefixes);
        assertTrue(InternshipParserUtil.arePrefixesPresent(argMultimap, prefixes));
    }
}
