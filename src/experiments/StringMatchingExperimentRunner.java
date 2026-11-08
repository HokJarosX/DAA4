package experiments;

import algorithms.stringmatching.KMP;
import algorithms.stringmatching.NaiveSearch;
import algorithms.stringmatching.RabinKarp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class StringMatchingExperimentRunner {

    private static final int REPETITIONS = 5;
    private static final int PATTERN_LENGTH = 10;

    public static void main(String[] args) throws IOException {
        int[] sizes = {10000, 50000, 100000};

        Random random = new Random(42);

        FileWriter writer = new FileWriter("string_matching_results.csv");

        writer.write("Algorithm,TextSize,Case,AverageTimeNs,AverageComparisons\n");

        for (int size : sizes) {
            String text = StringGenerator.generate(size, random);

            String presentPattern =
                    StringGenerator.generatePresentPattern(text, PATTERN_LENGTH, random);

            String absentPattern =
                    StringGenerator.generateAbsentPattern(PATTERN_LENGTH);

            runCase(writer, text, presentPattern, size, "Present");
            runCase(writer, text, absentPattern, size, "Absent");

            System.out.println("Finished size: " + size);
        }

        writer.close();

        System.out.println("Results saved to string_matching_results.csv");
    }

    public static void runCase(FileWriter writer, String text,
                               String pattern, int size, String testCase)
            throws IOException {

        long[] count = new long[1];

        NaiveSearch.search(text, pattern, count);
        KMP.search(text, pattern, count);
        RabinKarp.search(text, pattern, count);

        long totalTime = 0;
        long totalComparisons = 0;

        for (int r = 0; r < REPETITIONS; r++) {
            count[0] = 0;

            long start = System.nanoTime();
            NaiveSearch.search(text, pattern, count);
            long end = System.nanoTime();

            totalTime += end - start;
            totalComparisons += count[0];
        }

        writeResult(writer, "Naive", size, testCase,
                totalTime, totalComparisons);

        totalTime = 0;
        totalComparisons = 0;

        for (int r = 0; r < REPETITIONS; r++) {
            count[0] = 0;

            long start = System.nanoTime();
            KMP.search(text, pattern, count);

            long end = System.nanoTime();

            totalTime += end - start;
            totalComparisons += count[0];
        }

        writeResult(writer, "algorithms.stringmatching.KMP", size, testCase, totalTime, totalComparisons);

        totalTime = 0;
        totalComparisons = 0;

        for (int r = 0; r < REPETITIONS; r++) {
            count[0] = 0;

            long start = System.nanoTime();
            RabinKarp.search(text, pattern, count);
            long end = System.nanoTime();

            totalTime += end - start;
            totalComparisons += count[0];
        }

        writeResult(writer, "algorithms.stringmatching.RabinKarp", size, testCase,
                totalTime, totalComparisons);
    }

    public static void writeResult(FileWriter writer, String algorithm, int size, String testCase, long totalTime, long totalComparisons)
            throws IOException {

        double averageTime = totalTime / (double) REPETITIONS;
        double averageComparisons = totalComparisons / (double) REPETITIONS;

        writer.write(algorithm + "," + size + "," + testCase + "," + averageTime + "," + averageComparisons + "\n");
    }
}
