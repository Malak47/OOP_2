import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex2_1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String[] names = createTextFiles(3, 2, 1000);
        System.out.println(getNumOfLinesThreads(names));
    }

    /**
     * Creates 'n' text files with random number of lines in each file.
     *
     * @param n     The number of text files to be created.
     * @param seed  The seed for the random number generator.
     * @param bound The upper bound for the random number generator.
     * @return An array of strings containing the names of the created files.
     **/
    public static String[] createTextFiles(int n, int seed, int bound) {
        ArrayList<String> fileNames = new ArrayList<>(n);
        ArrayList<Integer> numOfLines = generatesRandoms(n, seed, bound);
        String name = "file_";
        for (int i = 1; i <= n; i++) {
            try {
                FileWriter fWriter = new FileWriter(name + i + ".txt");
                fileNames.add(name + i);
                for (int j = 1; j <= numOfLines.get(i - 1); j++) {
                    fWriter.write("Hello line: " + j);
                    if (j != numOfLines.get(i - 1))
                        fWriter.write("\n");
                }
                fWriter.close();
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
        return fileNames.toArray(new String[1]);
    }

    /**
     * Returns the total number of lines in all the text files specified by the 'fileNames' array.
     *
     * @param fileNames An array of strings containing the names of the text files.
     * @return The total number of lines in all the text files.
     **/
    public static int getNumOfLines(String[] fileNames) {
        int numOfLines = 0;
        for (String filename : fileNames) {
            try (BufferedReader reader = new BufferedReader(new FileReader("/Users/lara/IdeaProjects/OOP_2/" + filename + ".txt"))) {
                while (reader.readLine() != null)
                    numOfLines++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return numOfLines;
    }

    public static int getNumOfLinesThreads(String[] fileNames) {
        return 0;
    }

    public static int getNumOfLinesThreadPool(String[] fileNames) throws ExecutionException, InterruptedException {
        return 0;
    }

    /**
     * Generates a list of random integers.
     * @param n the number of random integers to generate.
     * @param seed the seed to use for the random number generator.
     * @param bound the upper bound (exclusive) for the random numbers.
     * @return a list of n random integers in the range [0, bound).
     **/
    public static ArrayList<Integer> generatesRandoms(int n, int seed, int bound) {
        ArrayList<Integer> randoms = new ArrayList<>();
        Random rand = new Random(seed);
        for (int i = 1; i <= n; i++) {
            int x = rand.nextInt(bound);
            randoms.add(x);
        }
        return randoms;
    }
}