package Part2;

import java.util.Objects;
import java.util.concurrent.*;

/**
 * CustomExecutor is an implementation of the {@link Executor} interface that is responsible for executing {@link Task} instances asynchronously.
 * The CustomExecutor uses a thread pool and a priority queue to execute tasks.
 * <p>
 * The CustomExecutor allows users to submit tasks to it in different forms:
 * {@link #submit(Task)}: submit a Task instance
 * {@link #submit(Callable, TaskType)}: submit an operation that may return a value with a specific task type. It will then be used for creating a Task instance
 * {@link #submit(Callable)}: submit an operation that may return a value. It will then be used for creating a Task instance with default task type OTHER
 * <p>
 * The thread pool is set to keep half the number of processors available for the JVM and to allow the number of processors available for the JVM minus 1,
 * excess idle threads will wait 300 milliseconds for new tasks before terminating.
 * <p>
 * The CustomExecutor maintain the maximum priority of Task instances in the queue at any given time by {@link #getCurrentMax()} in O(1) time & space complexity.
 * The CustomExecutor also provides {@link #gracefullyTerminate()} method that can be used to wait for the termination of all tasks and then terminate the executor
 */
public class CustomExecutor extends ThreadPoolExecutor {
    private int maxPriority;

    /**
     * Creates a new CustomExecutor instance with the following configuration:
     * - Initial number of threads equal to half of the number of processors available to the JVM
     * - Maximum number of threads equal to the number of processors available to the JVM minus 1
     * - Keep-alive time for excess idle threads of 300 milliseconds
     * - The internal queue will be a PriorityBlockingQueue that orders tasks according to their natural order.
     */
    public CustomExecutor() {
        super((Runtime.getRuntime().availableProcessors()) / 2, (Runtime.getRuntime().availableProcessors()) - 1,
                300L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
    }

    /**
     * Creates a new task with the given task object
     *
     * @param task the task to be wrapped
     * @param <T>  The type of the task result
     * @return a new task
     */
    protected <T> Task<T> createTask(Task<T> task) {
        return new Task<>(task, task.getTaskType());
    }

    /**
     * Submit a task to the executor.
     *
     * @param task The task to be executed
     * @return Future object representing the task
     * @throws NullPointerException if task is null
     */
    public <T> Future<T> submit(Task<T> task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException();
        }
        Task<T> t = createTask(task);
        execute(t);
        return t;
    }

    /**
     * Submit a callable task to the executor with a specific task type.
     * This will be used for creating a Task instance
     *
     * @param callable the callable task being wrapped
     * @param taskType the taskType for the task
     * @param <T>      The type of the task result
     * @return Future object representing the task
     * @throws NullPointerException if callable or taskType is null
     */
    public <T> Future<T> submit(Callable<T> callable, TaskType taskType) {
        if (callable == null || taskType == null) {
            throw new NullPointerException();
        }
        return submit(Task.createTask(callable, taskType));
    }

    /**
     * Submit a callable task to the executor with default task type OTHER
     *
     * @param callable the callable task being wrapped
     * @param <T>      The type of the task result
     * @return Future object representing the task
     * @throws NullPointerException if callable is null
     */
    public <T> Future<T> submit(Callable<T> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        return submit(Task.createTask(callable));
    }

    /**
     * This method is called after the execution of a task, to update the maxPriority variable
     * with the current max priority in the queue.
     *
     * @param runnable  the task that has been executed
     * @param throwable any exception that was thrown by the task
     */
    @Override
    protected void afterExecute(Runnable runnable, Throwable throwable) {
        if (!super.getQueue().isEmpty()) {
            if (((Task<?>) runnable).getPriority() < ((Task<?>) Objects.requireNonNull(super.getQueue().peek())).getPriority()) {
                setMaxPriority(((Task<?>) Objects.requireNonNull(super.getQueue().peek())).getPriority());
            }
        } else {
            setMaxPriority(0);
        }
    }

    /**
     * Set the maximum priority of the tasks in the queue
     *
     * @param maxPriority the maximum priority
     */
    public void setMaxPriority(int maxPriority) {
        this.maxPriority = maxPriority;
    }

    /**
     * Returns the maximum priority in the queue at any given time.
     *
     * @return the maximum priority of the tasks in the queue
     */
    public int getCurrentMax() {
        return this.maxPriority;
    }

    /**
     * Waits for the termination of all tasks and then terminate the executor
     */
    public void gracefullyTerminate() {
        super.shutdown();
    }

    /**
     * Returns a string representation of the CustomExecutor object and it's maxPriority.
     * The string representation consists of the string representation of the super class
     * ThreadPoolExecutor and the value of the maxPriority.
     *
     * @return A string representation of the CustomExecutor object
     */
    @Override
    public String toString() {

        String s = super.toString();
        return s.substring(s.indexOf('['), s.indexOf(']')) + ", max priority = " + getCurrentMax() + "]";
    }
}

