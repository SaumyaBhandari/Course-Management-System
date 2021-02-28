package Bodies;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DBHelpers.*;
import Courses.Modules;

public class Student extends Users {

    private int level;
    private String studentName;
    private String courseEnrolledIn;
    private String failStatus = "Pass";
    private String remarks;
    private String studentId;
    ArrayList<Modules> studentModules = new ArrayList<Modules>();
    ManagementDatabase db = new ManagementDatabase();

    public Student(String userId, String password){
        this.studentId = userId;
        super.setUserId(userId);
        setStudentProperties();
        super.setUserPassword(password);
        super.setUserType("Student");
        setModuleProperties();
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseEnrolledIn(){
        return this.courseEnrolledIn;
    }

    public void setCourseEnrolledIn(String courseName){
        this.courseEnrolledIn = courseName;
    }

    public String getFailStatus() {
        return failStatus;
    }
    public void setFailStatus(String failStatus) {
        this.failStatus = failStatus;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    

    public int getNoOfModules(){
        return studentModules.size();
    }

    public String getModuleNames(int num) {
        return studentModules.get(num).getModuleName();
    }

    public String getModuleCodes(int num){
        return  studentModules.get(num).getModuleCode();
    }

    public Boolean getEnrolledStatusOfModule(int num){
        return studentModules.get(num).getEnrolledStatus();
    }

    public String getModuleInstructorName(int num){
        return studentModules.get(num).getInstructorName();
    }

    public Boolean getCoreStatusOfModule(int num){
        return studentModules.get(num).getCoreStatus();
    }

    public Double getMarksSecuredInModule(int num){
        return studentModules.get(num).getMarksSecured();
    }

    public void setEnrolledStatusOfModule(int num, Boolean status, String moduleCode){
        studentModules.get(num).setEnrolledStatus(true);
        db.updateStudentEnrolledStatus(getUserId(), moduleCode, true);
    }


    public void setModuleProperties(){
        ResultSet resultSet = db.getStudentModuleProperties(getUserId());
        try {
            while (resultSet.next()) {
                Modules mod = new Modules();
                mod.setMarksSecured(resultSet.getDouble("marksSecured"));
                mod.setEnrolledStatus(resultSet.getBoolean("enrolledStatus"));
                studentModules.add(mod);
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void setStudentProperties(){
        ResultSet resultSet = db.getStudentProperties(studentId);
        try {
            while(resultSet.next()){
                setAddress(resultSet.getString("address"));
                setPhoneNo(resultSet.getString("phoneNo"));
                setLevel(resultSet.getInt("level"));
                setStudentName(resultSet.getString("studentName"));
                setCourseEnrolledIn(resultSet.getString("courseEnrolledIn"));
                setFailStatus(resultSet.getString("failStatus"));
                setRemarks(resultSet.getString("remarks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}