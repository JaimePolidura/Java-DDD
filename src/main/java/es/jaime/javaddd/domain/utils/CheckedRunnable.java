package es.jaime.javaddd.domain.utils;

@FunctionalInterface
public interface CheckedRunnable {
    void run() throws Exception;
}
