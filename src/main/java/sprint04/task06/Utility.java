package sprint04.task06;

import java.util.Arrays;
import java.util.Comparator;


class Utility {
    public static <T extends Person> void sortPeople(T[] people, Comparator<? super T> comparator){
        Arrays.sort(people, comparator);
    }
}

class PersonComparator implements Comparator<Person> {
    protected int result;
    @Override
    public int compare(Person o1, Person o2) {
        result = o1.name.compareTo(o2.name);
        if (result == 0) result = o1.age - o2.age;
        return result;
    }
}

class EmployeeComparator extends PersonComparator{
    @Override
    public int compare(Person o1, Person o2) {
        Employee emp1 = (Employee)o1;
        Employee emp2 = (Employee)o2;

        result = super.compare(o1, o2);
        if(result == 0) result = (int)(emp1.getSalary() - emp2.getSalary());
        return result;
    }
}

class DeveloperComparator extends EmployeeComparator{
    @Override
    public int compare(Person o1, Person o2) {
        Developer dev1 = (Developer)o1;
        Developer dev2 = (Developer)o2;

        result = super.compare(o1, o2);
        if(result == 0) result = dev1.getLevel().ordinal() - dev2.getLevel().ordinal();
        return result;
    }
}
