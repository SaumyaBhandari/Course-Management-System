package Bodies;

import java.sql.ResultSet;
import java.sql.SQLException;
import DBHelpers.*;

public class Teacher extends Users{

    private String teacherName;
    private String teacherId;
    private ManagementDatabase db = new ManagementDatabase();

    public Teacher(String userId, String password){
        this.teacherId = userId;
        super.setUserId(userId);
        super.setUserPassword(password);
        super.setUserType("Teacher");
        setTeacherProperties();
    }

    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherProperties() {
        ResultSet resultSet = db.getTeacherProperties(teacherId);
        try {
            while (resultSet.next()) {
                setAddress(resultSet.getString("address"));
                setPhoneNo(resultSet.getString("phoneNo"));
                setTeacherName(resultSet.getString("teacherName"));
                setUserPassword(resultSet.getString("userPassword"));
                System.out.println("Teacher set");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    



}


