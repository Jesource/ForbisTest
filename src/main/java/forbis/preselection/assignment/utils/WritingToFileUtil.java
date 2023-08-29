package forbis.preselection.assignment.utils;

import forbis.preselection.assignment.data.ResultRecord;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class WritingToFileUtil {
    private static String BASE_FOLDER = "results" + System.getProperty("file.separator");


    public static void writeResultRecordToFile(ResultRecord result) {
        try {
            FileWriter fileWriter = getPreparedFileForWriting();
            writeResultToFile(fileWriter, result);
        } catch (IOException e) {
            log.error("Failed to save results: {}", e.getMessage());
        }
    }


    private static void writeResultToFile(FileWriter fileWriter, ResultRecord result) throws IOException {
        JSONObject document = new JSONObject();

        document.put("timestamp", LocalDateTime.now());
        document.put("formatted_result", result.getFormattedTokenGroupsAsStrings());
        fileWriter.append(document + "\n").flush();
        fileWriter.close();
    }


    private static FileWriter getPreparedFileForWriting() throws IOException {
        createBaseFolderIfNeeded();

        return new FileWriter(BASE_FOLDER + "out1", true);
    }


    private static void createBaseFolderIfNeeded() {
        File baseFolder = new File(BASE_FOLDER);
        if (!baseFolder.exists()) {
            baseFolder.mkdir();
            log.info("Folder {} was created", BASE_FOLDER);
        }
    }

    private WritingToFileUtil() {
    }
}
