package Part2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<T> extends FutureTask<T> implements Callable<T>, Comparable<Task<T>> {

    private final Callable<T> callable;
    private final TaskType taskType;

    public Task(Callable<T> callable, TaskType taskType) {
        super(callable);
        this.callable = callable;
        this.taskType = taskType;
    }

    public static <T> Task<T> createTask(Callable<T> callable, TaskType taskType) {
        return new Task<>(callable, taskType);
    }

    public static <T> Task<T> createTask(Callable<T> callable) {
        return new Task<>(callable, TaskType.OTHER);
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public int getPriority() {
        return this.taskType.getPriorityValue();
    }

    @Override
    public T call() throws Exception {
        return callable.call();
    }

    @Override
    public int compareTo(Task<T> other) {
        return Integer.compare(taskType.getPriorityValue(), other.taskType.getPriorityValue());
    }

    @Override
    public String toString() {
        String s = super.toString();
        return s.substring(s.indexOf('['), s.indexOf(']')) + ", task type = " + this.taskType + "]";
    }
}
