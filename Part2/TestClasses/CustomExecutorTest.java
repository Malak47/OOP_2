package Part2.TestClasses;

import Part2.*;
import org.junit.jupiter.api.*;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertThrows;


class CustomExecutorTest {
    @Test
    void submit() throws ExecutionException, InterruptedException {
        CustomExecutor executor = new CustomExecutor();
        Future<String> future = executor.submit(() -> "submit(Callable<T> callable)");
        String result = future.get();
        Assertions.assertEquals("submit(Callable<T> callable)", result);
    }

    @Test
    void submitWithTaskType() throws ExecutionException, InterruptedException {
        CustomExecutor executor = new CustomExecutor();
        Future<String> future = executor.submit(() -> "submit(Callable<T> callable, TaskType taskType)", TaskType.COMPUTATIONAL);
        String result = future.get();
        Assertions.assertEquals("submit(Callable<T> callable, TaskType taskType)", result);
    }

    @Test
    void submitWithNullTask() {
        assertThrows(NullPointerException.class, () -> {
            CustomExecutor executor = new CustomExecutor();
            executor.submit((Task<String>) null);
        });
    }

    @Test
    void submitWithNullCallable() {
        assertThrows(NullPointerException.class, () -> {
            CustomExecutor executor = new CustomExecutor();
            executor.submit((Callable<String>) null);
        });
    }

    @Test
    void submitWithNullCallableAndTaskType() {
        assertThrows(NullPointerException.class, () -> {
            CustomExecutor executor = new CustomExecutor();
            executor.submit((Callable<String>) null, null);
        });
    }

    @Test
    void createTask() {
        new CustomExecutor();
        Callable<String> callable = () -> "createTask";
        Task<String> task = new Task<>(callable, TaskType.COMPUTATIONAL);
        Task<String> createdTask = new Task<>(task, task.getTaskType());
        Assertions.assertEquals(task.getTaskType(), createdTask.getTaskType());
    }

    @Test
    void setMaxPriority() {
        CustomExecutor executor = new CustomExecutor();
        int expectedMaxPriority = 10;
        executor.setMaxPriority(expectedMaxPriority);
        Assertions.assertEquals(expectedMaxPriority, executor.getCurrentMax());
    }

    @Test
    void gracefullyTerminate() throws InterruptedException {
        CustomExecutor executor = new CustomExecutor();
        executor.submit(() -> "Terminate");
        executor.gracefullyTerminate();
        Assertions.assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));
    }

    @Test
    void testToString() {
        CustomExecutor executor = new CustomExecutor();
        String executorToString = executor.toString();
        Assertions.assertTrue(executorToString.contains("max priority = "));
    }
}

