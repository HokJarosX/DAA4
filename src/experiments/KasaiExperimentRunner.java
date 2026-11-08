package experiments;

import algorithms.suffixarray.KasaiLCP;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class KasaiExperimentRunner {

    private static final int REPETITIONS = 5;

    public static void main(String[] args) throws IOException {
        int[] sizes = {1000, 5000, 10000};

        Random random = new Random(42);

        FileWriter writer = new FileWriter("kasai_results.csv");

        writer.write("Algorithm,InputSize,AverageTimeNs,AverageComparisons\n");

        for (int size : sizes) {
            String text = StringGenerator.generate(size, random);

            int[] suffixArray = SuffixArrayGenerator.build(text);

            long[] warmupCount = new long[1];
            KasaiLCP.build(text, suffixArray, warmupCount);

            long totalTime = 0;
            long totalComparisons = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                long[] comparisons = new long[1];

                long start = System.nanoTime();

                KasaiLCP.build(text, suffixArray, comparisons);

                long end = System.nanoTime();

                totalTime += end - start;
                totalComparisons += comparisons[0];
            }

            double averageTime = totalTime / (double) REPETITIONS;
            double averageComparisons = totalComparisons / (double) REPETITIONS;

            writer.write("Kasai," + size + "," + averageTime + ","
                    + averageComparisons + "\n");

            System.out.println("Finished size: " + size);
        }

        writer.close();

        System.out.println("Results saved to kasai_results.csv");
    }
}
