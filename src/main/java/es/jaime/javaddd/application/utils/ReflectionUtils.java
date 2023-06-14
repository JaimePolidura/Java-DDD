package es.jaime.javaddd.application.utils;

import es.jaime.javaddd.domain.exceptions.ResourceNotFound;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public final class ReflectionUtils {
    private ReflectionUtils() {}

    public static Optional<Constructor<?>> getSmallestConstructor(Class<?> classToGetConstructor){
        return Arrays.stream(classToGetConstructor.getConstructors())
                .min(Comparator.comparingInt(Constructor::getParameterCount));
    }

    public static boolean hasInterfaceWithGenericType(Class<?> toCheck, Class<?> typeToCheck) {
        return Arrays.stream(toCheck.getGenericInterfaces())
                .map(genericInterface -> (ParameterizedType) genericInterface)
                .map(ParameterizedType::getActualTypeArguments)
                .flatMap(Arrays::stream)
                .map(generic -> (Class<?>) generic)
                .anyMatch(generic -> generic.equals(typeToCheck));
    }

    public static List<Class<?>> getAbstractions(Class<?> implementation) {
        Class<?> superClass = implementation.getSuperclass();
        Class<?>[] interfaces = implementation.getInterfaces();
        List<Class<?>> abstractions = new ArrayList<>(interfaces.length + 1);
        abstractions.addAll(Arrays.asList(interfaces));
        if(superClass != null) abstractions.add(superClass);

        return abstractions;
    }

    public static boolean isImplementation(Class<?> classToCheck) {
        Class<?> classExtends = classToCheck.getSuperclass();
        boolean extendsAbstraction = classExtends != null && isAbstraction(classExtends);
        boolean implementsInterface = classToCheck.getInterfaces().length > 0;

        return implementsInterface || extendsAbstraction;
    }

    public static boolean isAbstraction(Class<?> classToCheck) {
        return classToCheck.isInterface() || Modifier.isAbstract(classToCheck.getModifiers());
    }

    public static List<Annotation> findAnnotationsInClass(Class<?> toCheck) {
        return new ArrayList<>(Arrays.asList(toCheck.getDeclaredAnnotations()));
    }

    public static Optional<Annotation> findAnnotationIn(Class<?> classToCheck, Class<? extends Annotation> annotation) {
        return Arrays.stream(classToCheck.getDeclaredAnnotations())
                .filter(a -> a.annotationType().equals(annotation))
                .findFirst();
    }

    public static boolean isAnnotatedWith(Class<?> classToCheck, Set<Class<? extends Annotation>> annotations){
        for (Annotation declaredAnnotation : classToCheck.getDeclaredAnnotations())
            for (Class<? extends Annotation> annotation : annotations)
                if(declaredAnnotation.annotationType().equals(annotation))
                    return true;

        return false;
    }

    public static List<Class<?>> findInterfacesInClass(Class<?> clazz) {
        return new ArrayList<>(Arrays.asList(clazz.getInterfaces()));
    }

    public static <I, O> O getFieldValue(I instance, String fieldName) throws IllegalAccessException {
        Field field = getAllFields(instance.getClass()).stream()
                .filter(f -> f.getName().equalsIgnoreCase(fieldName))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFound(String.format("Field %s not foun for %s", fieldName, instance.getClass().getName())));

        field.setAccessible(true);

        return (O) field.get(instance);
    }

    public static List<Field> getAllFields(Class<?> actualClass) {
        List<Field> fields = new ArrayList<>();

        while (actualClass != Object.class) {
            fields.addAll(Arrays.asList(actualClass.getDeclaredFields()));
            actualClass = actualClass.getSuperclass();
        }

        return fields;
    }
}
