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

import static forbis.preselection.assignment.utils.TextProcessingUtil.breakTextToTokens;
import static forbis.preselection.assignment.utils.TextProcessingUtil.checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics;
import static forbis.preselection.assignment.utils.WritingToFileUtil.writeResultRecordToFile;

@Service
@Slf4j
public class MainPageService {
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
            String fileContents = new String(inputFile.getBytes(), StandardCharsets.UTF_8);
            List<String> tokens = breakTextToTokens(fileContents);

            return filterTokens(tokens);
        } catch (IOException e) {
            log.error("Failed to read file contents: {}", e.getMessage());

            return null;
        }
    }

    private Collection<String> getValidTokensFromText(String inputText) {
        return filterTokens(breakTextToTokens(inputText));
    }
}
