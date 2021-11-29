package sprint07.task05;



import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyUtils {
    public static Stream<String> nameList(Map<String, Stream<String>> map) {
        if (map == null) throw new NullPointerException();

        return map.values().stream()
                .filter(stream -> stream != null)
                .flatMap(stream -> stream
                        .filter(s -> s != null && !s.isEmpty())
                        .map(s -> s.replaceAll(" ", "").toLowerCase()))
                .filter(s -> !s.isEmpty())
                .distinct()
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .sorted();
    }

    public static void main(String[] args) {
        nameList(Map.of("Desktop", Stream.of("Anna", "Marina", "Nikolay"),
                "Web", Stream.of("Anna", "Petro", "Nikolay"),
                "Spring", Stream.of("Galina", "", "Nikolay"),
                "Android", Stream.of("an na", null, "Alina"))).forEach(System.out::println);
    }


}
