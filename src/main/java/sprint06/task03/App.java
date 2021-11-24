package sprint06.task03;

import java.util.function.BinaryOperator;
import java.util.List;
import java.util.stream.Collectors;

class App {
    static BinaryOperator<String> greetingOperator = (s, s2) -> "Hello " + s + " " + s2 + "!!!";

    public static List<String> createGreetings(List<Person> people, BinaryOperator<String> binaryOperator) {
        return people.stream().map(person -> binaryOperator.apply(person.name, person.surName)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(createGreetings(List.of(new Person("name", "surname")), greetingOperator));
    }
}
