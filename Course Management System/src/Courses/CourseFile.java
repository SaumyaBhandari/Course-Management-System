package Courses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CourseFile {

    public CourseFile(){
        getDataFromCourseFile();
    }

    public ArrayList<Course> courses = new ArrayList<Course>();

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public void addNewCourse(String courseName, String courseTag, String level, String stream, Boolean status) {
        courses.add(new Course(courseName, courseTag, level,
                        stream, status));
        createCourseFile();
    }

    public void getDataFromCourseFile() {
        courses.clear();
        List<String> courseNames = new ArrayList<>();
        List<String> courseTags = new ArrayList<>();
        List<String> levels = new ArrayList<>();
        List<String> streams = new ArrayList<>();
        List<Boolean> statuses = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new FileReader("src/Files/Coures.txt"));
            while(sc.hasNextLine()){
                courseNames.add(sc.nextLine());
                courseTags.add(sc.nextLine());
                levels.add((sc.nextLine()));
                streams.add(sc.nextLine());
                statuses.add(Boolean.parseBoolean(sc.nextLine()));
            }
    
            for(int i = 0; i<courseNames.size(); i++){
                courses.add(new Course(courseNames.get(i), courseTags.get(i), levels.get(i),
                        streams.get(i), statuses.get(i)));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateCourseFile(String courseTag, Boolean status){
        int cancelIndex = 0;
        for (Iterator<Course> itr = courses.iterator(); itr.hasNext(); cancelIndex++) {
            if (itr.next().getCourseTag().equalsIgnoreCase(courseTag)) {
                break;
            }
        }
        courses.get(cancelIndex).setCancelStatus(status);

        File f = new File("src/Files/Coures.txt");
        f.delete();

        createCourseFile();
    }

    public void deleteCourse(String courseTag){
        int deleteIndex = 0;

        for (Iterator<Course> itr = courses.iterator(); itr.hasNext(); deleteIndex++) {
            if (itr.next().getCourseTag().equalsIgnoreCase(courseTag)) {
                break;
            }
        }
        courses.remove(deleteIndex);

        createCourseFile();
    }

    public void createCourseFile(){
        FileWriter file;
        try {
            file = new FileWriter("src/Files/Coures.txt");
            for(Course i: courses){
                file.write(i.getCourseName() + "\n" + i.getCourseTag() + "\n" + i.getLevel() + "\n" + i.getStream() +"\n"+ i.getCancelStatus()+ "\n");
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        getDataFromCourseFile();
    }
}