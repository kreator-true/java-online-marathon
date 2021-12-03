package sprint08.task02;

import sprint08.task01.ParallelCalculator;

class Accountant {
    public static int sum(int x, int y){
        ParallelCalculator calculator = new ParallelCalculator(Integer::sum, x, y);
        Thread thread = new Thread(calculator);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return calculator.result;
    }

    public static void main(String[] args) {
        System.out.println(sum(10, 6));
    }
}