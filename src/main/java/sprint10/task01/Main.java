package sprint10.task01;

import java.sql.SQLException;

class Main {
    public static void main(String[] args) throws SQLException {
        MyUtils myUtils = new MyUtils();
        myUtils.createConnection();
        myUtils.createStatement();

        /*myUtils.dropTable("Employee");
        myUtils.dropTable("Projects");
        myUtils.dropTable("Roles");
        myUtils.dropTable("Directions");

        myUtils.createTableRoles();
        myUtils.insertTableRoles("Developer");
        myUtils.insertTableRoles("DevOps");
        myUtils.insertTableRoles("QA");
        myUtils.getAllRoles().stream().forEach(s -> {
            try {
                System.out.println(myUtils.getRoleId(s) + " " + s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        System.out.println("__________");

        myUtils.createTableDirections();
        myUtils.insertTableDirections("Java");
        myUtils.insertTableDirections("Python");
        myUtils.insertTableDirections(".Net");
        myUtils.getAllDirestion().stream().forEach(s -> {
            try {
                System.out.println(myUtils.getDirectionId(s) + " " + s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        System.out.println("__________");

        myUtils.createTableProjects();
        myUtils.insertTableProjects("MoonLight", "Java");
        myUtils.insertTableProjects("Sun", "Java");
        myUtils.insertTableProjects("Mars", "Python");
        myUtils.getAllProjects().stream().forEach(s -> {
            try {
                System.out.println(myUtils.getProjectId(s) + " " + s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        System.out.println("__________");

        myUtils.createTableEmployee();
        myUtils.insertTableEmployee("Ivan", "Developer", "MoonLight");
        myUtils.insertTableEmployee("Petro", "Developer", "Sun");
        myUtils.insertTableEmployee("Stepan", "Developer", "Mars");
        myUtils.insertTableEmployee("Andriy", "DevOps", "MoonLight");
        myUtils.insertTableEmployee("Vasyl", "DevOps", "Mars");
        myUtils.insertTableEmployee("Ira", "Developer", "MoonLight");
        myUtils.insertTableEmployee("Anna", "QA", "MoonLight");
        myUtils.insertTableEmployee("Olia", "QA", "Sun");
        myUtils.insertTableEmployee("Maria", "QA", "Mars");
        myUtils.getAllEmployee().stream().forEach(s -> {
            try {
                System.out.println(myUtils.getEmployeeId(s) + " " + s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });*/

        myUtils.getAllJavaDevelopers().stream().forEach(System.out::println);
        System.out.println("________________");
        myUtils.getAllJavaProjects().stream().forEach(System.out::println);
        System.out.println("________________");
        myUtils.getAllDevelopers().stream().forEach(System.out::println);


        myUtils.closeStatement();
        myUtils.closeConnection();
    }
}
