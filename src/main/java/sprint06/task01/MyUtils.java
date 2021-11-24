package sprint06.task01;

import java.util.Arrays;
import java.util.function.Predicate;

public class MyUtils {

    public static int getCount(int[] array, Predicate<Integer> predicate) {
        return (int)Arrays.stream(array).filter(n -> predicate.test(n)).count();
    }

    public static void main(String[] args) {
        System.out.println(getCount(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, p -> p <= 2));
    }
}
