package algorithms.stringmatching;

import java.util.ArrayList;

public class NaiveSearch {

    public static ArrayList<Integer> search(String text, String pattern, long[] comparisons) {
        ArrayList<Integer> results = new ArrayList<>();

        comparisons[0] = 0;

        if (pattern.isEmpty()) {
            return results;
        }

        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            int j = 0;

            while (j < m) {
                comparisons[0]++;

                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }

                j++;
            }

            if (j == m) {
                results.add(i);
            }
        }

        return results;
    }
}
