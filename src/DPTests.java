
public class DPTests {

    public static void main(String[] args) {
        testLCS();
        testEditDistance();
    }

    public static void testLCS() {
        System.out.println("LCS TESTS");

        checkLCS("ABC", "ABC", 3);
        checkLCS("ABC", "AC", 2);
        checkLCS("", "ABC", 0);
        checkLCS("ABC", "DEF", 0);
        checkLCS("", "", 0);
    }

    public static void testEditDistance() {
        System.out.println("\nEDIT DISTANCE TESTS");

        checkEdit("", "", 0);
        checkEdit("ABC", "ABC", 0);
        checkEdit("cat", "cut", 1);
        checkEdit("cat", "cats", 1);
        checkEdit("cats", "cat", 1);
        checkEdit("", "ABC", 3);
    }

    public static void checkLCS(String first, String second, int expected) {
        long[] comparisons = new long[1];
        int result = LCS.findLCS(first, second, comparisons);

        if (result == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL: expected " + expected + ", got " + result);
        }
    }

    public static void checkEdit(String first, String second, int expected) {
        long[] comparisons = new long[1];
        int result = EditDistance.calculate(first, second, comparisons);

        if (result == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL: expected " + expected + ", got " + result);
        }
    }
}
