import java.util.*;
import java.util.function.*;

public class GenericMethods {

    public static <T, P> List<P> map(List<T> list, Function<T, P> function) {
        if (list == null || function == null) throw new IllegalArgumentException();
        List<P> result = new ArrayList<>();
        for (T item : list) result.add(function.apply(item));
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        if (list == null || predicate == null) throw new IllegalArgumentException();
        List<T> result = new ArrayList<>();
        for (T item : list) if (predicate.test(item)) result.add(item);
        return result;
    }

    public static <T> T reduce(List<T> list, T identity, BinaryOperator<T> operator) {
        if (list == null || operator == null) throw new IllegalArgumentException();
        T result = identity;
        for (T item : list) result = (result == null ? item : operator.apply(result, item));
        return result;
    }

    public static <T, P> P collect(List<T> list, Supplier<P> collectionFactory, BiConsumer<P, T> accumulator) {
        if (list == null || collectionFactory == null || accumulator == null) throw new IllegalArgumentException();
        P collection = collectionFactory.get();
        for (T item : list) accumulator.accept(collection, item);
        return collection;
    }
}
