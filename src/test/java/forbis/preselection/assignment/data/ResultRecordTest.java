package forbis.preselection.assignment.data;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultRecordTest {
    @Test
    @org.junit.Test
    public void givenOneValidToken_whenCallingMethodForRetrievingFormattedTokenGroups_thenOneWellFormattedStringIsReturned() {
        ResultRecord record = new ResultRecord(List.of("hello"));

        assertEquals(
                Lists.list("o 1 hello"),    // expected
                record.getFormattedTokenGroupsAsStrings()   // actual
        );
    }

    @Test
    @org.junit.Test
    public void givenNewTokenForNonExistingGroup_whenRecordHasExistingTokenGroup_thenNewTokenGroupCreatedAndSortedAlphabetically() {
        ResultRecord record = new ResultRecord(List.of("hello"));

        record.addToken("lambda");

        Assertions.assertEquals(
                Lists.list("a 1 lambda", "o 1 hello"),
                record.getFormattedTokenGroupsAsStrings()
        );
    }

    @Test
    @org.junit.Test
    public void givenNull_whenCreatingNewResultRecord_thenEmptyArrayOfFormattedStringsReturned() {
        ResultRecord record = new ResultRecord(null);

        assertEquals(List.of(), record.getFormattedTokenGroupsAsStrings());
    }

    @Test
    @org.junit.Test
    public void givenEmptyList_whenCreatingNewResultRecord_thenEmptyArrayOfFormattedStringsReturned() {
        ResultRecord record = new ResultRecord(List.of());

        assertEquals(List.of(), record.getFormattedTokenGroupsAsStrings());
    }


    @Test
    @org.junit.Test
    public void givenTokenGroupsWithOneToken_whenCallingForFormattedStrings_thenFormattedStringsWithNoSeparatorReturned() {
        ResultRecord record = new ResultRecord(List.of("hello"));

        record.addToken("lambda");

        Assertions.assertEquals(
                Lists.list("a 1 lambda", "o 1 hello"),
                record.getFormattedTokenGroupsAsStrings(',')
        );
    }


    @Test
    @org.junit.Test
    public void givenSeparator_whenCallingForFormattedStrings_thenTokensSeparatedWithProvidedSeparatorExpected() {
        ResultRecord record = new ResultRecord(List.of("hello", "marshmello"));

        assertEquals(
                List.of("o 2 hello,marshmello"),
                record.getFormattedTokenGroupsAsStrings(',')
        );
    }
}