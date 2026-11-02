
import java.util.ArrayList;

public class RabinKarp {

    private static final int BASE = 256;
    private static final long MOD = 1000000007L;

    public static ArrayList<Integer> search(String text, String pattern, long[] comparisons) {
        ArrayList<Integer> results = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        comparisons[0] = 0;



        if (m == 0 || m > n) {
            return results;
        }

        long patternHash = 0;
        long windowHash = 0;
        long highestPower = 1;

        for (int i = 0; i < m - 1; i++) {
            highestPower = (highestPower * BASE) % MOD;
        }

        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * BASE + pattern.charAt(i)) % MOD;
            windowHash = (windowHash * BASE + text.charAt(i)) % MOD;
        }

        for (int i = 0; i <= n - m; i++) {

            if (patternHash == windowHash) {
                int j = 0;

                while (j < m) {
                    comparisons[0]++;

                    if (pattern.charAt(j) != text.charAt(i + j)) {
                        break;
                    }

                    j++;
                }

                if (j == m) {
                    results.add(i);
                }
            }

            if (i < n - m) {
                windowHash = (windowHash - text.charAt(i) * highestPower % MOD + MOD) % MOD;
                windowHash = (windowHash * BASE + text.charAt(i + m)) % MOD;
            }
        }

        return results;
    }
}
