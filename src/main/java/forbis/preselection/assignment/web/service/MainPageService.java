package forbis.preselection.assignment.web.service;

import forbis.preselection.assignment.data.ResultRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static forbis.preselection.assignment.utils.TextProcessingUtil.breakTextToTokens;
import static forbis.preselection.assignment.utils.TextProcessingUtil.checkIfTokenContainsOnlyLatinLetters_withASCIISpecifics;
import static forbis.preselection.assignment.utils.WritingToFileUtil.writeResultRecordToFile;

@Service
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
}
