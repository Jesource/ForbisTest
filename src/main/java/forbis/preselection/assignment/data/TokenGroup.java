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
                + getTokensSeparatedWithSpace();
    }

    private String getTokensSeparatedWithSpace() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String token : tokens) {
            stringBuilder.append(token);
            stringBuilder.append(" ");
        }

        return stringBuilder.toString().trim();
    }

    public void addToken(String token) {
        if (token != null) {
            tokens.add(token);
        }
    }
}
