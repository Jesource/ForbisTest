package forbis.preselection.assignment.utils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class TextProcessingUtil {

    public static List<String> breakTextToTokens(String text) {
        Scanner scanner = new Scanner(removeAllPunctuationMarks(text));
        ArrayList<String> tokens = new ArrayList<>();

        while (scanner.hasNext()) {
            tokens.add(scanner.next());
        }
        scanner.close();

        return tokens;
    }

    static String removeAllPunctuationMarks(String text) {
        return text.replaceAll(",", "") // replace commas
                .replaceAll("[.]", "") // remove full stops
                .replaceAll("[?]", "") // remove question mark
                .replaceAll("!", "") // remove exclamation mark
                .replaceAll(";", "") // remove semicolon
                .replaceAll(":", "") // remove colon
                .replaceAll("'", "") // remove apostrophe
                .replaceAll("[(]", "") // remove round bracket
                .replaceAll("[)]", "") // remove round bracket
                .replaceAll("\\[", "") // remove square bracket
                .replaceAll("]", "") // remove square bracket
                .replaceAll("\"", "") // remove backslash
                .replaceAll("-", "") // remove hyphen
                .replaceAll("—", "") // remove dash
                .replaceAll("/", "") // remove slash
                .replaceAll("@", "") // remove at sign
                .replaceAll("\\{", "") // remove brace
                .replaceAll("}", "") // remove brace
                .replaceAll("\\*", "") // remove asterisk
                .trim();
    }

    static boolean checkIfTokenContainsOnlyLatinLetters_withRegex(String token) {
        return token.toLowerCase().matches("[a-zA-Z]+");
    }

    public static boolean checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics(String token) {
        for (char symbol : token.toLowerCase().toCharArray()) {
            if (!('a' <= symbol && symbol <= 'z')) {
                return false;
            }
        }

        return true;
    }

    private TextProcessingUtil() {
    }
}
