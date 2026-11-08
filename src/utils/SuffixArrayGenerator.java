package utils;

import java.util.Arrays;

public class SuffixArrayGenerator {

    public static int[] build(String text) {
        int n = text.length();

        Integer[] suffixes = new Integer[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }

        Arrays.sort(suffixes, (a, b) -> {
            int i = a;
            int j = b;

            while (i < n && j < n) {
                if (text.charAt(i) != text.charAt(j)) {
                    return Character.compare(text.charAt(i), text.charAt(j));
                }

                i++;
                j++;
            }

            return Integer.compare(n - a, n - b);
        });

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = suffixes[i];
        }

        return result;
    }
}
