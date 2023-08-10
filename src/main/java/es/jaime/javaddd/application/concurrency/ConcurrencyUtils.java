package es.jaime.javaddd.application.concurrency;

import es.jaime.javaddd.application.utils.Utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;

public final class ConcurrencyUtils {
    public static void atomicAcquire(Lock... locks) {
        Queue<Lock> acquired = new LinkedList<>();

        while (acquired.size() != locks.length) {
            Lock nextLock = locks[acquired.size()];

            if(nextLock.tryLock()){
                acquired.add(nextLock);
            }else{
                releaseAll(acquired);

                Utils.sleep((long) (Math.random() * 10));
            }
        }
    }

    private static void releaseAll(Queue<Lock> acquired) {
        while (!acquired.isEmpty()) {
            acquired.poll().unlock();
        }
    }

    public static void tryLockOnce(Lock lock, Runnable runnable) {
        if(!lock.tryLock()){
            return;
        }

        try {
            runnable.run();
        }finally {
            lock.unlock();
        }
    }
}
