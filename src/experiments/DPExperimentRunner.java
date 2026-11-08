package experiments;

import algorithms.dp.EditDistance;
import algorithms.dp.LCS;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DPExperimentRunner {

    private static final int REPETITIONS = 5;

    public static void main(String[] args) throws IOException {
        int[] sizes = {100, 500, 1000};

        Random random = new Random(42);

        FileWriter writer = new FileWriter("dp_results.csv");

        writer.write("Algorithm,InputSize,AverageTimeNs,AverageComparisons\n");

        for (int size : sizes) {
            long lcsTime = 0;
            long editTime = 0;

            long lcsComparisons = 0;
            long editComparisons = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                String first = StringGenerator.generate(size, random);
                String second = StringGenerator.generate(size, random);

                long[] lcsCount = new long[1];
                long start = System.nanoTime();

                LCS.findLCS(first, second, lcsCount);

                long end = System.nanoTime();
                lcsTime += end - start;
                lcsComparisons += lcsCount[0];

                long[] editCount = new long[1];
                start = System.nanoTime();

                EditDistance.calculate(first, second, editCount);

                end = System.nanoTime();
                editTime += end - start;
                editComparisons += editCount[0];
            }

            double avgLcsTime = lcsTime / (double) REPETITIONS;
            double avgEditTime = editTime / (double) REPETITIONS;

            double avgLcsComparisons = lcsComparisons / (double) REPETITIONS;
            double avgEditComparisons = editComparisons / (double) REPETITIONS;

            writer.write("algorithms.dp.LCS," + size + "," + avgLcsTime + "," + avgLcsComparisons + "\n");
            writer.write("algorithms.dp.EditDistance," + size + "," + avgEditTime + "," + avgEditComparisons + "\n");

            System.out.println("Finished size: " + size);
        }

        writer.close();

        System.out.println("Results saved to dp_results.csv");
    }
}
