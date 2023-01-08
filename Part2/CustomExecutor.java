package Part2;

import java.util.concurrent.*;

import java.util.concurrent.*;

public class CustomExecutor {
    private static final int NUM_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final int MIN_THREADS = NUM_PROCESSORS / 2;
    private static final int MAX_THREADS = NUM_PROCESSORS - 1;
    private static final int IDLE_TIMEOUT = 3000; // 3 seconds

    private final BlockingQueue<Runnable> taskQueue;
    private final ThreadPoolExecutor executor;
    private final RejectedExecutionHandler rejectionHandler;
    private volatile boolean isShutdown;

    public CustomExecutor() {
        // Use a PriorityBlockingQueue to store tasks, with the tasks sorted by priority
        taskQueue = new PriorityBlockingQueue<>();

        // Create a ThreadPoolExecutor with a custom ThreadFactory that creates daemon threads
        executor = new ThreadPoolExecutor(MIN_THREADS, MAX_THREADS, IDLE_TIMEOUT, TimeUnit.MILLISECONDS, taskQueue, new DaemonThreadFactory());

        // Set a custom RejectedExecutionHandler to log rejected tasks
        rejectionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("Task rejected: " + r.toString());
            }
        };

        isShutdown = false;
    }

    // Method for submitting Task instances to the priority task queue
    public <T> Future<T> submit(Task<T> task) {
        if (isShutdown) {
            throw new RejectedExecutionException("Executor has been shut down");
        }
        return executor.submit(task.getTask());
    }

    // Method for submitting an operation that can be performed asynchronously with the addition of TaskType
    public <T> Future<T> submit(Callable<T> task, TaskType type) {
        if (isShutdown) {
            throw new RejectedExecutionException("Executor has been shut down");
        }
        return submit(Task.createTask(task, type));
    }

    // Method for submitting an operation that can be performed asynchronously without TaskType as a parameter
    public <T> Future<T> submit(Callable<T> task) {
        if (isShutdown) {
            throw new RejectedExecutionException("Executor has been shut down");
        }
        return submit(Task.createTask(task));
    }


    // Returns the highest priority of a task found in the queue at the moment
    public int getCurrentMax() {
        // Make a copy of the taskQueue to avoid ConcurrentModificationException
        var queueCopy = new PriorityBlockingQueue<>(taskQueue);
        int maxPriority = Integer.MIN_VALUE;
        while (!queueCopy.isEmpty()) {
            var task = queueCopy.poll();
            if (task instanceof Task) {
                maxPriority = Math.max(maxPriority, ((Task) task).getPriorityValue());
            }
        }
        // Check if there are any tasks being executed by the threads in the thread pool
        if (executor.getPoolSize() > executor.getCompletedTaskCount()) {
            // Set the max priority to the maximum value of Integer
            maxPriority = Integer.MAX_VALUE;
        }
        return maxPriority;
    }




    // Terminates the activity of the CustomExecutor instance
// - Does not allow the introduction of additional tasks to the queue
// - Completes all tasks remaining in the queue
// - Terminates all the tasks that are currently being executed in the collection of threads of the CustomExecutor
    public void gracefullyTerminate() {
        isShutdown = true;
        executor.shutdown();
        try {
            if (!executor.awaitTermination(IDLE_TIMEOUT, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    private static class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            var t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    }
}