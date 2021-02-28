package DBHelpers;
import  java.sql.*;

import javax.swing.JOptionPane;

public class DBUtils {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/";

    static final String USER = "root";
    static final String PASS = "";
    static String dbName = "courseMgmt";
    static Connection conn = null;
    static Statement stmt = null;

    public DBUtils(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "Create Database "+dbName;
            stmt.execute(sql);
            System.out.println("Database has been successfully created...");
            createTables();
            System.out.println("All the required tables are now created and are filled with dummy datas...");
        } catch (SQLException se){
            System.out.println("Database Already Exists! No need to create one!!!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    private void createTables() {
        createUsersTable();
        createStudentTable();
        createTeacherTable();
        createCourseTable();
        createModuleTable();
        createStudentModuleTable();
    }

    private void createStudentModuleTable() {
        try{
            
            String url = "jdbc:mysql://localhost/"+dbName+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE studentmodules " +
                         "(studentId VARCHAR (255), " +
                         " moduleCode VARCHAR(255), " + 
                         " enrolledStatus tinyint(1), " +
                         " marksSecured double)"; 
            createTable.executeUpdate(sql);

            for(int j=1; j<=4; j++){
                if(j%2==0){
                    for(int i=1; i<10; i++){
                        String insert = "INSERT INTO studentmodules (studentId, moduleCode, enrolledStatus, marksSecured) VALUES ('ST0000"+j+"', 'BCS00"+i+"', 1, 0.00)";
                        PreparedStatement statement = con.prepareStatement(insert);
                        statement.executeUpdate();
                    }
    
                    for(int i=10; i<23; i++){
                        String insert = "INSERT INTO studentmodules (studentId, moduleCode, enrolledStatus, marksSecured) VALUES ('ST0000"+j+"', 'BCS0"+i+"', 1, 0.00)";
                        PreparedStatement statement = con.prepareStatement(insert);
                        statement.executeUpdate();
                    }
    
                    for(int i=23; i<25; i++){
                        String insert = "INSERT INTO studentmodules (studentId, moduleCode, enrolledStatus, marksSecured) VALUES ('ST0000"+j+"', 'BCS0"+i+"', 0, 0.00)";
                        PreparedStatement statement = con.prepareStatement(insert);
                        statement.executeUpdate();
                    }
                } else {
                    for(int i=1; i<10; i++){
                        String insert = "INSERT INTO studentmodules (studentId, moduleCode, enrolledStatus, marksSecured) VALUES ('ST0000"+j+"', 'BBA00"+i+"', 1, 0.00)";
                        PreparedStatement statement = con.prepareStatement(insert);
                        statement.executeUpdate();
                    }
    
                    for(int i=10; i<23; i++){
                        String insert = "INSERT INTO studentmodules (studentId, moduleCode, enrolledStatus, marksSecured) VALUES ('ST0000"+j+"', 'BBA0"+i+"', 1, 0.00)";
                        PreparedStatement statement = con.prepareStatement(insert);
                        statement.executeUpdate();
                    }
    
                    for(int i=23; i<25; i++){
                        String insert = "INSERT INTO studentmodules (studentId, moduleCode, enrolledStatus, marksSecured) VALUES ('ST0000"+j+"', 'BBA0"+i+"', 0, 0.00)";
                        PreparedStatement statement = con.prepareStatement(insert);
                        statement.executeUpdate();
                    }
                }
            }
        } catch (SQLException se){
           se.printStackTrace();
        }
    }

    private void createModuleTable() {
        try{
            String url = "jdbc:mysql://localhost/"+dbName+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE modules " +
                         "(moduleName VARCHAR(255), " +
                         " moduleCode VARCHAR(255), " + 
                         " teacherName VARCHAR(255), " +
                         " semester int(11), " +
                         " credits int(11), " +
                         " coreStatus tinyint(1))"; 
            createTable.executeUpdate(sql);


            String insert = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Problem Solving Using C', 'BCS001', 'Saumya Bhandary', 1, 15, 1), ('Computer Science', 'BCS002', 'Kritika Luitel', 1, 15, 1), ('Electronics I', 'BCS003', 'Nabin Panta', 1, 15, 1), ('Fundamentals of DataBase', 'BCS004', 'Himanshu Pandey', 1, 15, 1)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.executeUpdate();

            String insert2 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Mathematics I', 'BCS004', 'Rachana Lamsal', 2, 15, 1), ('Data Structures', 'BCS005', 'Jenish Shrestha', 2, 15, 1), ('Object Oriented Programming', 'BCS006', 'Saumya Bhandary', 2, 15, 1), ('Software Engineering', 'BCS005', 'Kritika Luitel', 2, 15, 1)";
            PreparedStatement statement2 = con.prepareStatement(insert2);
            statement2.executeUpdate();

            String insert3 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Mathematics II', 'BCS009', 'Nabin Panta', 3, 15, 1), ('Electronics II', 'BCS010', 'Himanshu Pandey', 3, 15, 1), ('Operating System', 'BCS011', 'Rachana Lamsal', 3, 15, 1), ('Computer Networks', 'BCS012', 'Jenish Shrestha', 3, 15, 1)";
            PreparedStatement statement3 = con.prepareStatement(insert3);
            statement3.executeUpdate();

            String insert4 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Compiler Construction', 'BCS013', 'Saumya Bhandary', 4, 15, 1), ('System Programming', 'BCS014', 'Kritika Luitel', 4, 15, 1), ('Computer Graphics', 'BCS015', 'Nabin Panta', 4, 15, 1), ('Internet Prgramming II', 'BCS016', 'Himanshu Pandey', 4, 15, 1)";
            PreparedStatement statement4 = con.prepareStatement(insert4);
            statement4.executeUpdate();

            String insert5 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Programming in Java I', 'BCS017', 'Rachana Lamsal', 5, 15, 1), ('Object Oriented Software Engineering', 'BCS018', 'Jenish Shrestha', 5, 15, 1), ('Computer Graphics using Java', 'BCS019', 'Saumya Bhandary', 5, 15, 1), ('Relational DataBase Management System', 'BCS020', 'Kritika Luitel', 5, 15, 1)";
            PreparedStatement statement5 = con.prepareStatement(insert5);
            statement5.executeUpdate();

            String insert6 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Programming in Java II', 'BCS021', 'Nabin Panta', 6, 15, 1), ('Project', 'BCS022', 'Himanshu Pandey', 6, 15, 1), ('Computer Networks II', 'BCS023', 'Rachana Lamsal', 6, 15, 0), ('Theoretical Computer Science', 'BCS024', 'Jenish Shrestha', 6, 15, 0)";
            PreparedStatement statement6 = con.prepareStatement(insert6);
            statement6.executeUpdate();

            String insert7 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Principles of Management', 'BBA001', 'Siddartha Shrestha', 1, 15, 1), ('Business Mathematics and Statics', 'BBA002', 'Saman Tamrakar', 1, 15, 1), ('Introduction to Operation Research', 'BBA003', 'Krishna Bhandari', 1, 15, 1), ('Business Economics', 'BBA004', 'Anudit Basnet', 1, 15, 1)";
            PreparedStatement statement7 = con.prepareStatement(insert7);
            statement7.executeUpdate();

            String insert8 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Management and Industry Relations', 'BBA004', 'Anushka Jain', 2, 15, 1), ('Marketing Management', 'BBA005', 'Roshan Pandey', 2, 15, 1), ('Business Data Processing', 'BBA006', 'Siddartha Shrestha', 2, 15, 1), ('Business Laws', 'BBA005', 'Saman Tamrakar', 2, 15, 1)";
            PreparedStatement statement8 = con.prepareStatement(insert8);
            statement8.executeUpdate();

            String insert9 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Introduction to Sociology', 'BBA009', 'Krishna Bhandari', 3, 15, 1), ('Essentials of Marketing', 'BBA010', 'Anudit Basnet', 3, 15, 1), ('MIS / Systems Design', 'BBA011', 'Anushka Jain', 3, 15, 1), ('Strategy', 'BBA012', 'Roshan Pandey', 3, 15, 1)";
            PreparedStatement statement9 = con.prepareStatement(insert9);
            statement9.executeUpdate();

            String insert10 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Sales & Distribution Management', 'BBA013', 'Siddartha Shrestha', 4, 15, 1), ('Manufacture Planning and Control', 'BBA014', 'Saman Tamrakar', 4, 15, 1), ('E-Commerce', 'BBA015', 'Krishna Bhandari', 4, 15, 1), ('Family Business Management', 'BBA016', 'Anudit Basnet', 4, 15, 1)";
            PreparedStatement statement10 = con.prepareStatement(insert10);
            statement10.executeUpdate();

            String insert11 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Project Management', 'BBA017', 'Anushka Jain', 5, 15, 1), ('Labour Legislation', 'BBA018', 'Roshan Pandey', 5, 15, 1), ('Marketing Management', 'BBA019', 'Siddartha Shrestha', 5, 15, 1), ('Micro Economics', 'BBA020', 'Saman Tamrakar', 5, 15, 1)";
            PreparedStatement statement11 = con.prepareStatement(insert11);
            statement11.executeUpdate();

            String insert12 = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES ('Consumer Behaviour', 'BBA021', 'Krishna Bhandari', 6, 15, 1), ('PR Management', 'BBA022', 'Anudit Basnet', 6, 15, 1), ('Digital Marketing', 'BBA023', 'Narayan Khatiwada', 6, 15, 0), ('Leadership and Ethics', 'BBA024', 'Roshan Pandey', 6, 15, 0)";
            PreparedStatement statement12 = con.prepareStatement(insert12);
            statement12.executeUpdate();

        } catch (SQLException se){
           se.printStackTrace();
        }
    }

    private void createCourseTable() {
        try{
            String url = "jdbc:mysql://localhost/"+dbName+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE courses " +
                         "(cancelStatus tinyint(1), " +
                         " level VARCHAR(255), " + 
                         " courseName VARCHAR(255), " +
                         " courseTag VARCHAR(255), " +
                         " stream VARCHAR(255))"; 
            createTable.executeUpdate(sql);

            String insert = "INSERT INTO courses (cancelStatus, level, courseName, courseTag, stream) VALUES (0, 'Bachelors', 'Computer Science', 'BCS', 'Science')";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.executeUpdate();

            insert = "INSERT INTO courses (cancelStatus, level, courseName, courseTag, stream) VALUES (0, 'Bachelors', 'Business Administration', 'BBA', 'Management')";
            statement = con.prepareStatement(insert);
            statement.executeUpdate();



        } catch (SQLException se){
           se.printStackTrace();
        }
    }

    private void createTeacherTable() {
        try{
            String url = "jdbc:mysql://localhost/"+dbName+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement createTable = con.createStatement();
            String sql = "create table teachers (teacherId varchar(255), teacherName varchar(255))"; 
            createTable.executeUpdate(sql);
 

            String insert = "INSERT INTO teachers (teacherId, teacherName) VALUES ('TH00001', 'Saumya Bhandary'), ('TH00002', 'Kritika Luitel'), ('TH00003', 'Nabin Panta'), ('TH00004', 'Himanshu Pandey'), ('TH00005', 'Rachana Lamsal'), ('TH00006', 'Jenish Shrestha')";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.executeUpdate();
            insert = "INSERT INTO teachers (teacherId, teacherName) VALUES ('TH00007', 'Siddartha Shrestha'), ('TH00008', 'Anushka Jain'), ('TH00009', 'Anudit Basnet'), ('TH00010', 'Krishna Bhandari'), ('TH00011', 'Roshan Pandey'), ('TH00012', 'Saman Tamrakar')";
            statement = con.prepareStatement(insert);
            statement.executeUpdate();

        } catch (SQLException se){
           se.printStackTrace();
        }
    }

    private void createStudentTable() {
        try{
            String url = "jdbc:mysql://localhost/"+dbName+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE student " +
                         "(studentId varchar(255), " +
                         " level VARCHAR(255), " + 
                         " studentName VARCHAR(255), " +
                         " courseEnrolledIn VARCHAR(255), " + 
                         " failStatus VARCHAR(255), " +
                         " remarks VARCHAR(255)) "; 
            createTable.executeUpdate(sql);

            String insert = "INSERT INTO student (studentId, level, studentName, courseEnrolledIn, failStatus, remarks) VALUES ('ST00001', '1', 'Nimesh Humagain', 'BCS', 'Pass', 'Average Student'), ('ST00002', '1', 'Prakrit Adhikari', 'BBA', 'Fail', 'Poor in studies'), ('ST00003', '3', 'Prativa Parajuli', 'BCS', 'Pass', 'Good Student'), ('ST00004', '2', 'Abhinash Gautam', 'BBA', 'Pass', 'Average Student')";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.executeUpdate();

        } catch (SQLException se){
           se.printStackTrace();
        }
    }

    private void createUsersTable() {
        try{
            String url = "jdbc:mysql://localhost/"+dbName+"?useTimezone=true&serverTimezone=UTC";
            Connection con = DriverManager.getConnection(url, USER, PASS);
            Statement createTable = con.createStatement();
            String sql = "CREATE TABLE Users " +
                         "(userId varchar(255), " +
                         " userType VARCHAR(255), " + 
                         " userPassword VARCHAR(255), " +
                         " address VARCHAR(255), " + 
                         " phoneNo VARCHAR(255), " +
                         " PRIMARY KEY ( userId ))"; 
            createTable.executeUpdate(sql);


            String insert = "INSERT INTO users (userId, userType, userPassword, address, phoneNo) VALUES ('ST00001', 'Student', '1234', 'Mulpani', '9843866411'), ('ST00002', 'Student', '1234', 'Tokha', '9841555226'), ('ST00003', 'Student', '1234', 'Patan', '9851089632'), ('ST00004', 'Student', '1234', 'Naxal', '9841859674')";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.executeUpdate();

            String insert2 = "INSERT INTO users (userId, userType, userPassword, address, phoneNo) VALUES ('TH00001', 'Teacher', '1234', 'Bouddha', '9841852963'), ('TH00002', 'Teacher', '1234', 'Gyaneshwor', '9841852963'), ('TH00003', 'Teacher', '1234', 'Ratopul', '9841858455'), ('TH00004', 'Teacher', '1234', 'Baneshwor', '9841855563'), ('TH00005', 'Teacher', '1234', 'Maitighar', '9841417773'), ('TH00006', 'Teacher', '1234', 'Teku', '9841852222'), ('TH00007', 'Teacher', '1234', 'Bouddha', '9841852963'), ('TH00008', 'Teacher', '1234', 'Gyaneshwor', '9841852963'), ('TH00009', 'Teacher', '1234', 'Ratopul', '9841858455'), ('TH00010', 'Teacher', '1234', 'Baneshwor', '9841855563'), ('TH00011', 'Teacher', '1234', 'Maitighar', '9841417773'), ('TH00012', 'Teacher', '1234', 'Teku', '9841852222')";
            PreparedStatement statement2 = con.prepareStatement(insert2);
            statement2.executeUpdate();

        } catch (SQLException se){
           se.printStackTrace();
        }
    }

    public static Connection getDbConnection() {
        try{
            String connectionString = "jdbc:mysql://localhost:3306/"+dbName+"?useTimezone=true&serverTimezone=UTC";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(connectionString, USER, PASS);
        } catch(SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not connect to the database. Please kindly start your mysql database and restart the application.", "Start the database!", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return null;
        }
    }




}

