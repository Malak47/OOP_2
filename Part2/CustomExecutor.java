package Part2;

import java.util.Objects;
import java.util.concurrent.*;

public class  CustomExecutor extends ThreadPoolExecutor {
    private int maxPriority;


    public CustomExecutor() {
        super((Runtime.getRuntime().availableProcessors()) / 2, (Runtime.getRuntime().availableProcessors()) - 1,
                300L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
    }

    protected <T> Task<T> createTask(Task<T> task) {
        return new Task<>(task,task.getTaskType());
    }


    public <T> Future<T> submit(Task<T> task) throws NullPointerException {
        if (task == null) throw new NullPointerException();
        Task<T> t = createTask(task);
        execute(t);
        return t;
    }

    public <T> Future<T> submit(Callable<T> callable, TaskType taskType) {
        if (callable == null || taskType == null) {
            throw new NullPointerException();
        }
        return submit(Task.createTask(callable, taskType));
    }

    public <T> Future<T> submit(Callable<T> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        return submit(Task.createTask(callable));
    }


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

    public void setMaxPriority(int maxPriority) {
        this.maxPriority = maxPriority;
    }

    public int getCurrentMax() {
        return this.maxPriority;
    }


    public void gracefullyTerminate() {
        super.shutdown();
    }

    @Override
    public String toString() {

        String s = super.toString();
        return s.substring(s.indexOf('['), s.indexOf(']')) + ", max priority = " + getCurrentMax() + "]";
    }
}

