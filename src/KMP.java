
import java.util.ArrayList;

public class KMP {

    public static ArrayList<Integer> search(String text, String pattern, long[] comparisons) {
        ArrayList<Integer> results = new ArrayList<>();

        comparisons[0] = 0;

        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return results;
        }

        int[] lps = buildLPS(pattern, comparisons);

        int i = 0;
        int j = 0;

        while (i < n) {
            comparisons[0]++;

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                results.add(i - j);
                j = lps[j - 1];
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return results;
    }

    private static int[] buildLPS(String pattern, long[] comparisons) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0;
        int i = 1;

        while (i < m) {
            comparisons[0]++;

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
