package Part2.TestClasses;

import Part2.*;

public class myTest {
    public static void main(String[] args) {


        CustomExecutor customExecutor = new CustomExecutor();
//        customExecutor.setCorePoolSize(1);
//        customExecutor.setMaximumPoolSize(1);


        var task = Task.createTask(() -> {
            System.out.println(customExecutor + "\tCOMPUTATIONAL=1");
            return null;
        }, TaskType.COMPUTATIONAL);

        var task2 = Task.createTask(() -> {
            System.out.println(customExecutor + "\tIO=2");
            return null;
        }, TaskType.IO);

        var task3 = Task.createTask(() -> {
            System.out.println(customExecutor + "\tOTHER=3");
            return null;
        }, TaskType.OTHER);


        customExecutor.submit(task3);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);

        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);

        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);

        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);
        customExecutor.submit(task3);
        customExecutor.submit(task);
        customExecutor.submit(task2);

        customExecutor.gracefullyTerminate();

    }
}
