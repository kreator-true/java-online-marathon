package sprint02;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class task04 {
    public static void main(String[] args) {
        Employee emp = new Employee("Ivan", 10, new BigDecimal(3000.00));
        Manager emp1 = new Manager("Vania", 4, new BigDecimal(1200.00), 1.6);
        Manager emp10 = new Manager("Vania", 4, new BigDecimal(1200.00), 1.6);
        Manager emp6 = new Manager("Boris", 4, new BigDecimal(1200.00), 1.6);
        Employee emp2 = new Employee("Anna", 2, new BigDecimal(1000.00));
        Manager emp3 = new Manager("Egor", 1, new BigDecimal(1000.00), 1.1);
        Manager emp7 = new Manager("Anna", 10, new BigDecimal(1000.00), 1.1);
        Employee emp4 = new Employee("Max", 10, new BigDecimal(3000.00));
        Employee emp5 = new Employee("Danil", 10, new BigDecimal(3000.00));

        List<Employee> workers = List.of(emp1, emp, emp2, emp3, emp4, emp5, emp7, emp6, emp, emp, emp10);
        MyUtils2  my = new MyUtils2();

        print(my.largestEmployees(workers));

        print(my.largestEmployees(null));
        print(my.largestEmployees(new ArrayList<>()));

        print(my.largestEmployees(List.of(emp1, emp1, emp1)));
        print(my.largestEmployees(List.of(emp, emp4, emp5)));
    }

    public static void print(List<?> list){
        if(list == null) {System.out.println("NULL"); return;};
        if(list.isEmpty()) {System.out.println("isEmpty"); return;};

        list.stream().forEach(System.out::println);
    }
}

class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name;
        this.experience = experience;
        this.basePayment = basePayment;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public BigDecimal getPayment() {
        return basePayment.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience, basePayment);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Employee)) return false;
        if(obj == this) return  true;
        Employee employee = (Employee) obj;
        return getName().equals(employee.getName()) && getExperience() == employee.getExperience() && getPayment().equals(employee.getPayment());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", basePayment=" + basePayment +
                '}';
    }
}
class Manager extends Employee {
    private double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience, basePayment);
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public BigDecimal getPayment() {
        BigDecimal payment = super.getPayment().multiply(new BigDecimal(coefficient)).setScale(2, RoundingMode.HALF_UP);

        return payment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPayment(), getExperience(), getCoefficient());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Manager)) return false;
        if(obj == this) return  true;
        Manager manager = (Manager) obj;
        return getName().equals(manager.getName()) && getExperience() == manager.getExperience()
                && getPayment().equals(manager.getPayment()) && getCoefficient() == manager.getCoefficient();
    }
    @Override
    public String toString() {
        return "Manager{" +
                "name='" + getName() + '\'' +
                ", experience=" + getExperience() +
                ", basePayment=" + getPayment() +
                ", cooficient=" + getCoefficient() +
                '}';
    }

}
class MyUtils2 {
    private BigDecimal payment;
    private List<Employee> result;
    public List<Employee> largestEmployees(List<Employee> workers) {
        if(workers == null) return null;
        if(workers.isEmpty()) return new ArrayList<>();
        if(workers.size() == 1) return workers;

        payment = new BigDecimal(0);
        result = new ArrayList<>();

        List<Employee> employees = new ArrayList<>();
        List<Employee> managers = new ArrayList<>();

        Comparator<Employee> comparatorExp = Comparator.comparingInt(Employee::getExperience).reversed();
        Comparator<Employee> comparatorPayment = Comparator.comparing(Employee::getPayment).reversed();

        workers.stream().forEach(worker -> {
            if(worker instanceof Employee) employees.add(worker);
            if(worker instanceof Manager) managers.add(worker);
        });

        Collections.sort(employees, comparatorPayment);
        Collections.sort(managers, comparatorPayment);

        addResultList(employees);
        addResultList(managers);

        Collections.sort(result, comparatorExp);

        return result.stream().distinct().collect(Collectors.toList());
    }
    private void addResultList(List<Employee> employees){
        if(employees.size() == 0) return;
        if (employees.size() == 1) {
            result.addAll(employees);
            return;
        }
        payment = employees.get(0).getPayment();
        for(Employee e : employees)
            if(e.getPayment().equals(payment)) result.add(e);
    }
}
