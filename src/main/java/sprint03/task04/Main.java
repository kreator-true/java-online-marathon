package sprint03.task04;

public class Main {

    public static void main(String[] args) {
        System.out.println(drawLine(LineType.DOTTED));
    }
    public static String drawLine(LineType lineType) {
        return String.format("The line is %s type", lineType.getName());
    }
}
