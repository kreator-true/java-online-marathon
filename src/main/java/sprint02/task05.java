package sprint02;

import java.util.ArrayList;
import java.util.List;

public class task05 {
    public static void main(String[] args) {
        Square s1 = new Square(5);
        Square s2 = new Square(2);
        Rectang r1 = new Rectang(4,3);
        Rectang r2 = new Rectang(5, 4);
        System.out.println(new MyUtils4().sumPerimeter(List.of(s1, s2, r1, r2)));
    }
}
class Rectang extends Square{
    private double height;

    public Rectang(double width, double height) {
        super(width);
        this.height = height;
    }

    @Override
    public double getPerimeter() {
        return (getWidth() + height) * 2;
    }
}
class Square {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getPerimeter(){
        return width * 4.0;
    }
}
class MyUtils4 {
    public double sumPerimeter(List<?> firures) {
        if(firures == null || firures.isEmpty()) return 0;
        List<Double> allperimetr = new ArrayList<>();
        firures.stream()
                .filter(firure -> firure instanceof Square || firures instanceof Rectang)
                .distinct()
                .forEach(firure -> allperimetr.add(((Square)firure).getPerimeter()));
        return allperimetr.stream().mapToDouble(Double::doubleValue).sum();
    }
}
