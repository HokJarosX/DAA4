package algorithms.dp;

public class LCS {

    public static int findLCS(String first, String second, long[] comparisons) {
        int n = first.length();
        int m = second.length();

        int[][] dp = new int[n + 1][m + 1];

        comparisons[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                comparisons[0]++;

                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }
}