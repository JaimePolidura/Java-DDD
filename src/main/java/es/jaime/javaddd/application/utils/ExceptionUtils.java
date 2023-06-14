package es.jaime.javaddd.application.utils;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public final class ExceptionUtils {
    public static <T> T rethrow(Callable<T> toRun, Supplier<? extends RuntimeException> thrower) {
        try{
            return toRun.call();
        }catch (Exception e){
            throw thrower.get();
        }
    }

    public static void ignoreException(CheckedRunnable checkedRunnable) {
        try{
            checkedRunnable.run();
        }catch (Exception ignored) {

        }
    }

    public static void ignoreExceptionButPrint(CheckedRunnable runnable) {
        try{
            runnable.run();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runCheckedOrTerminate(CheckedRunnable checkedRunnable) {
        try {
            checkedRunnable.run();
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static <T> T rethrowChecked(Callable<T> runnable) {
        try{
            return runnable.call();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void rethrowChecked(CheckedRunnable runnable) {
        try{
            runnable.run();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
