package sprint08.task01;

import java.util.function.BinaryOperator;

public class ParallelCalculator implements Runnable{
    private BinaryOperator<Integer> operator;
    private int operand1;
    private int operand2;
    public int result;

    public ParallelCalculator(BinaryOperator<Integer> operator, int operand1, int operand2) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public void run() {
        result = operator.apply(operand1, operand2);
    }
}
