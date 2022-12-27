import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Ex2_1 {

    public static String[] createTextFiles(int n, int seed, int bound) {
        ArrayList<String> fileNames = new ArrayList<>(n);
        ArrayList<Integer> numOfLines = generatesRandoms(n, seed, bound);
        String name = "file_";
        for(int i=1; i<=n; i++) {
            try {
                FileWriter fWriter = new FileWriter(name + i + ".txt");
                fileNames.add(name + i);
                for (int j = 1; j <= numOfLines.get(i - 1); j++) {
                    fWriter.write("Hello line: " + j);
                    if(j != numOfLines.get(i - 1))
                        fWriter.write("\n");
                }
                fWriter.close();
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
        return fileNames.toArray(new String[1]);
    }

    public static int getNumOfLines(String[] fileNames){
        return 0;
    }

    public int getNumOfLinesThreads(String[] fileNames){
        return 0;
    }

    public int getNumOfLinesThreadPool(String[] fileNames){
        return 0;
    }

    /**
     * This method generates random number of lines for each file.
     * @param n: Number of lines.
     * @param seed: Used to create a new random number.
     * @param bound: Max number of lines.
     * @return Array List that contains 'n' random numbers.
     */
    public static ArrayList<Integer> generatesRandoms(int n, int seed, int bound){
        ArrayList<Integer> randoms = new ArrayList<>();
        Random rand = new Random(seed);
        for (int i = 1; i <= n; i++) {
            int x = rand.nextInt(bound);
            randoms.add(x);
        }
        return randoms;
    }

}
