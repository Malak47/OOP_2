# File Line Counter
This Java program contains three methods for counting the number of lines in a given set of text files.
### Ex2_1
This class contains the following methods:
#### Method 1: createTextFiles

This method creates 'n' text files with random number of lines in each file. It takes the following arguments:

- __n:__ The number of text files to be created.
- __seed:__ The seed for the random number generator.
- __bound:__ The upper bound for the random number generator.

<br>
It returns an array of strings containing the names of the created files.

#### Method 2: getNumOfLines

This method returns the total number of lines in all the text files specified by the 'fileNames' array. It takes the following argument:

- __fileNames:__ An array of strings containing the names of the text files.

#### Method 3: getNumOfLinesThreads

This method returns the total number of lines in all the text files specified by the 'fileNames' array, using multiple threads. It takes the following argument:

- __fileNames:__ An array of strings containing the names of the text files.

#### Method 4: getNumOfLinesThreadPool

This method returns the total number of lines in all the text files specified by the 'fileNames' array, using a thread pool. It takes the following argument:

- __fileNames:__ An array of strings containing the names of the text files.

### LineCounterThread
This is a class that extends the **Thread** class and counts the number of lines in a specified text file. The file path is specified in the constructor, and the number of lines can be accessed using the **getNumLines()** method.

### LineCounterCallable
This is a class that implements the **Callable** interface and counts the number of lines in a specified text file. The file path is specified in the constructor, and the number of lines can be accessed by calling the **call()** method. The **call()** method returns the number of lines as an Integer.

### Running time comparison:
| numOfFiles | getNumOfLines | getNumOfLinesThreads | getNumOfLinesThreadPool |
|------------|---------------|----------------------|-------------------------|
| 10         |               |                      |                         |
| 100        |               |                      |                         |
| 1000       |               |                      |                         |
| 10000      |               |                      |                         |

### Example usage

```Java
// Create 5 text files with random number of lines
String[] fileNames = FileLineCounter.createTextFiles(5, 2, 1000);

// Count the total number of lines in all the text files using the getNumOfLines method
int numOfLines1 = FileLineCounter.getNumOfLines(fileNames);

// Count the total number of lines in all the text files using the getNumOfLinesThreads method
int numOfLines2 = FileLineCounter.getNumOfLinesThreads(fileNames);

// Count the total number of lines in all the text files using the getNumOfLinesThreadPool method
int numOfLines3 = FileLineCounter.getNumOfLinesThreadPool(fileNames);
```
### UML: 
![UML](https://github.com/Lara1011/OOP_2/blob/4eeeac4675b2547e8a4b27c3b77644b418ddee48/UML.png)