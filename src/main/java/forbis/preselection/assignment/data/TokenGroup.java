package forbis.preselection.assignment.data;

import java.util.ArrayList;
import java.util.List;

public class TokenGroup {
    private char groupSymbol;
    private List<String> tokens;

    public TokenGroup(char groupSymbol) {
        this.groupSymbol = groupSymbol;
        tokens = new ArrayList<>();
    }

    public String getFormattedResultString() {
        return groupSymbol + " "
                + tokens.size() + " "
                + getTokensSeparatedWithProvidedSeparator(' ');
    }

    public String getFormattedResultString(char separator) {
        return groupSymbol + " "
                + tokens.size() + " "
                + getTokensSeparatedWithProvidedSeparator(separator);
    }

    private String getTokensSeparatedWithProvidedSeparator(char separator) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String token : tokens) {
            stringBuilder.append(token);
            stringBuilder.append(separator);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public void addToken(String token) {
        if (token != null) {
            tokens.add(token);
        }
    }
}
