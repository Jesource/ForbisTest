package forbis.preselection.assignment.web.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageServiceTest {
    MainPageService service = new MainPageService();


    @Test
    public void givenValidAndNonValidTokens_whenFilteringTokens_thenOnlyValidTokensExpected() {
        List listOfTokens = List.of("valid", "n0t_valid", "good");

        assertEquals(List.of("valid", "good"), service.filterTokens(listOfTokens));
    }


    @Test
    public void givenEmptyString_whenProcessingTextInput_thenEmptyListExpected() {
        String input = "";
        ArrayList output = new ArrayList<>();

        service.processTextInput(input, output);

        assertEquals(List.of(), output);
    }


    @Test
    public void givenNull_whenProcessingTextInput_thenEmptyListExpected() {
        ArrayList output = new ArrayList<>();

        service.processTextInput(null, output);

        assertEquals(List.of(), output);
    }
}