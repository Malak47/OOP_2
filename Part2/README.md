```
       _____  _    ____  _  ______   __        _____ _____ _   _   ____  ____  ___ ___  ____  ___ _____ ___ _____ ____  
      |_   _|/ \  / ___|| |/ / ___|  \ \      / /_ _|_   _| | | | |  _ \|  _ \|_ _/ _ \|  _ \|_ _|_   _|_ _| ____/ ___| 
        | | / _ \ \___ \| ' /\___ \   \ \ /\ / / | |  | | | |_| | | |_) | |_) || | | | | |_) || |  | |  | ||  _| \___ \ 
        | |/ ___ \ ___) | . \ ___) |   \ V  V /  | |  | | |  _  | |  __/|  _ < | | |_| |  _ < | |  | |  | || |___ ___) |
        |_/_/   \_\____/|_|\_\____/     \_/\_/  |___| |_| |_| |_| |_|   |_| \_\___\___/|_| \_\___| |_| |___|_____|____/ 
```

# Priority-Based Task Manager

This project is an implementation of a Priority-Based Task Manager, it contains three classes: `TaskType`, `Task`, and
`CustomExecutor`.

---

## TaskType
An Enum class that defines the type of the task, it has three values: [`COMPUTATIONAL, IO, OTHER`]. Each
value has a priority level from 1 to 10.

## Task
This class represents a unit of work that can be executed asynchronously, It extends `FutureTask` and implements
`Callable`, and holds a `TaskType` property to define the priority of the task. It provides two factory methods for creating
task instances:
   - `createTask(Callable, TaskType)`: creates a new Task instance with the given callable and taskType.
   - `createTask(Callable)`: creates a new Task instance with the given callable and default taskType OTHER.

## CustomExecutor 
This class is an implementation of `Executor` interface, it's used to execute tasks based on their
priority level, it allows the submission of `Task` instances, it takes care of sorting the tasks based on their priority
level and then executing them.

---

## Example usage
```Java
//#################### Here is how to CREATE the tasks ####################\\

// creates a new Task with task type IO and Callable task
Task<String> task1 = Task.createTask(() -> "Task 1", TaskType.IO);

// creates a new Task with task type COMPUTATIONAL and Callable task
Task<String> task2 = Task.createTask(() -> "Task 2", TaskType.COMPUTATIONAL);

// creates a new Task with default task type OTHER and Callable task
Task<String> task3 = Task.createTask(() -> "Task 3");

//###### Here is how to SUBMIT the tasks to the executor ######\\ 

// create an instance of the CustomExecutor
CustomExecutor executor = new CustomExecutor();

// submit the task to the executor
executor.submit(task1);
executor.submit(task2);
executor.submit(task3);

//###### Here is how to EXECUTE the tasks in order of priority ######\\ 

// execute all tasks in the executor
executor.execute(task1);
executor.execute(task2);
executor.execute(task3);

//#################### Here is how to SHUTDOWN the executor ####################\\
executor.gracefullyTerminate();
```

---

## Testing && Results
**Test #1: We used all the Threads in the cpu.**

![UML](https://github.com/Malak47/OOP_2/blob/main/Part2/Images/Result1.png)

**Test #2: We used only one Thread.**

![UML](https://github.com/Malak47/OOP_2/blob/main/Part2/Images/Result2.png)

---

## UML
![UML](https://github.com/Malak47/OOP_2/blob/main/Part2/Images/UML.png)
