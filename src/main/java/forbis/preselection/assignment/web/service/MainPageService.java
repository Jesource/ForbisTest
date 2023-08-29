package forbis.preselection.assignment.web.service;

import forbis.preselection.assignment.data.ResultRecord;
import forbis.preselection.assignment.dto.ResultRecordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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


    private void readFile() throws IOException {
        File file = new File("results/out1");
        System.out.println("Does file exist: " + file.exists());
        if (file.exists()) {
            Scanner scanner = new Scanner(file);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
            scanner.close();
            System.out.println("End of file\n");

            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
            System.out.println("End of file\n");

        } else {
            System.out.println("File does not exist");
        }
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

//        do {
//            line = reader.readLine();
//            rawLines.add(line);
//        } while (line != null);
        while (line != null) {
            rawLines.add(line);
            line = reader.readLine();
        }
        reader.close();

        return rawLines;
    }
}
