package es.jaime.javaddd.application.utils;

import java.util.concurrent.locks.Lock;

public final class ConcurrencyUtils {
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
