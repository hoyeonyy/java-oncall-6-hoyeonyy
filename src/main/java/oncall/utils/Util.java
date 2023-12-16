package oncall.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

    public static String removeSpace(String input) {
        return input.replaceAll(Regex.SPACE.regex, Regex.NOTHING.regex);
    }

    public static List<String> splitByComma(String input) {
        return new ArrayList<>(Arrays.asList(removeSpace(input).split(Regex.COMMA.regex)));
    }

    private enum Regex {
        SPACE(" "),
        NOTHING(""),
        COMMA(",");

        private final String regex;

        Regex(String regex) {
            this.regex = regex;
        }
    }

    private Util() {
    }
}