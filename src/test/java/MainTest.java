import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {


    @Test
    public void sumNumbers_whenAllPositive_returnsCorrected() {
        String[] list = {"1", "5", "7"};

        Integer result =  Main.sumNumbers(list);

        assertEquals(result, 13);
    }

    @Test
    public void sumNumbers_whenTwoAreTruncatedThousand_returnsCorrected() {
        String[] list = {"1444", "5667", "7"};

        Integer result = Main.sumNumbers(list);

        assertEquals(result, 7);
    }

    @Test
    public void sumNumbers_whenAllAreTruncatedThousand_returnsCorrected() {
        String[] list = {"1444", "5667", "3334"};

        Integer result =  Main.sumNumbers(list);

        assertEquals(result, 0);
    }

    @Test
    public void truncateThousand_below_returnTrue() {
        int random = ThreadLocalRandom.current().nextInt(999);

        boolean result = Main.truncateThousand(random);

        assertEquals(result, true);

    }

    @Test
    public void truncateThousand_above_returnFalse() {
        int random = ThreadLocalRandom.current().nextInt(999);
        boolean result = Main.truncateThousand(random + 1001);

        assertEquals(result, false);

    }

    @Test
    public void truncateThousand_equals_returnTrue() {

        boolean result = Main.truncateThousand(1000);

        assertEquals(result, true);

    }

    @Test
    public void checkNegativesNumber_whenPositiveNumberGiven_doesNotThrowException() {
        String[] list = {"3", "5", "34"};

        Main.checkNegativesNumber(list);
    }

    @Test
    public void checkNegativesNumber__whenNegativeNumberGiven_doesThrowException() {
        String[] list = {"-3", "5", "34"};

        assertThrows(IllegalArgumentException.class,
                () -> Main.checkNegativesNumber(list));
    }

    @Test
    public void appendEscape_whenCharIsNontRegax_returnsNonEscape() {
        List<String> regexList = Arrays.asList("@", "#", "%", "&", "<", ">");
        String given = regexList.get(new Random().nextInt(regexList.size()));

        String result = Main.appendEscape(given);

        assertEquals(result, given);
    }

    @Test
    public void appendEscape_whenCharIsRegex_returnsEscape() {
        List<String> regexList = Arrays.asList("$", "!", "^", "(", ")", "[", "]", "{", "}",
                ".", "|", "*", "?", "-");
        String given = regexList.get(new Random().nextInt(regexList.size()));

        String result = Main.appendEscape(given);

        assertEquals(result, "\\" + given);
    }

}