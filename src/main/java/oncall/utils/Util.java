package oncall.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    public static String removeSpace(String input) {
        return input.replaceAll(Regex.SPACE.regex, Regex.NOTHING.regex);
    }

    public static String removeDelimiters(String input) {
        return input.replace(Regex.BRACKETS_START.regex, Regex.NOTHING.regex)
                .replace(Regex.BRACKETS_END.regex, Regex.NOTHING.regex);
    }

    public static List<String> splitByComma(String input) {
        return new ArrayList<>(Arrays.asList(removeSpace(input).split(Regex.COMMA.regex)));
    }

    public static List<String> applyAll(String input) {
        return splitByComma(removeDelimiters(removeSpace(input)));
    }


    private enum Regex {
        SPACE(" "),
        NOTHING(""),
        BRACKETS_START("["),
        BRACKETS_END("]"),
        COMMA(",");

        private final String regex;

        Regex(String regex) {
            this.regex = regex;
        }
    }

    private Util() {
    }
}