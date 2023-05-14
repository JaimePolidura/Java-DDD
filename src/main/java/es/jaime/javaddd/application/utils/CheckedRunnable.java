package es.jaime.javaddd.application.utils;

@FunctionalInterface
public interface CheckedRunnable {
    void run() throws Exception;
}
