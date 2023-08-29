package forbis.preselection.assignment.data;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultRecordTest {
    @Test
    void givenOneValidToken_whenCallingMethodForRetrievingFormattedTokenGroups_thenOneWellFormattedStringIsReturned() {
        ResultRecord record = new ResultRecord(List.of("hello"));

        assertEquals(
                Lists.list("o 1 hello"),    // expected
                record.getFormattedTokenGroupsAsStrings()   // actual
        );
    }

    @Test
    void givenNewTokenForNonExistingGroup_whenRecordHasExistingTokenGroup_thenNewTokenGroupCreatedAndSortedAlphabetically() {
        ResultRecord record = new ResultRecord(List.of("hello"));

        record.addToken("lambda");

        Assertions.assertEquals(
                Lists.list("a 1 lambda", "o 1 hello"),
                record.getFormattedTokenGroupsAsStrings()
        );
    }

    @Test
    void givenNull_whenCreatingNewResultRecord_thenEmptyArrayOfFormattedStringsReturned() {
        ResultRecord record = new ResultRecord(null);

        assertEquals(List.of(), record.getFormattedTokenGroupsAsStrings());
    }

    @Test
    void givenEmptyList_whenCreatingNewResultRecord_thenEmptyArrayOfFormattedStringsReturned() {
        ResultRecord record = new ResultRecord(List.of());

        assertEquals(List.of(), record.getFormattedTokenGroupsAsStrings());
    }
}