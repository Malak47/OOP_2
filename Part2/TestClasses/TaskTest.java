package Part2.TestClasses;

import Part2.Task;
import Part2.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;


class TaskTest {
    Callable<String> callable1 = () -> "Task1";
    Callable<String> callable2 = () -> "Task2";
    Callable<String> callable3 = () -> "Task3";
    TaskType taskType1 = TaskType.COMPUTATIONAL;
    TaskType taskType2 = TaskType.IO;
    TaskType taskType3 = TaskType.OTHER;
    Task<String> task1 = Task.createTask(callable1, taskType1);
    Task<String> task2 = Task.createTask(callable2, taskType2);
    Task<String> task3 = Task.createTask(callable3, taskType3);

    @Test
    void createTask() {     //Constructor without Priority -> gets the default priority = OTHER(3).
        Callable<String> callable4 = () -> "Task4";
        Task<String> task4 = Task.createTask(callable4);
        Assertions.assertEquals(TaskType.OTHER.getPriorityValue(), task4.getPriority());
    }

    @Test
    void CreateTask() throws Exception {
        Assertions.assertEquals(taskType1, task1.getTaskType());
        Assertions.assertEquals("Task1", task1.call());
        Assertions.assertEquals(taskType2, task2.getTaskType());
        Assertions.assertEquals("Task2", task2.call());
        Assertions.assertEquals(taskType3, task3.getTaskType());
        Assertions.assertEquals("Task3", task3.call());
    }

    @Test
    void getTaskType() {
        Assertions.assertEquals(TaskType.COMPUTATIONAL, taskType1.getType());
        Assertions.assertEquals(TaskType.IO, taskType2.getType());
        Assertions.assertEquals(TaskType.OTHER, taskType3.getType());
    }

    @Test
    void getPriority() {
        Assertions.assertEquals(1, task1.getPriority());
        Assertions.assertEquals(2, task2.getPriority());
        Assertions.assertEquals(3, task3.getPriority());
    }

    @Test
    void call() throws Exception {
        String result = task1.call();
        Assertions.assertEquals("Task1", result);
    }

    @Test
    public void compareTo() {
        TaskType taskType4 = TaskType.OTHER;
        Assertions.assertTrue(taskType1.getPriorityValue() < taskType2.getPriorityValue());
        Assertions.assertTrue(taskType2.getPriorityValue() < taskType3.getPriorityValue());
        Assertions.assertEquals(taskType4.getPriorityValue(), taskType3.getPriorityValue());
    }

    @Test
    void testToString() {
        String taskString = task1.toString();
        Assertions.assertTrue(taskString.contains("task type = " + taskType1));
        Assertions.assertTrue(taskString.contains("["));
    }
}