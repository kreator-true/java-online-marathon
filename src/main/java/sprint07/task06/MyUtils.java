package sprint07.task06;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class MyUtils {
    public static Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) {
        if(list.isEmpty() || list == null) return Map.of();

        return list.stream()
                .filter(stream -> stream != null)
                .flatMap(stream -> stream
                        .filter(s -> s != null && !s.isEmpty())
                        .map(MyUtils::extractNumber))
                .filter(s -> !s.isEmpty())
                .collect(toMap(s -> s.substring(0, 3), s -> Stream.of(s.substring(3)), (o, o2) -> Stream.concat(o, o2).sorted().distinct()));

        /*return list.stream()
                .filter(stream -> stream != null)
                .flatMap(stream -> stream
                        .filter(s -> s != null)
                        .filter(s -> !s.isEmpty())
                        .map(MyUtils::extractNumber))
                .sorted()
                .collect(groupingBy(s -> s.substring(0, 3), mapping(s -> s.substring(3), toSet())))
                .entrySet().stream()
                .collect(toMap(entry -> entry.getKey(), entry -> entry.getValue().stream()));*/
    }

    static String extractNumber(String number){
        String result = number.replaceAll("[( )-]", "");
        int length = result.length();
        if(!result.isEmpty()) {
            if (length == 7) return "loc" + result;
            if (length < 7 || (length > 7 && length < 10) || length > 10) return "err" + result;
        }
        return result;
    }

    public static void main(String[] args) {
        phoneNumbers(List.of(
                Stream.of("093 987 65 43", "(050)1234567", "12-345"),
                Stream.of( "050-2345678", "0939182736", "224-19-28"),
                Stream.of("(093)-11-22-334", "044 435-62-18", "721-73-45"),
                Stream.of("12345678", "435-218", "72173145"),
                Stream.of("()-", null, "", "23", "123")
        )).forEach((s, stream) -> System.out.println(s + stream.toList()));
    }
}