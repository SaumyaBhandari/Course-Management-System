package Courses;

import java.util.ArrayList;
import java.sql.*;
import DBHelpers.*;

public class Course {

    private String level;
    private String courseName;
    private String courseTag;
    private String stream;
    private Boolean cancelStatus;
    ArrayList<Modules> mods = new ArrayList<Modules>(24);
    ManagementDatabase db = new ManagementDatabase();



    public Course(String courseTag){
        this.courseTag = courseTag;
        insertCourseProperties();
        insertModuleProperties();
    }

    public Course(String courseName, String courseTag, String level, String stream, Boolean cancelStatus){
        this.courseName = courseName;
        this.courseTag = courseTag;
        this.level = level;
        this.stream = stream;
        this.cancelStatus = cancelStatus;
    }

    public Course(String level, String courseName, String stream, ArrayList<Modules> mods){
        this.level = level;
        this.courseName = courseName;
        this.stream = stream;
        this.mods = mods;
        insertCourseProperties();
        insertModuleProperties();
    }

    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStream() {
        return stream;
    }
    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getCourseTag() {
        return courseTag;
    }
    public void setCourseTag(String courseTag) {
        this.courseTag = courseTag;
    }

    public Boolean getCancelStatus() {
        return cancelStatus;
    }
    public void setCancelStatus(Boolean cancelStatus) {
        this.cancelStatus = cancelStatus;
    }


    public int getNoOfModules(){
        return mods.size();
    }

    public String getModulesOfTheCourse(int num) {
        return mods.get(num).getModuleName();
    }

    public String getModuleCodes(int num){
        return  mods.get(num).getModuleCode();
    }

    public int getCreditsOfModules(int num){
        return mods.get(num).getCredits();
    }

    public int getSemesterOfModule(int num){
        return mods.get(num).getSemester();
    }


    public String getModuleInstructorName(int num){
        return mods.get(num).getInstructorName();
    }

    public Boolean getCoreStatusOfModule(int num){
        return mods.get(num).getCoreStatus();
    }


    public void setEnrolledStatusOfModule(int num, Boolean status){
        mods.get(num).setEnrolledStatus(status);
    }



    
    public void insertModuleProperties(){
        ResultSet resultSet = db.getCourseModuleProperties(getCourseTag());
        try {
            while (resultSet.next()) {
                Modules mod = new Modules(resultSet.getString("moduleName"), resultSet.getString("moduleCode"));
                mod.setInstructorName(resultSet.getString("teacherName"));
                mod.setSemester(resultSet.getInt("semester"));
                mod.setCredits(resultSet.getInt("credits"));
                mod.setCoreStatus(resultSet.getBoolean("coreStatus"));
                mods.add(mod);
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void insertCourseProperties(){
        ResultSet resultSet = db.getCourseProperties(getCourseTag());
        try {
            while (resultSet.next()) {
                setLevel(resultSet.getString("level"));
                setCourseName(resultSet.getString("courseName"));
                setCourseTag(resultSet.getString("courseTag"));
                setStream(resultSet.getString("stream"));
                setCancelStatus(resultSet.getBoolean("cancelStatus"));
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }

    



}
