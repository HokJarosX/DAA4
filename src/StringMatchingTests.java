
import java.util.ArrayList;

public class StringMatchingTests {

    public static void main(String[] args) {
        test("ABCABC", "ABC");
        test("ABCDEFG", "ABC");
        test("ABCDEFG", "EFG");
        test("AAAAAA", "AAA");
        test("ABCDEFG", "HIJ");
        test("ABC", "ABCDEFG");
        test("", "A");
        test("ABC", "");
    }

    public static void test(String text, String pattern) {
        long[] naiveCount = new long[1];
        long[] rabinCount = new long[1];
        long[] kmpCount = new long[1];

        ArrayList<Integer> naive = NaiveSearch.search(text, pattern, naiveCount);
        ArrayList<Integer> rabin = RabinKarp.search(text, pattern, rabinCount);
        ArrayList<Integer> kmp = KMP.search(text, pattern, kmpCount);

        boolean passed = naive.equals(rabin) && naive.equals(kmp);

        if (passed) {
            System.out.println("PASS: " + text + " | " + pattern);
        } else {
            System.out.println("FAIL: " + text + " | " + pattern);
            System.out.println("Naive: " + naive);
            System.out.println("Rabin-Karp: " + rabin);
            System.out.println("KMP: " + kmp);
        }
    }
}
