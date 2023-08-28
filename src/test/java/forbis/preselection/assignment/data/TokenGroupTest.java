package forbis.preselection.assignment.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenGroupTest {

    @Test
    void givenTokens_whenPopulatingTokenGroup_thenWellFormattedStringIsExpected() {
        TokenGroup group = new TokenGroup('a');

        group.addToken("Carla");
        group.addToken("Andorra");
        group.addToken("panda");
        group.addToken("pizza");

        assertEquals(
                "a 4 Carla Andorra panda pizza",
                group.getFormattedResultString()
        );
    }
}