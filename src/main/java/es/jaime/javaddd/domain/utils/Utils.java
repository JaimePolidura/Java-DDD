package es.jaime.javaddd.domain.utils;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class Utils {
    private Utils() {}

    @SafeVarargs
    public static <E> List<E> toList(List<E> ...elements){
        List<E> toReturn = new ArrayList<>();

        for (List<E> element : elements) {
            toReturn.addAll(element);
        }

        return toReturn;
    }

    public static void repeat(double times, Runnable runnable) {
        for (int i = 0; i < times; i++)
            runnable.run();
    }

    public static void repeat(double times, Consumer<Integer> consumer) {
        for (int i = 0; i < times; i++)
            consumer.accept(i);
    }



    public static void repeat(int times, Runnable runnable){
        for (int i = 0; i < times; i++)
            runnable.run();
    }

    @SneakyThrows
    public static void sleep(long millis) {
        Thread.sleep(millis);
    }
}
