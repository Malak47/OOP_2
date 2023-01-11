# File Line Counter
This Java program contains three methods for counting the number of lines in a given set of text files.
### Ex2_1
This class contains the following methods:
#### Method 1: createTextFiles

This method creates 'n' text files with random number of lines in each file. It takes the following arguments:

- __n:__ The number of text files to be created.
- __seed:__ The seed for the random number generator.
- __bound:__ The upper bound for the random number generator.

It returns an array of strings containing the names of the created files.

#### Method 2: getNumOfLines

This method returns the total number of lines in all the text files specified by the 'fileNames' array. It takes the following argument:

- __fileNames:__ An array of strings containing the names of the text files.

It returns the total number of lines in all the text files.

#### Method 3: getNumOfLinesThreads

This method returns the total number of lines in all the text files specified by the 'fileNames' array, using multiple threads. It takes the following argument:

- __fileNames:__ An array of strings containing the names of the text files.

It returns the total number of lines in all the text files.

#### Method 4: getNumOfLinesThreadPool

This method returns the total number of lines in all the text files specified by the 'fileNames' array, using a thread pool. It takes the following argument:

- __fileNames:__ An array of strings containing the names of the text files.

It returns the total number of lines in all the text files.

#### Method 5: generatesRandoms

This method generates a list of 'n' random integers. It takes the following arguments:
- __n:__ The number of random integers to generate.
- __seed:__ The seed to use for the random number generator.
- __bound:__ the upper bound (exclusive) for the random numbers.

It returns a list of n random integers in the range [0, bound).

### LineCounterThread
This is a class that extends the **Thread** class and counts the number of lines in a specified text file. The file path is specified in the constructor, and the number of lines can be accessed using the **getNumLines()** method.

### LineCounterCallable
This is a class that implements the **Callable** interface and counts the number of lines in a specified text file. The file path is specified in the constructor, and the number of lines can be accessed by calling the **call()** method. The **call()** method returns the number of lines as an Integer.

### Running time comparison (seconds):
| bound   | numOfFiles | getNumOfLines | getNumOfLinesThreads | getNumOfLinesThreadPool |
|---------|------------|---------------|----------------------|-------------------------|
| 100000  | 100        | 0.225         | 0.131                | 0.11                    |
| 100000  | 1000       | 1.441         | 1.087                | 0.478                   |
| 1000000 | 100        | 1.296         | 0.972                | 0.392                   |
| 1000000 | 1000       | 16.529        | 12.056               | 3.301                   |

### Example usage

```Java
// Create 5 text files with random number of lines
String[] fileNames = Ex2_1.createTextFiles(10, 2, 10000);

// Count the total number of lines in all the text files using the getNumOfLines method
int numOfLines1 = Ex2_1.getNumOfLines(fileNames);

// Count the total number of lines in all the text files using the getNumOfLinesThreads method
int numOfLines2 = Ex2_1.getNumOfLinesThreads(fileNames);

// Count the total number of lines in all the text files using the getNumOfLinesThreadPool method
int numOfLines3 = Ex2_1.getNumOfLinesThreadPool(fileNames);
```
### UML: 
![UML](https://github.com/Lara1011/OOP_2/blob/26ac4dc7ffe1c18e60816701cbb833bc4f8be4a5/Part1/UML.png)
