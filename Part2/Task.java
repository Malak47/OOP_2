package Part2;

import java.util.concurrent.Callable;

public class Task<T> implements Comparable<Task> {
    private final Callable<T> task;
    private final TaskType type;
    private int priority;

    private Task(Callable<T> task, TaskType type) {
        this.task = task;
        this.type = type;
        this.priority = type.getPriorityValue();
    }

    public static <T> Task<T> createTask(Callable<T> task, TaskType type) {
        return new Task<>(task, type);
    }

    public static <T> Task<T> createTask(Callable<T> task) {
        // Set the default TaskType to COMPUTATIONAL
        return new Task<>(task, TaskType.COMPUTATIONAL);
    }

    public Callable<T> getTask() {
        return task;
    }

    public TaskType getType() {
        return type;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriorityValue() {
        return priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
}


