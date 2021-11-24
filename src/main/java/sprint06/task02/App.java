package sprint06.task02;

import java.util.Arrays;
import java.util.function.Consumer;

public class App {
     static Consumer<double[]> cons = array -> {
        for (int i = 0; i < array.length; i++){
            if (array[i] > 2) array[i] *= 0.8;
            else array[i] *= 0.9;
        }
    };

    public static double[] getChanged(double[] initialArray, Consumer<double[]> consumer) {
        if (initialArray == null) return new double[]{};
        if (initialArray.length == 0) return initialArray;

        double[] resultArray = Arrays.copyOf(initialArray, initialArray.length);
        consumer.accept(resultArray);
        return resultArray;
    }

    public static void main(String[] args) {
        Arrays.stream(getChanged(new double[]{1, 2, 3, 5.01, 8.3, 9.06}, cons)).forEach(System.out::println);
        Arrays.stream(getChanged(null, cons));

    }
}
