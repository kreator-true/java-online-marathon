package sprint02;

import java.util.*;
import java.util.stream.Collectors;

public class task06 {
    public static void main(String[] args) {
        Circle c1 = new Circle("Circle", 3.0);
        Circle c2 = new Circle("Circle", 2.0);
        Circle c3 = new Circle("Circle", 3.0);
        Rectangle r1 = new Rectangle("Ractanfle", 1.0, 3.0);
        Rectangle r2 = new Rectangle("Ractanfle", 2.0, 3.0);
        Rectangle r3 = new Rectangle("Ractanfle", 3.0, 2.0);

        new MyUtils6().maxAreas(List.of(c1, c2, c3, r1, r2, r3)).forEach(System.out::println);

    }
}
abstract class Shape {
    private String name;

    public Shape(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    abstract double getFigureArea();

    public double getArea(){
        return getFigureArea();
    }


}
class Circle extends Shape {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), radius);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Circle)) return false;
        if(this == obj) return true;
        Circle circle = (Circle) obj;
        return getName().equals(circle.getName()) && radius == circle.getRadius();
    }

    @Override
    public double getFigureArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
class Rectangle extends Shape {
    private double height;
    private double width;

    public Rectangle(String name, double height, double width) {
        super(name);
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), height, width);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Rectangle)) return false;
        if(this == obj) return true;
        Rectangle rectangle = (Rectangle) obj;
        return getName().equals(rectangle.getName()) && height == rectangle.getHeight() && width == rectangle.getWidth();
    }

    @Override
    double getFigureArea() {
        return height * width;
    }
}
class MyUtils6 {
    public List<Shape> maxAreas(List<Shape> shapes) {
        if(shapes == null) return null;
        if(shapes.isEmpty()) return new ArrayList<>();
        if(shapes.size() == 1) return shapes;

        List<Shape> resultList = new ArrayList<>();
        Comparator<Shape> comparatorArea = Comparator.comparing(shape -> shape.getArea());
        List<Shape> circleList = new ArrayList<>();
        List<Shape> rectangleList = new ArrayList<>();
        shapes.stream().forEach(shape -> {
            if(shape instanceof Circle) circleList.add(shape);
            if(shape instanceof Rectangle) rectangleList.add(shape);
        });
        Collections.sort(circleList, comparatorArea.reversed());
        Collections.sort(rectangleList, comparatorArea.reversed());
        double maxCircleArea = circleList.get(0).getArea();
        double maxRectangleArea = rectangleList.get(0).getArea();
        for(Shape shape : circleList)
            if(shape.getArea() == maxCircleArea) resultList.add(shape);

        for(Shape shape : rectangleList)
            if(shape.getArea() == maxRectangleArea) resultList.add(shape);

        return resultList.stream().distinct().collect(Collectors.toList());
    }
}
