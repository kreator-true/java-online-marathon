package sprint02;

import java.util.*;
import java.util.stream.Collectors;

public class task03 {
    public static void main(String[] args) {
        Person1 student1 = new Student("Petro", "University", 3);
        Person1 worker1 = new Worker("Andriy", "Developer", 12);
        Person1 worker5 = new Worker("Andriy", "Developer", 12);
        Person1 student2 = new Student("Stepan", "College", 4);
        Person1 worker = new Worker("Ira", "Manager", 8);
        Person1 student3 = new Student("Ihor", "University", 4);
        Person1 student4 = new Student("Ihor", "University", 4);
        Person1 person = new Person1("Ivan");
        List<Person1> persons = List.of(student1, worker1, student2, worker, student3, person, student4, worker5);
        System.out.println(new MyUtils1().maxDuration(persons));

    }
}

class Person1 {
    private String name;

    public Person1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person[" +
                "name=" + name +
                ']';
    }
}
class Student extends Person1 {
    private String studyPlace;
    private int studyYears;

    public Student(String name, String studyPlace, int studyYears) {
        super(name);
        this.studyPlace = studyPlace;
        this.studyYears = studyYears;
    }

    public String getStudyPlace() {
        return studyPlace;
    }

    public int getStudyYears() {
        return studyYears;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), studyPlace, studyYears);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Student)) return false;
        if(this == obj) return true;
        Student s = (Student) obj;
        return getName().equals(s.getName()) && studyPlace.equals(s.studyPlace) && studyYears == s.studyYears;
    }

    @Override
    public String toString() {
        return "Student[" +
                "name=" + getName() +
                ", studyPlace=" + studyPlace  +
                ", studyYears=" + studyYears +
                ']';
    }
}
class Worker extends Person1 {
    private String workPosition;
    private int experienceYears;

    public Worker(String name, String workPosition, int experienceYears) {
        super(name);
        this.workPosition = workPosition;
        this.experienceYears = experienceYears;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public int getExperienceYears() {
        return experienceYears;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getName(), workPosition, experienceYears);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Worker)) return false;
        if(this == obj) return true;
        Worker w = (Worker) obj;
        return getName().equals(w.getName()) && workPosition.equals(w.workPosition) && experienceYears == w.experienceYears;
    }


    @Override
    public String toString() {
        return "Worker[" +
                "name=" + getName() +
                ", workPosition=" + workPosition +
                ", experienceYears=" + experienceYears +
                ']';
    }
}
class MyUtils1 {

    public List<Person1> maxDuration(List<Person1> persons) {
        if(persons == null) return null;
        if(persons.isEmpty()) return List.of();
        List<Person1> resultList = new ArrayList<>();
        List<Worker> workers = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        int maxDurationWorker = 0;
        int maxDurationStudent = 0;
        for (Person1 person : persons){
            if(person instanceof Worker){
                workers.add((Worker) person);
                int expYears = ((Worker) person).getExperienceYears();
                maxDurationWorker = expYears > maxDurationWorker ? expYears : maxDurationWorker;
            }
            if(person instanceof Student){
                students.add((Student) person);
                int studyYears = ((Student) person).getStudyYears();
                maxDurationStudent = studyYears > maxDurationStudent ? studyYears : maxDurationStudent;
            }
        }
        for (Worker worker : workers)
            if(worker.getExperienceYears() == maxDurationWorker) resultList.add(worker);

        for (Student student : students)
            if(student.getStudyYears() == maxDurationStudent) resultList.add(student);

        return resultList.stream().distinct().collect(Collectors.toList());

    }
}
