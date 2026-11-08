package tests;

import algorithms.suffixarray.KasaiLCP;

import java.util.Arrays;

public class KasaiTests {

    public static void main(String[] args) {
        String text = "banana";
        int[] suffixArray = {5, 3, 1, 0, 4, 2};
        int[] expected = {0, 1, 3, 0, 0, 2};

        long[] comparisons = new long[1];

        int[] result = KasaiLCP.build(text, suffixArray, comparisons);

        if (Arrays.equals(result, expected)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
            System.out.println(Arrays.toString(result));
        }
    }
}
