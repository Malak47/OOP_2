package Part1;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class Part1_test {

    @Test
    void createTextFiles() {
        String[] names = Ex2_1.createTextFiles(3, 2, 100000);
        assertArrayEquals(new String[]{"file_1", "file_2", "file_3"}, names);
        System.out.println("Deleting files: ");
        Ex2_1.deleteFiles(names);
        System.out.println();
    }

    @Test
    void getNumOfLines() {
        String[] names = Ex2_1.createTextFiles(3, 2, 100000);
        int numOfLines = Ex2_1.getNumOfLines(names);
        assertEquals(91520, numOfLines);
        System.out.println("Deleting files: ");
        Ex2_1.deleteFiles(names);
        System.out.println();
    }

    @Test
    void getNumOfLinesThreads() {
        String[] names = Ex2_1.createTextFiles(3, 2, 100000);
        int numOfLines = Ex2_1.getNumOfLinesThreads(names);
        assertEquals(91520, numOfLines);
        System.out.println("Deleting files: ");
        Ex2_1.deleteFiles(names);
        System.out.println();
    }

    @Test
    void getNumOfLinesThreadPool() throws ExecutionException, InterruptedException {
        String[] names = Ex2_1.createTextFiles(3, 2, 100000);
        int numOfLines = Ex2_1.getNumOfLinesThreadPool(names);
        assertEquals(91520, numOfLines);
        System.out.println("Deleting files: ");
        Ex2_1.deleteFiles(names);
        System.out.println();
    }
}