package Part2;

import java.util.concurrent.Callable;

class Task<V> implements Callable<V> {

    @Override
    public synchronized V call() throws Exception {
        return null;
    }
}
