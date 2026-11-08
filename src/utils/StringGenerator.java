package utils;

import java.util.Random;

public class StringGenerator {

    private static final String ALPHABET = "ABCD";

    public static String generate(int length, Random random) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            result.append(ALPHABET.charAt(index));
        }

        return result.toString();
    }

    public static String generatePresentPattern(String text, int length, Random random) {
        int start = random.nextInt(text.length() - length + 1);
        return text.substring(start, start + length);
    }

    public static String generateAbsentPattern(int length) {
        return "E".repeat(length);
    }
}