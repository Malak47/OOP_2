package Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class extends the {@link Thread} class and counts the number of lines in a specified text file.
 * The file path is specified in the constructor {@link LineCounterThread#LineCounterThread(String)}.
 * The number of lines can be accessed using the {@link LineCounterThread#getNumOfLines()} method.
 */
public class LineCounterThread extends Thread{
    private String fileName;
    private int numOfLines;

    /**
     * Constructs a new {@link LineCounterThread} object with the specified file name.
     * @param fileName the name of the file to be processed
     */
    public LineCounterThread(String fileName){
        this.fileName = fileName;
        this.numOfLines = 0;
    }

    /**
     * Counts the number of lines in the specified file.
     */
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("files\\" + fileName + ".txt"))) {
            while (reader.readLine() != null) {
                numOfLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the number of lines counted in the file.
     * @return the number of lines in the file
     */
    public int getNumOfLines(){
        return this.numOfLines;
    }

}
