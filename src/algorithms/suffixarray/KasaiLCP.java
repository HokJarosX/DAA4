package algorithms.suffixarray;

public class KasaiLCP {

    public static int[] build(String text, int[] suffixArray, long[] comparisons) {
        int n = text.length();

        comparisons[0] = 0;

        if (suffixArray.length != n) {
            throw new IllegalArgumentException("Invalid suffix array");
        }

        int[] lcp = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[suffixArray[i]] = i;
        }

        int h = 0;

        for (int i = 0; i < n; i++) {
            if (rank[i] == 0) {
                h = 0;
                continue;
            }

            int j = suffixArray[rank[i] - 1];

            while (i + h < n && j + h < n) {
                comparisons[0]++;

                if (text.charAt(i + h) != text.charAt(j + h)) {
                    break;
                }

                h++;
            }

            lcp[rank[i]] = h;

            if (h > 0) {
                h--;
            }
        }

        return lcp;
    }
}
