package pages.utils;

import java.util.List;

public class Helpers {

    public static boolean isTextEqual(String text1, String text2) {
        return text1.toLowerCase().trim().equals(text2.toLowerCase().trim());
    }

    public static boolean isArraySortedByAsc(List<Double> array) {
        boolean result = true;
        for (int i = 0; i < array.size()-1; i++) {
            if (array.get(i) > array.get(i+1)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static boolean isArraySortedByDesc(List<Double> array) {
        boolean result = true;
        for (int i = 0; i < array.size()-1; i++) {
            if (array.get(i) < array.get(i+1)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
