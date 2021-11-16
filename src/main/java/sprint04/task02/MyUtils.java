package sprint04.task02;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class MyUtils {
    private Set<Student> students;
    public static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return id == student.id && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }
    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
        students = new HashSet<>();
        for(Student student1 : list1){
            for(Student student2 : list2){
                if(student1.equals(student2)) students.add(student1);
            }
        }

        return students;
    }
}
