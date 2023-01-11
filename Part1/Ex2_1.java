package Part1;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {

    public static void main(String[] args) throws Exception {
        String[] names = createTextFiles(1000, 2, 100000);
//        String[] names = new String[1000];
//        for (int i = 0; i < 1000; i++)
//            names[i] = "file_" + (i + 1);
        long start = System.currentTimeMillis();
        System.out.println(getNumOfLines(names));
        long end = System.currentTimeMillis();
        System.out.println("Regular: " + (end - start));
        start = System.currentTimeMillis();
        System.out.println(getNumOfLinesThreads(names));
        end = System.currentTimeMillis();
        System.out.println("Thread: " + (end - start));
        start = System.currentTimeMillis();
        System.out.println(getNumOfLinesThreadPool(names));
        end = System.currentTimeMillis();
        System.out.println("ThreadPool: " + (end - start));
        Ex2_1.deleteFiles(names);
    }

    /**
     * Creates 'n' text files with random number of lines in each file.
     *
     * @param n     The number of text files to be created.
     * @param seed  The seed for the random number generator.
     * @param bound The upper bound for the random number generator.
     * @return An array of strings containing the names of the created files.
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        ArrayList<String> fileNames = new ArrayList<>(n);
        ArrayList<Integer> numOfLines = generatesRandoms(n, seed, bound);
        String name = "file_";
        for (int i = 1; i <= n; i++) {
            try {
                FileWriter fWriter = new FileWriter("files/" + name + i + ".txt");
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
     */
    public static int getNumOfLines(String[] fileNames) {
        int numOfLines = 0;
        for (String filename : fileNames) {
            try (BufferedReader reader = new BufferedReader(new FileReader("files/" + filename + ".txt"))) {
                while (reader.readLine() != null)
                    numOfLines++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return numOfLines;
    }

    /**
     * Returns the total number of lines in all the text files specified by the 'fileNames' array, using multiple threads.
     *
     * @param fileNames An array of strings containing the names of the text files.
     * @return The total number of lines in all the text files.
     */
    public static int getNumOfLinesThreads(String[] fileNames) {
        int numOfLines = 0;
        LineCounterThread[] threads = new LineCounterThread[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            threads[i] = new LineCounterThread(fileNames[i]);
            threads[i].start();
        }
        for (LineCounterThread thread : threads) {
            try {
                thread.join();
                numOfLines += thread.getNumOfLines();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return numOfLines;

        /*
        // The implementation that works better on macOS.

        int numOfLines = 0;
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);  // use a thread pool with 8 threads
        ArrayList<Future<Integer>> results = new ArrayList<>(fileNames.length);
        try {
            for (int i = 0; i < fileNames.length; i++) {
                results.add(i, executor.submit(new LineCounterCallable(fileNames[i])));
            }
            for (Future<Integer> result : results) {
                numOfLines += result.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return numOfLines;
         */
    }

    /**
     * Returns the total number of lines in all the text files specified by the 'fileNames' array, using a thread pool.
     *
     * @param fileNames An array of strings containing the names of the text files.
     * @return The total number of lines in all the text files.
     */

    public static int getNumOfLinesThreadPool(String[] fileNames) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(fileNames.length);
        ArrayList<Future<Integer>> results = new ArrayList<>(fileNames.length);

        for (String fileName : fileNames) {
            results.add(threadPool.submit(new LineCounterCallable(fileName)));
        }

        threadPool.shutdown();
        int totalLines = 0;

        for (Future<Integer> result : results) {
            totalLines += result.get();
        }

        return totalLines;
    }

    /**
     * Generates a list of random integers.
     *
     * @param n     the number of random integers to generate.
     * @param seed  the seed to use for the random number generator.
     * @param bound the upper bound (exclusive) for the random numbers.
     * @return a list of n random integers in the range [0, bound).
     */
    public static ArrayList<Integer> generatesRandoms(int n, int seed, int bound) {
        ArrayList<Integer> randoms = new ArrayList<>();
        Random rand = new Random(seed);
        for (int i = 1; i <= n; i++) {
            int x = rand.nextInt(bound);
            randoms.add(x);
        }
        return randoms;
    }

    /**
     * Delete multiple files with the given file names.
     * @param fileNames An array of file names of files to be deleted
     */
    public static void deleteFiles(String[] fileNames) {
        for(String fileName:fileNames) {
            File file = new File("files/"+fileName + ".txt");
            if (file.delete()) {
                System.out.println(fileName + ".txt deleted successfully");
            } else {
                System.out.println("Failed to delete : " + fileName + ".txt");
            }
        }
    }
}
