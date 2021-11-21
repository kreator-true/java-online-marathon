package sprint05.task05;


public class InsufficientAmountException extends Exception {
    private double needs;

    public InsufficientAmountException(double needs) {
        super("Sorry, but you are short $" + needs);
        this.needs = needs;
    }

    public double getAmount() {
        return needs;
    }

}

