package sprint04.task05;



class Main {
    public static void main(String[] args) {
        Array<Integer> set1 = new Array<>(new Integer[]{1, 2, 4, 5});
        double averageValue1 = ArrayUtil.averageValue(set1);
        System.out.println(averageValue1);

        Array<Double> set2 = new Array<>(new Double[]{1.0, 2.0, 5.0});
        double averageValue2 = ArrayUtil.averageValue(set2);
        System.out.println(averageValue2);

    }
}
