package forbis.preselection.assignment.web.service;

import forbis.preselection.assignment.data.ResultRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import static forbis.preselection.assignment.utils.TextProcessingUtil.breakTextToTokens;
import static forbis.preselection.assignment.utils.TextProcessingUtil.checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics;
import static forbis.preselection.assignment.utils.WritingToFileUtil.writeResultRecordToFile;

@Service
@Slf4j
public class MainPageService {
    public List<String> proceedTextInput(String inputText) {
        List<String> tokens = breakTextToTokens(inputText);
        ResultRecord result = new ResultRecord(filterTokens(tokens));
        writeResultRecordToFile(result);

        return result.getFormattedTokenGroupsAsStrings();
    }


    private List<String> filterTokens(List<String> tokens) {
        List<String> filteredTokens = new ArrayList<>();

        for (String token : tokens) {
            if (checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics(token)) {
                filteredTokens.add(token);
            }
        }

        return filteredTokens;
    }

    public List<String> proceedInput(String inputText, MultipartFile inputFile) {
        List<String> validTokens = new ArrayList<>();

        processTextInput(inputText, validTokens);
        processFileInput(inputFile, validTokens);
        ResultRecord result = new ResultRecord(validTokens);
        writeResultRecordToFile(result);

        return result.getFormattedTokenGroupsAsStrings();
    }

    private void processFileInput(MultipartFile inputFile, Collection<String> validTokens) {
        if (!inputFile.isEmpty()) {
            validTokens.addAll(getValidTokensFromFile(inputFile));
        }
    }

    private void processTextInput(String inputText, Collection<String> validTokens) {
        if (!inputText.isBlank()) {
            validTokens.addAll(getValidTokensFromText(inputText));
        }
    }

    private Collection<String> getValidTokensFromFile(MultipartFile inputFile) {
        try {
            List<String> tokens = new ArrayList<>();

            String fileContents = new String(inputFile.getBytes(), StandardCharsets.UTF_8);
            Scanner scanner = new Scanner(fileContents);
            while (scanner.hasNext()) {
                tokens.add(scanner.next());
            }

            return filterTokens(tokens);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<String> getValidTokensFromText(String inputText) {
        return filterTokens(breakTextToTokens(inputText));
    }
}
