package sprint10.task01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class MyUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/sprint10";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private Statement statement;
    private String schemaName;
    private ResultSet resultSet;

    public Connection createConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }
    public Statement createStatement() throws SQLException {
        if(statement == null && connection != null) statement = connection.createStatement();
        return statement;
    }
    public void closeStatement() throws SQLException {
        statement.close();
    }
    public void createSchema(String schemaName) throws SQLException {
        myExecute("CREATE SCHEMA IF NOT EXISTS " + schemaName);
        this.schemaName = schemaName;
    }
    public void dropSchema() throws SQLException {
        myExecute("DROP SCHEMA IF EXISTS " + schemaName);
    }
    public void useSchema() throws SQLException {
        myExecute("USE " + schemaName);
    }
    public void createTableRoles() throws SQLException {
        myExecute("CREATE TABLE IF NOT EXISTS `Roles` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`roleName` VARCHAR(45) NULL," +
                "PRIMARY KEY (`id`))");
    }
    public void createTableDirections() throws SQLException {
        myExecute("CREATE TABLE IF NOT EXISTS `Directions` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`directionName` VARCHAR(45) NULL," +
                "PRIMARY KEY (`id`))");
    }
    public void createTableProjects() throws SQLException {
        myExecute("CREATE TABLE IF NOT EXISTS `Projects` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`projectName` VARCHAR(45) NULL," +
                "`directionId` INT NOT NULL," +
                "PRIMARY KEY (`id`, `directionId`)," +
                "INDEX `fk_Projects_Directions_idx` (`directionId` ASC) VISIBLE," +
                "CONSTRAINT `fk_Projects_Directions`" +
                "  FOREIGN KEY (`directionId`)" +
                "  REFERENCES `Directions` (`id`)" +
                "  ON DELETE NO ACTION" +
                "  ON UPDATE NO ACTION)");
    }
    public void createTableEmployee() throws SQLException {
        myExecute("CREATE TABLE IF NOT EXISTS `Employee` (" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`firstName` VARCHAR(45) NULL," +
                "`projectId` INT NOT NULL," +
                "`roleId` INT NOT NULL," +
                "PRIMARY KEY (`id`, `projectId`, `roleId`)," +
                "INDEX `fk_Employee_Projects1_idx` (`projectId` ASC) VISIBLE," +
                "INDEX `fk_Employee_Roles1_idx` (`roleId` ASC) VISIBLE," +
                "CONSTRAINT `fk_Employee_Projects1`" +
                "  FOREIGN KEY (`projectId`)" +
                "  REFERENCES `Projects` (`id`)" +
                "  ON DELETE NO ACTION" +
                "  ON UPDATE NO ACTION," +
                "CONSTRAINT `fk_Employee_Roles1`" +
                "  FOREIGN KEY (`roleId`)" +
                "  REFERENCES `Roles` (`id`)" +
                "  ON DELETE NO ACTION" +
                "  ON UPDATE NO ACTION)");
    }
    public void dropTable(String tableName) throws SQLException {
        myExecute(String.format("DROP TABLE IF EXISTS %s", tableName));
    }
    public void insertTableRoles(String roleName) throws SQLException {
        if(getRoleId(roleName) == 0)
            myExecute(String.format("INSERT INTO Roles(roleName) VALUE('%s')", roleName));
    }
    public void insertTableDirections(String directionName) throws SQLException {
        if(getDirectionId(directionName) == 0)
            myExecute(String.format("INSERT INTO Directions(directionName) VALUE('%s')", directionName));
    }
    public void insertTableProjects(String projectName, String directionName) throws SQLException {
        if(getProjectId(projectName) == 0)
            myExecute(String.format("INSERT INTO Projects(projectName, directionId) VALUE(" + "'%s', %d)", projectName, getDirectionId(directionName)));
    }
    public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException {
            myExecute(String.format("INSERT INTO Employee(firstName, roleId, projectId) VALUE('%s', %d, %d)", firstName, getRoleId(roleName), getProjectId(projectName)));
    }
    public int getRoleId(String roleName) throws SQLException {
        return getId(getAllRoles(), roleName);
    }
    public int getDirectionId(String directionName) throws SQLException {
        return getId(getAllDirestion(), directionName);
    }
    public int getProjectId(String projectName) throws SQLException {
        return getId(getAllProjects(), projectName);
    }
    public int getEmployeeId(String firstName) throws SQLException {
        return getId(getAllEmployee(), firstName);
    }
    public List<String> getAllRoles() throws SQLException {
        return getAll("SELECT roleName FROM Roles");
    }
    public List<String> getAllDirestion() throws SQLException {
        return getAll("SELECT directionName FROM Directions");
    }
    public List<String> getAllProjects() throws SQLException {
        return getAll("SELECT projectName FROM Projects");
    }
    public List<String> getAllEmployee() throws SQLException {
        return getAll("SELECT firstName FROM Employee");
    }
    public List<String> getAllDevelopers() throws SQLException {
        return getAll("SELECT firstName FROM Employee WHERE roleId = " + getRoleId("Developer"));
    }
    public List<String> getAllJavaProjects() throws SQLException {
        return getAll("SELECT projectName FROM Projects WHERE directionId = " + getDirectionId("Java"));
    }
    public List<String> getAllJavaDevelopers() throws SQLException {
        return getAll(String.format("SELECT firstName FROM Employee WHERE roleId = %d AND projectId IN (%d, %d)",
                getRoleId("Developer"), getProjectId("Sun"), getProjectId("MoonLight")));
    }

    private void myExecute(String query) throws SQLException {
        statement.execute(query);
    }

    private List<String> getAll(String query) throws SQLException {
        List<String> listAll = new ArrayList<>();
        resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            listAll.add(resultSet.getString(1));
        }
        return listAll;
    }

    private int getId(List<String> list, String name) {
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).equals(name)) return i + 1;

        return 0;
    }
}


