package sprint04.task06;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Person[] people = {
                new Person("person1", 20),
                new Person("person2", 21),
                new Person("person3", 22),
                new Person("person4", 23),
                new Employee("employee1", 24, 222.2),
                new Employee("employee2", 25, 222.3),
                new Developer("developer1", 26, 333.1, Level.JUNIOR),
                new Developer("developer2", 27,444.1, Level.MIDDLE),
                new Employee("employee3", 19, 222.4),
                new Developer("developer3", 25, 666.6, Level.SENIOR),
        };

        Utility.sortPeople(people, new PersonComparator());

        Arrays.stream(people).forEach(System.out::println);

    }
}
