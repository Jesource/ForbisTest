package forbis.preselection.assignment.web.service;

import forbis.preselection.assignment.data.ResultRecord;
import forbis.preselection.assignment.dto.ResultRecordDto;
import forbis.preselection.assignment.web.models.InputModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    List<String> filterTokens(List<String> tokens) {
        List<String> filteredTokens = new ArrayList<>();

        for (String token : tokens) {
            if (checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics(token)) {
                filteredTokens.add(token);
            }
        }

        return filteredTokens;
    }

    private void processFileInput(MultipartFile inputFile, Collection<String> validTokens) {
        if (inputFile != null && !inputFile.isEmpty()) {
            try {
                validTokens.addAll(getValidTokensFromFile(inputFile));
            } catch (IOException e) {
                log.warn("Failed to proceed file '{}'", inputFile.getOriginalFilename());
            }
        }
    }

    void processTextInput(String inputText, Collection<String> validTokens) {
        if (inputText != null && !inputText.isBlank()) {
            validTokens.addAll(getValidTokensFromText(inputText));
        }
    }

    private Collection<String> getValidTokensFromFile(MultipartFile inputFile) throws IOException {
        String fileContents = new String(inputFile.getBytes(), StandardCharsets.UTF_8);
        List<String> tokens = breakTextToTokens(fileContents);

        return filterTokens(tokens);
    }

    private Collection<String> getValidTokensFromText(String inputText) {
        return filterTokens(breakTextToTokens(inputText));
    }


    public Collection<ResultRecordDto> getPreviousResults() throws IOException {
        File savedResultsFile = new File("results/out1");

        if (!savedResultsFile.exists()) {
            return null;
        }
        BufferedReader reader = new BufferedReader(new FileReader(savedResultsFile));
        List<String> rawRecords = getLinensFromFile(reader);

        return mapRawRecordsToResultRecordDto(rawRecords);
    }

    private Collection<ResultRecordDto> mapRawRecordsToResultRecordDto(List<String> rawRecords) {
        List<ResultRecordDto> resultRecordDtos = new ArrayList<>();

        for (String record : rawRecords) {
            resultRecordDtos.add(new ResultRecordDto(record));
        }

        return resultRecordDtos;
    }

    private List<String> getLinensFromFile(BufferedReader reader) throws IOException {
        List<String> rawLines = new ArrayList<>();
        String line = reader.readLine();

        while (line != null) {
            rawLines.add(line);
            line = reader.readLine();
        }
        reader.close();

        return rawLines;
    }

    public List<String> proceedInput(InputModel inputModel, char separator) {
        List<String> validTokens = new ArrayList<>();

        processTextInput(inputModel.getTextInput1(), validTokens);
        processTextInput(inputModel.getTextInput2(), validTokens);
        processFileInput(inputModel.getFile1(), validTokens);
        processFileInput(inputModel.getFile2(), validTokens);
        ResultRecord result = new ResultRecord(validTokens);
        writeResultRecordToFile(result);

        return result.getFormattedTokenGroupsAsStrings(separator);
    }
}
