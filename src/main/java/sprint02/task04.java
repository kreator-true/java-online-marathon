package sprint02;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class task04 {
    public static void main(String[] args) {

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
        return super.getPayment().multiply(new BigDecimal(coefficient)).setScale(2, RoundingMode.HALF_UP);
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
    private int exp;
    private List<Employee> result;

    public List<Employee> largestEmployees(List<Employee> workers) {
        if(workers == null) return null;
        if(workers.isEmpty()) return new ArrayList<>();
        if(workers.size() == 1) return workers;

        payment = new BigDecimal(0);
        result = new ArrayList<>();
        exp = 0;

        List<Employee> employees = new ArrayList<>();
        List<Employee> managers = new ArrayList<>();

        Comparator<Employee> comparatorExp = Comparator.comparingInt(Employee::getExperience).reversed();
        Comparator<Employee> comparatorPayment = Comparator.comparing(Employee::getPayment).reversed();

        workers.stream().forEach(worker -> {
            if(worker instanceof Manager) managers.add(worker);
            else employees.add(worker);
        });

        Collections.sort(employees, comparatorPayment);
        Collections.sort(managers, comparatorPayment);

        addResultListForPayment(employees);
        addResultListForPayment(managers);

        Collections.sort(employees, comparatorExp);
        Collections.sort(managers, comparatorExp);

        addResultListForExp(employees);
        addResultListForExp(managers);

        return result.stream()
                .distinct()
                .sorted(comparatorPayment.thenComparing(comparatorExp))
                .collect(Collectors.toList());
    }
    private void addResultListForPayment(List<Employee> employees){
        if (chekList(employees)) return;

        payment = employees.get(0).getPayment();
        for(Employee e : employees)
            if(e.getPayment().equals(payment)) result.add(e);
    }
    private void addResultListForExp(List<Employee> employees){
        if (chekList(employees)) return;

        exp = employees.get(0).getExperience();
        for(Employee e : employees)
            if(e.getExperience() == exp) result.add(e);
    }

    private boolean chekList(List<Employee> employees){
        if(employees.size() == 0) return true;
        if (employees.size() == 1) {
            result.addAll(employees);
            return true;
        }
        return false;
    }
}