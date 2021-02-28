package DBHelpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import javax.swing.JOptionPane;

public class ManagementDatabase {

    private Connection con;
    private String id = null;

    public ManagementDatabase() {
        try {
            con = DBUtils.getDbConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setRowSelected(String id) {
        this.id = id;
    }

    public ResultSet getStudentModuleProperties(String studentId) {
        String select = "SELECT * FROM studentmodules WHERE studentId = ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(select);
            stmnt.setString(1, studentId);
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addStudentModuleProperties(String studentId, String courseTag) {

        ResultSet rs = getModules(courseTag);
        Boolean enrolledStatus = true;
        int flag = 0;

        try {
            while (rs.next()) {
                if (flag > 21) {
                    enrolledStatus = false;
                }
                String module = rs.getString("moduleCode");
                String insert = "Insert into studentmodules (studentId, moduleCode, enrolledStatus, marksSecured) values (?, ?, ?, ?)";
                PreparedStatement statement = con.prepareStatement(insert);
                statement.setString(1, studentId);
                statement.setString(2, module);
                statement.setBoolean(3, enrolledStatus);
                statement.setDouble(4, 0.0);
                statement.executeUpdate();
                flag++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getStudents() {
        String select = "SELECT * FROM users JOIN student ON users.userId = student.studentId order by student.studentName asc";
        try {
            PreparedStatement stmnt = con.prepareStatement(select);
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ResultSet getTeacher() {
        String select = "SELECT * FROM users JOIN teachers ON users.userId = teachers.teacherId order by teachers.teacherName asc";
        try {
            PreparedStatement stmnt = con.prepareStatement(select);
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ResultSet getTeacherModules(String teacherName) {
        String select = "select * from modules where teacherName = ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(select);
            stmnt.setString(1, teacherName);
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ResultSet getModules(String courseTag) {
        String select = "Select * from modules where moduleCode like ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(select);
            stmnt.setString(1, courseTag + "%");
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ResultSet getCourses() {
        String select = "Select * from courses";
        try {
            PreparedStatement stmnt = con.prepareStatement(select);
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void saveUserDetails(String level, String studentName, String courseEnrolledIn, String failStatus,
            String userId, String userType, String userPassword, String address, String phoneNo) {

        try {
            FileWriter file = new FileWriter("src/Files/LogIn.txt", true);
            file.write(userId + " " + userPassword + " " + userType + "\n");
            file.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String insert = "INSERT INTO users (userId, userType, userPassword, address, phoneNo) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, userId);
            statement.setString(2, userType);
            statement.setString(3, userPassword);
            statement.setString(4, address);
            statement.setString(5, phoneNo);
            statement.executeUpdate();
            saveStudentDetails(level, studentName, courseEnrolledIn, failStatus, userId);
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "The student already exists!");
            System.out.println("Error in user table");
        }

    }

    // method overloading
    public void saveUserDetails(String teacherName, String userId, String userType, String userPassword, String address,
            String phoneNo) {

        try {
            FileWriter file = new FileWriter("src/Files/LogIn.txt", true);
            file.write(userId + " " + userPassword + " " + userType + "\n");
            file.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String insert = "INSERT INTO users (userId, userType, userPassword, address, phoneNo) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, userId);
            statement.setString(2, userType);
            statement.setString(3, userPassword);
            statement.setString(4, address);
            statement.setString(5, phoneNo);
            statement.executeUpdate();
            saveTeacherDetails(teacherName, userId);
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "The Person already exists!");
            System.out.println("Error in user table");
        }

    }

    public void updateUserDetails(String level, String studentName, String courseEnrolledIn, String failStatus,
            String userId, String userType, String userPassword, String address, String phoneNo) {

        String update = "UPDATE users SET userId = ?, userType = ?, userPassword = ?, address = ?, phoneNo = ? WHERE userId = ?";
        try {
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, userId);
            statement.setString(2, userType);
            statement.setString(3, userPassword);
            statement.setString(4, address);
            statement.setString(5, phoneNo);
            statement.setString(6, id);
            if (id == null) {
                JOptionPane.showMessageDialog(null,
                        "Please select the student that you want to update these records on!");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to update the details of the student: "
                        + id + "?") == JOptionPane.YES_OPTION) {
                    statement.executeUpdate();

                    updateStudentDetails(level, studentName, courseEnrolledIn, failStatus, userId);
                }
            }
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "Student Id cannot be updated!");
        }

    }

    public void updateUserDetails(String teacherName, String userId, String userType, String userPassword,
            String address, String phoneNo) {

        String update = "UPDATE users SET userId = ?, userType = ?, userPassword = ?, address = ?, phoneNo = ? WHERE userId = ?";
        try {
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, userId);
            statement.setString(2, userType);
            statement.setString(3, userPassword);
            statement.setString(4, address);
            statement.setString(5, phoneNo);
            statement.setString(6, id);
            if (id == null) {
                JOptionPane.showMessageDialog(null,
                        "Please select the student that you want to update these records on!");
            } else {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to update the details of the teacher: "
                        + id + "?") == JOptionPane.YES_OPTION) {
                    statement.executeUpdate();
                    updateTeacherDetails(teacherName, userId);
                }
            }
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "Student Id cannot be updated!");
        }

    }

    public void deleteUserDetails(String userType) {

        try {
            File inputFile = new File("src/Files/LogIn.txt");
            File tempFile = new File("src/Files/LogInTemp.txt");
            
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String idToDelete = id;
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                if(currentLine.contains(idToDelete)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close(); 
            reader.close(); 
            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        

        if (userType == "Student") {
            deleteStudentDetails();
        } else if (userType == "Teacher") {
            deleteTeacherDetails();
        }
        String delete = "Delete from users WHERE userId = ?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setString(1, id);
            if (id == null) {
                JOptionPane.showMessageDialog(null, "Please select the id that you want to delete these records on!");
            } else {
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void saveStudentDetails(String level, String studentName, String courseEnrolledIn, String failStatus,
            String userId) {

        String insert = "INSERT INTO student (studentId, level, studentName, courseEnrolledIn, failStatus, remarks) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, userId);
            statement.setInt(2, Integer.valueOf(level));
            statement.setString(3, studentName);
            statement.setString(4, courseEnrolledIn);
            statement.setString(5, failStatus);
            statement.setString(6, "Not Evaluated");
            statement.executeUpdate();
            addStudentModuleProperties(userId, courseEnrolledIn);
        } catch (NumberFormatException ex) {
            System.out.println("Error in student table");
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void saveTeacherDetails(String teacherName, String teacherId) {

        String insert = "INSERT INTO teachers (teacherId, teacherName) VALUES (?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, teacherId);
            statement.setString(2, teacherName);
            statement.executeUpdate();
        } catch (NumberFormatException ex) {
            System.out.println("Error in teacher table");
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void updateStudentDetails(String level, String studentName, String courseEnrolledIn, String failStatus,
            String userId) {

        String update = "UPDATE student SET studentId = ?, level = ?, studentName = ?, courseEnrolledIn = ?, failStatus = ?, remarks = ? WHERE studentId = ?";

        try {
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, userId);
            statement.setInt(2, Integer.valueOf(level));
            statement.setString(3, studentName);
            statement.setString(4, courseEnrolledIn);
            statement.setString(5, failStatus);
            statement.setString(6, "Not Evaluated");
            statement.setString(7, id);
            statement.executeUpdate();
        } catch (NumberFormatException ex) {
            System.out.println("Error in student table");
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateTeacherDetails(String teacherName, String teacherId) {

        String update = "UPDATE teachers SET teacherId = ?, teacherName = ? WHERE teacherId = ?";

        try {
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, teacherId);
            statement.setString(2, teacherName);
            statement.setString(3, teacherId);
            statement.executeUpdate();
        } catch (NumberFormatException ex) {
            System.out.println("Error in teacher table");
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void deleteStudentDetails() {

        String delete = "delete from student WHERE studentId = ?";

        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (NumberFormatException ex) {
            System.out.println("Error in student table");
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        delete = "delete from studentModules where studentId = ?";

        PreparedStatement statement;
        try {
            statement = con.prepareStatement(delete);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    private void deleteTeacherDetails() {
        
        String delete = "delete from teachers WHERE teacherId = ?";
        
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setString(1, id);
            statement.executeUpdate();
        }catch(NumberFormatException ex){
            System.out.println("Error in teacher table");
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
    }

    public ResultSet searchStudentData(String name){
        
        String search = "SELECT * FROM users JOIN student ON users.userId = student.studentId where student.studentName like ? OR student.studentId like ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(search);
            stmnt.setString(1, name);
            stmnt.setString(2, name);
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  null;
        
    }

    public ResultSet searchTeacherData(String name){
        
        String search = "SELECT * FROM users JOIN teachers ON users.userId = teachers.teacherId where teachers.teacherName like ? OR teachers.teacherId like ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(search);
            stmnt.setString(1, name);
            stmnt.setString(2, name);
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  null;
        
    }


    public void saveCourseDetails(String courseName, String courseTag, String level, String stream, String[] moduleNames, String[] moduleCodes, String[] semesters, Boolean[] coreStatus, String[] teachers) {
        String insert = "INSERT INTO courses (cancelStatus, level, courseName, courseTag, stream) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setBoolean(1, false);
            statement.setString(2, level);
            statement.setString(3, courseName);
            statement.setString(4, courseTag);
            statement.setString(5, stream);
            statement.executeUpdate();
            saveModuleDetails(moduleNames, moduleCodes, semesters, coreStatus, teachers);
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void saveModuleDetails(String[] moduleNames, String[] moduleCodes, String[] semesters, Boolean[] coreStatus, String[] teachers){

        String insert = "INSERT INTO modules (moduleName, moduleCode, teacherName, semester, credits, coreStatus) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            for(int i=0; i<moduleNames.length; i++){
                PreparedStatement statement = con.prepareStatement(insert);
                statement.setString(1, moduleNames[i]);
                statement.setString(2, moduleCodes[i]);
                statement.setString(3, teachers[i]);
                statement.setString(4, semesters[i]);
                statement.setInt(5, 15);
                statement.setBoolean(6, coreStatus[i]);
                statement.executeUpdate();
            }    
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void updateCourseDetails(String courseName, String courseTag, String level, String stream, String[] moduleNames, String[] moduleCodes, String[] semesters, Boolean[] coreStatus, String[] teachers) {

        try {
            updateModulesDetails(moduleNames, moduleCodes, semesters, coreStatus, teachers, courseTag);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        }

    }

    public void updateCourseDetails(String courseTag, Boolean cancelStatus) {

        String update = "update courses set cancelStatus = ? where courseTag = ?";

        try {
            PreparedStatement statement = con.prepareStatement(update);
            statement.setBoolean(1, cancelStatus);
            statement.setString(2, courseTag);
            statement.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "An error occured! Please try again!!");
        }

    }

    public void deleteCourseDetails(String courseTag) {
        String delete = "delete from courses where courseTag = ?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setString(1, courseTag);
            statement.executeUpdate();
            deleteModuleDetails(courseTag);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "An error occured! Please try again!!");
        }
    }
    
    public void deleteModuleDetails(String courseTag) {
        String delete = "delete from modules where moduleCode like ?";
        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setString(1, courseTag+"%");
            statement.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "An error occured! Please try again!!");
        }
    }

    private void updateModulesDetails(String[] moduleNames, String[] moduleCodes, String[] semesters, Boolean[] coreStatus, String[] teachers, String courseTag){

        String update = "update modules set moduleName = ?, moduleCode =?, teacherName = ?, semester = ?, credits = ?, coreStatus = ? where moduleCode = ?";

        try {
            for(int i=0; i<moduleNames.length; i++){
                PreparedStatement statement = con.prepareStatement(update);
                statement.setString(1, moduleNames[i]);
                statement.setString(2, moduleCodes[i]);
                statement.setString(3, teachers[i]);
                statement.setString(4, semesters[i]);
                statement.setInt(5, 15);
                statement.setBoolean(6, coreStatus[i]);
                statement.setString(7, moduleCodes[i]);
                statement.executeUpdate();
            }    

        // update = "update studentmodules set moduleCode = ? ";
        // for(int i=0; i<moduleNames.length; i++){
        //     PreparedStatement statement = con.prepareStatement(update);
        //     statement.setString(1, moduleCodes[i]);
        //     statement.executeUpdate();
        // } 


        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Please fill in all the data required!");
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getStudentProperties(String studentId) {
        String select = "SELECT * FROM users JOIN student ON users.userId = student.studentId where student.studentId = ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, studentId);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
   }


   public ResultSet getModuleProperties(String studentId){
        String select = "SELECT * FROM studentmodules WHERE studentId = ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(select);
            stmnt.setString(1, studentId);
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  null;
    }

    public void updateStudentEnrolledStatus(String studentId, String moduleCode, Boolean enrolledStatus){
        String update = "UPDATE studentmodules SET enrolledStatus=? WHERE moduleCode = ? AND studentId = ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(update);
            stmnt.setBoolean(1, enrolledStatus);
            stmnt.setString(2, moduleCode);
            stmnt.setString(3, studentId);
            stmnt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getTeacherProperties(String teacher) {
        String select = "SELECT * FROM users JOIN teachers ON users.userId = teachers.teacherId where teachers.teacherId = ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, teacher);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getStudentsInModule(String moduleCode){
        String select = "SELECT * FROM studentModules JOIN student ON studentModules.studentId = student.studentId where studentModules.moduleCode LIKE ?";
        try {
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, moduleCode+"%");
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editStudentMarks(String marks, String remarks, String studentId, String moduleCode){

        String update = "UPDATE studentmodules SET marksSecured=? WHERE moduleCode = ? AND studentId = ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(update);
            stmnt.setString(1, marks);
            stmnt.setString(2, moduleCode);
            stmnt.setString(3, studentId);
            stmnt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
         
        update = "UPDATE student SET remarks=? WHERE studentId = ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(update);
            stmnt.setString(1, remarks);
            stmnt.setString(2, studentId);
            stmnt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    public ResultSet getCourseModuleProperties(String courseTag){
        String select = "SELECT * FROM modules WHERE moduleCode LIKE ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(select);
            stmnt.setString(1, courseTag+"%");
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  null;
    }

    public ResultSet getCourseProperties(String courseTag){
        String select = "SELECT * FROM courses WHERE courseTag = ?";
        try {
            PreparedStatement stmnt = con.prepareStatement(select);
            stmnt.setString(1, courseTag);
            return stmnt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  null;
    }



}

