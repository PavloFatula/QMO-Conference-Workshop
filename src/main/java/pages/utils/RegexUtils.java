package pages.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtils {

    private final static String PATTERN_UNSIGNED_NUMBER = "\\d+";
    private final static String PATTERN_UNSIGNED_DOUBLE = "(\\d)+[, ]*\\d*\\.\\d+";
    private final static String EXTRACT_NUMBER_MESSAGE = "NumberFormatException for pattern =  %s text =  %s";

    private RegexUtils() {
    }
    public static String replaceCommaInString(String text) {
        return text.replace(",", "");
    }

    public static boolean isTextMatches(String pattern, String text) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        return matcher.matches();
    }

    public static boolean isDoubleMatches(String text) {
        return isTextMatches(PATTERN_UNSIGNED_DOUBLE, text);
    }

    public static String extractFirstString(String pattern, String text) {
        String result = "";
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        if (matcher.find()) {
            result = text.substring(matcher.start(), matcher.end());
        }
        return result;
    }

    public static int extractFirstNumber(String text) {
        int result = -1;
        String extractText = extractFirstString(PATTERN_UNSIGNED_NUMBER, text);
        if (!extractText.isEmpty()) {
            try {
                result = Integer.parseUnsignedInt(extractText);
            } catch (NumberFormatException e) {
                // TODO Develop Custom Exception
                throw new RuntimeException(String.format(EXTRACT_NUMBER_MESSAGE, PATTERN_UNSIGNED_NUMBER, text));
            }
        }
        return result;
    }

    public static double extractFirstDouble(String text) {
        double result = -1;
        String extractText = extractFirstString(PATTERN_UNSIGNED_DOUBLE, text);
        if (!extractText.isEmpty()) {
            try {
                if (extractText.contains(",")) {
                    extractText = RegexUtils.replaceCommaInString(extractText);
                }
                result = Double.parseDouble(extractText);

            } catch (NumberFormatException e) {
                // TODO Develop Custom Exception
                throw new RuntimeException(String.format(EXTRACT_NUMBER_MESSAGE, PATTERN_UNSIGNED_DOUBLE, text));
            }
        }
        return result;
    }
}
