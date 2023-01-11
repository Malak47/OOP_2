# Multithreading: File Line Counter

This project counts the number of lines in specified text files using different methods. It contains 3
classes: `LineCounterCallable`, `LineCounterThread`, and `Ex2_1`.
---
## LineCounterCallable

This class implements the `Callable` interface and counts the number of lines in a specified text
file. The file path is specified in the constructor `LineCounterCallable(String)`. The number of lines can be accessed by
calling the call() method.

## LineCounterThread

This class extends the `Thread` class and counts the number of lines in a specified text file. The file
path is specified in the constructor` LineCounterThread(String)`. The number of lines can be accessed using the
`getNumOfLines()` method.

## Ex2_1

This class contains the main method, it has a function `createTextFiles(int, int, int)` that creates the files in "
files" folder and also a function `getNumOfLines(String[] names)` that counts the number of lines using regular method,
and another function `getNumOfLinesThreads(String[] names)` that counts the number of lines using threads method.
---
## Running time comparison (seconds)

**iMac [M1: 8 - cores CPU && 8 - cores GPU, Memory: 16 GB]**

| bound   | numOfFiles | getNumOfLines | getNumOfLinesThreads | getNumOfLinesThreadPool |
|---------|------------|---------------|----------------------|-------------------------|
| 100000  | 100        | 0.225         | 0.131                | 0.11                    |
| 100000  | 1000       | 1.441         | 1.087                | 0.478                   |
| 1000000 | 100        | 1.296         | 0.972                | 0.392                   |
| 1000000 | 1000       | 16.529        | 12.056               | 3.301                   |

**MSI Laptop [CPU: Intel Core i7-9750H 6 - cores, GPU: NVIDIA GeForce GTX 1660 Ti, Memory: 32 GB]**

| bound   | numOfFiles | getNumOfLines | getNumOfLinesThreads | getNumOfLinesThreadPool |
|---------|------------|---------------|----------------------|-------------------------|
| 100000  | 100        | 0.286         | 0.105                | 0.104                   |
| 100000  | 1000       | 3.391         | 0.781                | 0.691                   |
| 1000000 | 100        | 2.361         | 0.542                | 0.479                   |
| 1000000 | 1000       | 23.839        | 9.344                | 4.648                   |
---
## Example usage

```Java

//#################### Here is how to CREATE the files ####################\\

// Creates 10 text files with random number of lines up to 10000
                String[] fileNames = createTextFiles(10, 2, 10000);

//###### Here is how to COUNT the number of lines with each function ######\\ 

                        // Counts the number of lines using SingleThread \\
                        int numOfLines1 = getNumOfLines(fileNames);

                        // Counts the number of lines using MultiThreading \\
                        int numOfLines2 = getNumOfLinesThreads(fileNames);

                        // Counts the number of lines using ThreadPool \\
                        int numOfLines3 = getNumOfLinesThreadPool(fileNames);

//#################### Here is how to count the RUN-TIME ###################\\

                        long startTime = System.currentTimeMillis();
                        /* -> Here we call any function to run... <- */
                        long endTime = System.currentTimeMillis();

                        System.out.println(endTime - startTime);

//#################### Here is how to DELETE the files ####################\\
                        deleteFiles(fileNames);
```
---
### UML:
![UML](https://github.com/Lara1011/OOP_2/blob/26ac4dc7ffe1c18e60816701cbb833bc4f8be4a5/Part1/UML.png)