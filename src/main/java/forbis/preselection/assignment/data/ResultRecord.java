package forbis.preselection.assignment.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultRecord {
    private Map<Character, TokenGroup> filteredTokens = new HashMap<>();

    public ResultRecord(List<String> initialListOfTokens) {
        if (initialListOfTokens == null || initialListOfTokens.isEmpty()) {
            return;
        }

        for (String token : initialListOfTokens) {
            addToken(token);
        }
    }


    public void addToken(String token) {
        char lastSymbolOfToken = getLastSymbolOfTokenInLowerCase(token);

        addTokenToAppropriateTokenGroup(lastSymbolOfToken, token);
    }

    private void createNewTokenGroupIfNeeded(char lastSymbolOfToken) {
        if (!checkIfTokenGroupAlreadyExists(lastSymbolOfToken)) {
            filteredTokens.put(lastSymbolOfToken, new TokenGroup(lastSymbolOfToken));
        }
    }

    private void addTokenToAppropriateTokenGroup(char lastSymbolOfToken, String token) {
        createNewTokenGroupIfNeeded(lastSymbolOfToken);
        filteredTokens.get(lastSymbolOfToken).addToken(token);
    }

    private boolean checkIfTokenGroupAlreadyExists(char token) {
        return filteredTokens.containsKey(token);
    }


    private char getLastSymbolOfTokenInLowerCase(String token) {
        char[] chars = token.toLowerCase().toCharArray();

        return chars[chars.length - 1];
    }

    public List<String> getFormattedTokenGroupsAsStrings() {
        Object[] filteredKeys = filteredTokens.keySet().stream().sorted().toArray();
        List<String> output = new ArrayList<>();

        for (Object key : filteredKeys) {
            output.add(filteredTokens.get(key).getFormattedResultString());
        }

        return output;
    }
}
