package sprint07.task07;


import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class MyUtils {
    public Stream<Integer> duplicateElements(Stream<Integer> stream) {
        return stream.filter(integer -> integer != null)
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= 2)
                .map(Map.Entry::getKey)
                .sorted();

    }

    public static void main(String[] args) {
        new MyUtils().duplicateElements(Stream.of(1, -1, 1, -1, 3, 4, null, 6, 7, 4)).forEach(System.out::println);
    }
}
