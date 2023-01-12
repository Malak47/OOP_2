# Multi-Threading and Priority-Based Task Management
This repository contains two parts that showcase the use of Multi-Threading and task management based on priority levels. The first project is a multi-Threaded text file line counter and the second one is a priority-based task manager.

## Part 1: Multi-Threading Text File Line Counter
This project demonstrates the use of Multi-Threading for counting the number of lines in text files. It includes two classes that implement Multi-Threading using both the `Callable` interface and the `Thread` class, and the main class which has another regular method for calculating the number of lines in text files.

## Part 2: Priority-Based Task Manager
This project demonstrates the use of priority-based task management. It includes three classes that implement a task manager that can sort and execute tasks based on their priority level. It includes an implementation of the `Executor` interface and allows for the submission of tasks and sorting them based on priority.

---

## Usage

1. For the first project, you can use the class `Ex2_1` that has a main method to test the methods' performance. It creates 1000 text files with random numbers and count the lines in three different ways, the first one is a regular method without using thread, the second one using the `LineCounterCallable` class (Multi-Threads) and the last one using the `LineCounterThread` class (threadpool) , The results of the execution will show the time it took to count the lines using the three methods.
<br>


2. For the second project, you can use the class `TaskType` to set the priority level of the task, use `Task` to create instances of tasks, you can use the two factory methods `createTask(Callable, TaskType)` or `createTask(Callable)` methods. Then use the `CustomExecutor` to submit the tasks and execute them based on their priority level.

## Packages

- **Part1 :** 
  * `Ex2_1.java` : Runs the main.
  * `LineCounterCallable.java` : Implements Callable\<Integer>.
  * `LineCounterThread.java` : Extends Thread.
  * `Part1_test.java` : JUNIT test for the functions.
  * `UML.png` : UML graph.
  * `README.md` : Deeper explanations of the Classes and their functionalities.
  
<br>

- **Part2 :**
  * `Task.java` : Runs the main.
  * `TaskType.java` : Implements Callable\<Integer>.
  * `CustomExecutor.java` : Extends Thread.
  * **Images** :
    * Part2.uml
    * Result1.png
    * Result2.png
    * UML.png
  * **Images** :
      * CustomExecutorTest.java
      * TaskTest.java
      * MyTest.java
      * Tests.java
  * `UML.png` : UML graph.
  * `README.md` : Deeper explanations of the Classes and their functionalities.
