import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * This class implements the {@link Callable} interface and counts the number of lines in a specified text file.
 * The file path is specified in the constructor {@link LineCounterCallable#LineCounterCallable(String)}.
 * The number of lines can be accessed by calling the {@link LineCounterCallable#call()} method.
 *
 */
public class LineCounterCallable implements Callable<Integer> {
    private String fileName;

    /**
     * Constructs a new {@link LineCounterCallable} object with the specified file name.
     *
     * @param fileName the name of the file to be processed
     */
    public LineCounterCallable(String fileName){
        this.fileName = fileName;
    }

    /**
     * Counts the number of lines in the specified file and returns the result as an {@link Integer}.
     *
     * @return the number of lines in the file
     */
    @Override
    public Integer call() {
        int numOfLines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/lara/IdeaProjects/OOP_2/" + fileName + ".txt"))) {
            while (reader.readLine() != null) {
                numOfLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numOfLines;
    }
}