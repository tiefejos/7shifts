
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println(Add(""));
        System.out.println(Add("//;\n1;3;4"));
        System.out.println(Add("//$\n1$2$3"));
        System.out.println(Add("//@\n2@3@8"));

        System.out.println(Add("//!\n1!5!67"));
        System.out.println(Add("//*\n1*2*3"));

        System.out.println(Add("//^\n1^5^67"));
        System.out.println(Add("//@\n1@5@22"));
        System.out.println(Add("//{\n1{5{22"));


    }

    public static int Add(String numbers) {
        if(numbers.isEmpty()) {
            return 0;
        }

        String newLine = "\n";
        String[] split = numbers.split(newLine);
        String getDelimiter = split[0].substring(2);
        String[] list = split[1].split(appendEscape(getDelimiter));

        checkNegativesNumber(list);
        return sumNumbers(list);
    }

    static Integer sumNumbers(String[] list) {
        return Stream.of(list)
                .mapToInt(digit -> Integer.parseInt(digit))
                .filter(number -> truncateThousand(number))
                .sum();
    }

    static boolean truncateThousand(int number) {
        return number > 1000 ? false : true;
    }

    static void checkNegativesNumber(String[] givenlist) {
        String numList = Stream.of(givenlist)
                .mapToInt(number -> Integer.parseInt(number))
                .filter(number -> number < 0)
                .mapToObj(number -> String.valueOf(number))
                .collect(Collectors.joining(",", "[", "],"));
        if(numList.length() > 3) {
            throw new IllegalArgumentException("Negatives not allowed " + numList);
        }
    }

    static String appendEscape(String delimiter) {
            if(delimiter.contains("$") || delimiter.contains("!") || delimiter.contains("^")
                    || delimiter.contains("(") || delimiter.contains(")") || delimiter.contains("*")
                    || delimiter.contains("|") || delimiter.contains("+") || delimiter.contains("?")
                    || delimiter.contains("/") || delimiter.contains("-") || delimiter.contains("\\")
                    || delimiter.contains("{") || delimiter.contains("}") || delimiter.contains("[")
                    || delimiter.contains("]")  || delimiter.contains(".")
            ) {
                return "\\" + delimiter;
        }
        return delimiter;
    }


}