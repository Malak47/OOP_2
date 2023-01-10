package Part2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * The Task class represents a unit of work that can be executed asynchronously by a {@link CustomExecutor}.
 * It extends {@link FutureTask} and implements {@link Callable}, and holds a {@link TaskType} property to define the priority of the task.
 * <p>
 * Task class provides two factory methods for creating task instances:
 * {@link #createTask(Callable, TaskType)}: creates a new Task instance with the given callable and taskType
 * {@link #createTask(Callable)}: creates a new Task instance with the given callable and default taskType OTHER.
 * <p>
 * The task can be sorted according to their task type priority by implementing {@link Comparable} interface.
 * The class also overrides the {@link #toString()} method to add the task type property to the string representation
 *
 * @param <T> The type of the task result (Generic)
 */
public class Task<T> extends FutureTask<T> implements Callable<T>, Comparable<Task<T>> {

    private final Callable<T> callable;
    private final TaskType taskType;

    /**
     * Creates a Task that will, upon running, execute the given Callable.
     *
     * @param callable the callable task being wrapped, if null, a {@link NullPointerException} will be thrown
     * @param taskType the task type that this task represents
     */
    public Task(Callable<T> callable, TaskType taskType) {
        super(callable);
        this.callable = callable;
        this.taskType = taskType;
    }

    /**
     * creates a new Task instance with the given callable and taskType (Factory)
     *
     * @param callable the callable task being wrapped, if null, a {@link NullPointerException} will be thrown
     * @param taskType the task type that this task represents
     * @param <T>      the type of the task result
     * @return a new Task instance
     */
    public static <T> Task<T> createTask(Callable<T> callable, TaskType taskType) {
        return new Task<>(callable, taskType);
    }

    /**
     * creates a new Task instance with the given callable and default taskType OTHER
     *
     * @param callable the callable task being wrapped, if null, a {@link NullPointerException} will be thrown
     * @param <T>      the type of the task result
     * @return a new Task instance
     */
    public static <T> Task<T> createTask(Callable<T> callable) {
        return new Task<>(callable, TaskType.OTHER);
    }

    /**
     * get the task type of the task
     *
     * @return TaskType of the task
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * get the priority value of the task
     *
     * @return int value of the task priority
     */
    public int getPriority() {
        return this.taskType.getPriorityValue();
    }

    @Override
    public T call() throws Exception {
        return callable.call();
    }

    /**
     * compare the task to other task by their task type priority
     *
     * @param other the task to be compared
     * @return -1 if this task has higher priority, 1 if this task has lower priority, 0 if they have the same priority
     */
    @Override
    public int compareTo(Task<T> other) {
        return Integer.compare(taskType.getPriorityValue(), other.taskType.getPriorityValue());
    }

    /**
     * overrides the toString method to add the task type property to the string representation
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        String s = super.toString();
        return s.substring(s.indexOf('['), s.indexOf(']')) + ", task type = " + this.taskType + "]";
    }
}
