package sprint03.task03;

public class Main {

    public static void main(String[] args) {
        multiplyAbyB(109 ,20);
    }

    public static void execute(int a, int b, Strategy strategy){
        double result = strategy.doOperation(a, b);
        System.out.println(result);
    }

    public static void addAtoB(int a, int b) {
        execute(a, b, (a1, b1) -> a1 + b1);
    }

    public static void subtractBfromA(int a, int b) {
        execute(a, b, (a1, b1) -> a1 - b1);
    }

    public static void multiplyAbyB(int a, int b) {
        execute(a, b, (a1, b1) -> a1 * b1);
    }

    public static void divideAbyB(int a, int b) {
        execute(a, b, (a1, b1) -> a1 / b1);
    }

}
