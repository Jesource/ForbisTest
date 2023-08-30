package forbis.preselection.assignment.utils;



import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static forbis.preselection.assignment.utils.TextProcessingUtil.*;
import static org.junit.jupiter.api.Assertions.*;

public class TextProcessingUtilTest {

    @Test
    @org.junit.Test
    public void givenTextWithTokens_whenBreakingTextToListOfTokens_thenOnlyPunctuationMarksAreNotReturnedAsTokens() {
        String inputText = "Hello, my name is Andrej. J. . ? ! , ";
        List<String> expected = Arrays.asList("Hello", "my", "name", "is", "Andrej", "J");

        List<String> output = breakTextToTokens(inputText);

        assertEquals(expected, output);
    }


    @Test
    @org.junit.Test
    public void givenStringWithPunctuationMarks_whenFilteringPunctuationMarks_thenStringWithNoPunctuationMarksReturned() {
        String inputText = "/Hello', [my]; (name) is:- @{\"Andrej\"}*. J. . ? ! ,";
        String expected = "Hello my name is Andrej J";

        String output = removeAllPunctuationMarks(inputText);

        assertEquals(expected, output);
    }


    @Test
    @org.junit.Test
    public void checkIfTokenContainsOnlyLatinLetters_withRegexTest() {
        assertAll(
                () -> assertTrue(checkIfTokenContainsOnlyLatinLetters_withRegex("aBc")),
                () -> assertFalse(checkIfTokenContainsOnlyLatinLetters_withRegex("aBc1")),
                () -> assertFalse(checkIfTokenContainsOnlyLatinLetters_withRegex("привет")),
                () -> assertFalse(checkIfTokenContainsOnlyLatinLetters_withRegex("ąžuolynas")),
                () -> assertFalse(checkIfTokenContainsOnlyLatinLetters_withRegex("ąsotis"))
        );
    }


    @Test
    @org.junit.Test
    public void checkIfTokenContainsOnlyLatinLetters_withASCIISpecificsTest() {
        assertAll(
                () -> assertTrue(checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics("aBc")),
                () -> assertTrue(checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics("az")),
                () -> assertTrue(checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics("AZ")),
                () -> assertFalse(checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics("aBc1")),
                () -> assertFalse(checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics("z3d")),
                () -> assertFalse(checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics("привет"))
        );
    }
}