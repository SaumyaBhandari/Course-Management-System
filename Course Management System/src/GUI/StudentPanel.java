package GUI;

import Bodies.Student;
import Courses.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings("serial")
public class StudentPanel extends JPanel{

    private JPanel optionsLeftStudent = new JPanel();
    private JPanel optionsRightStudent = new JPanel();


    private JPanel profilePanel;
    private JPanel coursePanel;
    private JPanel modulesPanel;
    private JPanel dashBoardPanel;
    private JRadioButton viewProfile;
    private JRadioButton viewCourses;
    private JRadioButton viewModules;
    private JRadioButton dashBoard;
    private JLabel topTextOfViewPanel;
    private JPanel detailsPanel;
    private ButtonGroup optionsGroup;
    private Student student;
    private Course course;

    public StudentPanel(String studentId, String password, JButton logoutButton) {


        student = new Student(studentId, password);
        course = new Course(student.getCourseEnrolledIn());



        setLayout(new BoxLayout(this, 0));

        optionsLeftStudent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#ffbf12")), "Options: "));
        optionsLeftStudent.setLayout(new GridLayout(2, 1));
        optionsLeftStudent.setMaximumSize(new DimensionUIResource(500, 1080));
        JPanel optionsLeftStudentTop = new JPanel();
        optionsLeftStudentTop.setLayout(new GridLayout(4, 1));

        profilePanel = new JPanel();
        coursePanel = new JPanel();
        modulesPanel = new JPanel();
        dashBoardPanel = new JPanel();
        profilePanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        profilePanel.setLayout(new GridLayout(1, 1));
        coursePanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        coursePanel.setLayout(new GridLayout(1, 1));
        modulesPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        modulesPanel.setLayout(new GridLayout(1, 1));
        dashBoardPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        dashBoardPanel.setLayout(new GridLayout(1, 1));

        dashBoard = new JRadioButton("Dashboard", true);
        viewProfile = new JRadioButton("Profile");
        viewCourses = new JRadioButton("Courses");
        viewModules = new JRadioButton("Your Module Details     ");

        Font fontForOptions = new Font("SansSerif", 0, 24);
        viewProfile.setFont(fontForOptions);
        viewCourses.setFont(fontForOptions);
        viewModules.setFont(fontForOptions);
        dashBoard.setFont(fontForOptions);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(dashBoard);
        optionsGroup.add(viewProfile);
        optionsGroup.add(viewCourses);
        optionsGroup.add(viewModules);

        dashBoardPanel.add(dashBoard);
        profilePanel.add(viewProfile);
        coursePanel.add(viewCourses);
        modulesPanel.add(viewModules);

        logoutButton.setBackground(Color.decode("#f0bc2e"));
        logoutButton.setForeground(Color.white);
        logoutButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.decode("#212121")));

        optionsLeftStudentTop.add(dashBoardPanel);
        optionsLeftStudentTop.add(profilePanel);
        optionsLeftStudentTop.add(coursePanel);
        optionsLeftStudentTop.add(modulesPanel);

        JPanel optionsLeftStudentBottom = new JPanel();
        optionsLeftStudentBottom.setLayout(new BoxLayout(optionsLeftStudentBottom, 1));
        JPanel logoPanel = new JPanel();
        logoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        logoPanel.setBackground(Color.WHITE);
        ImageIcon logo = new ImageIcon("src/GUI/Logo.png");
        JLabel image = new JLabel();
        image.setIcon(logo);
        logoPanel.add(image, BorderLayout.CENTER);
        JPanel logoutButtonPanel = new JPanel(new GridLayout(1, 1));
        logoutButtonPanel.add(logoutButton);
        optionsLeftStudentBottom.add(logoPanel);
        optionsLeftStudentBottom.add(logoutButtonPanel);
        optionsLeftStudent.add(optionsLeftStudentTop);
        optionsLeftStudent.add(optionsLeftStudentBottom);
        

        optionsRightStudent.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#ffbf12")), "View: "));
        optionsRightStudent.setLayout(new BoxLayout(optionsRightStudent, 1));

        JPanel topViewPanel = new JPanel();
        topTextOfViewPanel = new JLabel();
        topTextOfViewPanel.setForeground(Color.decode("#230030"));
        topTextOfViewPanel.setFont(new Font("Sans Serif", 1, 40));
        detailsPanel = new JPanel();



        dashBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColorOfSelected();
                topTextOfViewPanel.setText("Welcome " + student.getStudentName() + "!");
                addDashboardToDetailsPanel();
            }
        });
        dashBoard.doClick();



        viewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColorOfSelected();
                topTextOfViewPanel.setText("Your Details are: ");
                addProfileToDetailsPanel();
            }
        });



        viewCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColorOfSelected();
                topTextOfViewPanel.setText("Modules of the course that you are enrolled in: ");
                addCoursesToDetailsPanel();
            }
        });



        viewModules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColorOfSelected();
                topTextOfViewPanel.setText("The modules for this semester are: ");
                addModuleDetailsToDetailsPanel();
            }
        });


        
        topViewPanel.add(topTextOfViewPanel, BorderLayout.CENTER);
        optionsRightStudent.add(topViewPanel);
        optionsRightStudent.add(detailsPanel);


        add(optionsLeftStudent);
        add(optionsRightStudent);

    }












    
    private void addDashboardToDetailsPanel(){

        

        detailsPanel.removeAll();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));

        JPanel dashBoardModulesPanel = new JPanel();
        dashBoardModulesPanel.setLayout(new GridLayout(1, 1, 8, 16));
        
        JPanel viewMoreButtonPanel = new JPanel();
        viewMoreButtonPanel.setBackground(Color.white);
        JButton viewMoreButton = new JButton("Manage Enrollment");
        viewMoreButton.setFont(new Font("sansserif", 1, 16));
        viewMoreButton.setBackground(Color.decode("#f0bc2e"));
        viewMoreButton.setForeground(Color.white);
        viewMoreButtonPanel.add(viewMoreButton);

        viewMoreButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int options = 0;
                for(int i=0; i<course.getNoOfModules(); i++){
                    if(student.getEnrolledStatusOfModule(i) == false){
                        options++;
                    }
                }
                if(options == 1 || options == 0){
                    JOptionPane.showMessageDialog(null, "You have already chosen your optional module!!", "Optional Module already set!", 1);
                } else {
                    JFrame enrollFrame = new JFrame();
                    enrollFrame.setSize(300, 400);
                    enrollFrame.setLocationRelativeTo(null);
                    enrollFrame.setTitle("Optional Modules");
                    enrollFrame.setDefaultCloseOperation(1);

                    JPanel titlePanel = new JPanel();
                    JLabel title = new JLabel("Enroll In the module");
                    title.setFont(new Font("sansserif", 1, 16));
                    titlePanel.add(title, BorderLayout.CENTER);

                    JPanel buttonPanel = new JPanel();
                    JPanel modulesListPanel = new JPanel();

                    modulesListPanel.setLayout(new GridLayout(2, 1));

                    ButtonGroup optmodules = new ButtonGroup();

                    JRadioButton optModul1 = new JRadioButton(course.getModulesOfTheCourse(22));
                    JRadioButton optModul2 = new JRadioButton(course.getModulesOfTheCourse(23));
                    optmodules.add(optModul1);
                    optmodules.add(optModul2);
                    
                    modulesListPanel.add(optModul1);
                    modulesListPanel.add(optModul2);

                    JButton enrollButton = new JButton("Enroll");
                    enrollButton.setBackground(Color.decode("#f0bc2e"));
                    enrollButton.setForeground(Color.white);
                    enrollButton.setFont(new Font("sansserif", 1, 16));
                    enrollButton.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e) {
                            int yesno = JOptionPane.showConfirmDialog(null, "Are you sure that you want to enroll in this module?\nYou will study this module in the sixth semster!");
                            if(yesno == JOptionPane.YES_OPTION){    
                                if(optModul1.isSelected()){
                                    student.setEnrolledStatusOfModule(22, true, course.getModuleCodes(22));
                                }else if(optModul2.isSelected()){
                                    student.setEnrolledStatusOfModule(23, true, course.getModuleCodes(23));
                                }
                                viewProfile.doClick();
                                dashBoard.doClick();
                            } 
                            enrollFrame.dispose();   
                        };
                    });
                    buttonPanel.add(enrollButton, BorderLayout.CENTER);

                    JPanel mainPanel = new JPanel();
                    mainPanel.setLayout(new BoxLayout(mainPanel, 1));
                    mainPanel.add(titlePanel);
                    mainPanel.add(modulesListPanel);
                    mainPanel.add(buttonPanel);

                    enrollFrame.add(mainPanel);

                    enrollFrame.setVisible(true);

                }
            }
        });


        JPanel year1 = new JPanel();
        year1.setLayout(new BoxLayout(year1, 1));
        year1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1), "You are currently in Year 1: "));
        year1.setBackground(Color.white);
        JPanel year2 = new JPanel();
        year2.setLayout(new BoxLayout(year2, 1));
        year2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1), "You are currently in Year 2: "));
        year2.setBackground(Color.white);
        JPanel year3 = new JPanel();
        year3.setLayout(new BoxLayout(year3, 1));
        year3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1), "You are currently in Year 3: "));
        year3.setBackground(Color.white);

        if(student.getLevel() == 1){
            JPanel year1ModulesPanel = new JPanel(new GridLayout(13, 2, 0, 16));
            year1ModulesPanel.setBackground(Color.white);
            year1ModulesPanel.add(new JLabel(""));
            year1ModulesPanel.add(new JLabel(""));
            year1ModulesPanel.add(new JLabel("        Module Name"));
            year1ModulesPanel.add(new JLabel("Enrollment Status"));
            year1ModulesPanel.add(new JLabel(""));
            year1ModulesPanel.add(new JLabel(""));
            for(int i=0; i<8; i++){
                year1ModulesPanel.add(new JLabel("        "+course.getModulesOfTheCourse(i)));
                String enrolled = "Enrolled";
                if(!student.getEnrolledStatusOfModule(i)){
                    enrolled = "Not Enrolled";
                }
                year1ModulesPanel.add(new JLabel(enrolled));
            }
            year1.add(year1ModulesPanel);
            year1.add(viewMoreButtonPanel);
            dashBoardModulesPanel.add(year1);
        } 
        
        else if(student.getLevel() == 2){
            JPanel year2ModulesPanel = new JPanel(new GridLayout(13, 2, 0, 16));
            year2ModulesPanel.setBackground(Color.white);
            year2ModulesPanel.add(new JLabel(""));
            year2ModulesPanel.add(new JLabel(""));
            year2ModulesPanel.add(new JLabel("        Module Name"));
            year2ModulesPanel.add(new JLabel("Enrollment Status"));
            for(int i=8; i<16; i++){
                year2ModulesPanel.add(new JLabel("        "+course.getModulesOfTheCourse(i)));
                String enrolled = "Enrolled";
                if(!student.getEnrolledStatusOfModule(i)){
                    enrolled = "Not Enrolled";
                }
                year2ModulesPanel.add(new JLabel(enrolled));
            }
            year2.add(year2ModulesPanel);
            year2.add(viewMoreButtonPanel);
            dashBoardModulesPanel.add(year2);
        } 
        
        else if(student.getLevel() == 3){
            JPanel year3ModulesPanel = new JPanel(new GridLayout(13, 2, 0, 16));
            year3ModulesPanel.setBackground(Color.white);
            year3ModulesPanel.add(new JLabel(""));
            year3ModulesPanel.add(new JLabel(""));
            year3ModulesPanel.add(new JLabel("        Module Name"));
            year3ModulesPanel.add(new JLabel("Enrollment Status"));
            for(int i=16; i<course.getNoOfModules(); i++){
                year3ModulesPanel.add(new JLabel("        "+course.getModulesOfTheCourse(i)));
                String enrolled = "Enrolled";
                if(!student.getEnrolledStatusOfModule(i)){
                    enrolled = "Not Enrolled";
                }
                year3ModulesPanel.add(new JLabel(enrolled));
            }
            year3.add(year3ModulesPanel);
            year3.add(viewMoreButtonPanel);
            dashBoardModulesPanel.add(year3);
        }


        
        JPanel dashBoardOptionsPanel = new JPanel();
        dashBoardOptionsPanel.setLayout(new GridLayout(3, 5, 64, 0));
        dashBoardOptionsPanel.setBackground(Color.decode("#212121"));
        dashBoardOptionsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(1), "Activities: "));
        JButton viewProfileButton = new JButton("View Profile");
        JButton viewCoursesButton = new JButton("View Courses");
        JButton viewModulesButton = new JButton("View Modules");
        viewProfileButton.setForeground(Color.white);
        viewCoursesButton.setForeground(Color.white);
        viewModulesButton.setForeground(Color.white);
        viewProfileButton.setBackground(Color.decode("#230030"));
        viewCoursesButton.setBackground(Color.decode("#230030"));
        viewModulesButton.setBackground(Color.decode("#230030"));
        viewProfileButton.setFont(new Font("sansserif", 1, 24));
        viewCoursesButton.setFont(new Font("sansserif", 1, 24));
        viewModulesButton.setFont(new Font("sansserif", 1, 24));
        dashBoardOptionsPanel.add(new JLabel(""));
        dashBoardOptionsPanel.add(new JLabel(""));
        dashBoardOptionsPanel.add(new JLabel(""));
        dashBoardOptionsPanel.add(viewProfileButton);
        dashBoardOptionsPanel.add(viewCoursesButton);
        dashBoardOptionsPanel.add(viewModulesButton);
        dashBoardOptionsPanel.add(new JLabel(""));
        dashBoardOptionsPanel.add(new JLabel(""));
        dashBoardOptionsPanel.add(new JLabel(""));
        dashBoardOptionsPanel.setBackground(Color.WHITE);

        

        viewProfileButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProfile.doClick();
            }
        });

        viewCoursesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCourses.doClick();
            }
        });

        viewModulesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModules.doClick();
            }
        });

        
        
        detailsPanel.add(dashBoardModulesPanel);
        detailsPanel.add(dashBoardOptionsPanel);
    }

    











    private void addProfileToDetailsPanel(){

        detailsPanel.removeAll();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));
        detailsPanel.setFont(new Font("SansSerif", 1, 50));
        JLabel nameoptions = new JLabel("    Name:");
        JLabel name = new JLabel(student.getStudentName());
        JLabel usernameoptions = new JLabel("    Username:");
        JLabel username = new JLabel(student.getUserId());
        JLabel addressoptions = new JLabel("    Address:");
        JLabel address = new JLabel(student.getAddress());
        JLabel phonenooptions = new JLabel("    Phone Number:");
        JLabel phoneno = new JLabel(student.getPhoneNo());
        JLabel leveloptions = new JLabel("    Level:");
        JLabel level = new JLabel(Integer.toString(student.getLevel()));
        Font profilesFont = new Font("Arial", 1, 24);
        nameoptions.setFont(profilesFont);
        name.setFont(profilesFont);
        usernameoptions.setFont(profilesFont);
        username.setFont(profilesFont);
        addressoptions.setFont(profilesFont);
        address.setFont(profilesFont);
        phonenooptions.setFont(profilesFont);
        phoneno.setFont(profilesFont);
        leveloptions.setFont(profilesFont);
        level.setFont(profilesFont);
        
        JPanel profileDescriptionPanel = new JPanel(new GridLayout(0, 2, 0, 8));
        profileDescriptionPanel.add(nameoptions);
        profileDescriptionPanel.add(name);
        profileDescriptionPanel.add(usernameoptions);
        profileDescriptionPanel.add(username);
        profileDescriptionPanel.add(leveloptions);
        profileDescriptionPanel.add(level);
        profileDescriptionPanel.add(addressoptions);
        profileDescriptionPanel.add(address);
        profileDescriptionPanel.add(phonenooptions);
        profileDescriptionPanel.add(phoneno);
        
        detailsPanel.add(profileDescriptionPanel);
        detailsPanel.add(new JPanel(new GridLayout(5, 1, 0, 64)));

    }

















    private void addCoursesToDetailsPanel(){

        
        detailsPanel.removeAll();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));
        JPanel coursesButtonPanel = new JPanel();
        coursesButtonPanel.setLayout(new BoxLayout(coursesButtonPanel, 0));

        JButton course1 = new JButton(course.getCourseName());
        course1.setFont(new Font("SansSerif", 1, 20));
        course1.setBackground(Color.decode("#230030"));
        course1.setForeground(Color.white);

        coursesButtonPanel.add(course1);
        JPanel courseDetailsPanel = new JPanel();
        JScrollPane pane = new JScrollPane(courseDetailsPanel);
        courseDetailsPanel.setLayout(new GridLayout(30, 4, 0, 8));
        courseDetailsPanel.setBackground(Color.WHITE);
        courseDetailsPanel.add(new JLabel(""));
        courseDetailsPanel.add(new JLabel(""));
        courseDetailsPanel.add(new JLabel(""));
        courseDetailsPanel.add(new JLabel(""));
        courseDetailsPanel.add(new JLabel("      Module Name"));
        courseDetailsPanel.add(new JLabel("Module Code"));
        courseDetailsPanel.add(new JLabel("Credits"));
        courseDetailsPanel.add(new JLabel("Semester"));
        courseDetailsPanel.add(new JLabel(""));
        courseDetailsPanel.add(new JLabel(""));
        courseDetailsPanel.add(new JLabel(""));
        courseDetailsPanel.add(new JLabel(""));            
        for(int i=0; i<course.getNoOfModules(); i++){
            String moduleName = course.getModulesOfTheCourse(i);
            String moduleCode = course.getModuleCodes(i);
            String credits = Integer.toString(course.getCreditsOfModules(i));
            String semester = Integer.toString(course.getSemesterOfModule(i));
            courseDetailsPanel.add(new JLabel("      "+moduleName));
            courseDetailsPanel.add(new JLabel(moduleCode));
            courseDetailsPanel.add(new JLabel(credits));
            courseDetailsPanel.add(new JLabel(semester));
        }
        course1.doClick();
        
        detailsPanel.add(coursesButtonPanel);
        detailsPanel.add(pane);
    }
   



    








    private void addModuleDetailsToDetailsPanel(){

        detailsPanel.removeAll();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));

        JPanel displayModulesPanel = new JPanel(new GridLayout(2, 1));
        
        JPanel firstSemPanel = new JPanel();
        JPanel secondSemPanel = new JPanel();
        firstSemPanel.setLayout(new GridLayout(7, 4, 0, 16));
        secondSemPanel.setLayout(new GridLayout(7, 4, 0, 16));
        firstSemPanel.setBackground(Color.white);
        secondSemPanel.setBackground(Color.white);
        firstSemPanel.add(new JLabel(""));
        firstSemPanel.add(new JLabel(""));
        firstSemPanel.add(new JLabel(""));
        firstSemPanel.add(new JLabel(""));
        firstSemPanel.add(new JLabel("        Module Name"));
        firstSemPanel.add(new JLabel("Module Code"));
        firstSemPanel.add(new JLabel("Instructor Name"));
        firstSemPanel.add(new JLabel("Marks Secured"));
        secondSemPanel.add(new JLabel(""));
        secondSemPanel.add(new JLabel(""));
        secondSemPanel.add(new JLabel(""));
        secondSemPanel.add(new JLabel(""));
        secondSemPanel.add(new JLabel("        Module Name"));
        secondSemPanel.add(new JLabel("Module Code"));
        secondSemPanel.add(new JLabel("Instructor Name"));
        secondSemPanel.add(new JLabel("Marks Secured"));

        
        if(student.getLevel() == 1){
            firstSemPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#212121")), "Semester 1: "));
            secondSemPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#212121")), "Semester 2: "));
            for(int i=0; i<course.getNoOfModules(); i++){
                if(i<4){
                    firstSemPanel.add(new JLabel("        "+course.getModulesOfTheCourse(i)));
                    firstSemPanel.add(new JLabel(course.getModuleCodes(i)));
                    firstSemPanel.add(new JLabel(course.getModuleInstructorName(i)));
                    firstSemPanel.add(new JLabel(student.getMarksSecuredInModule(i).toString()));
                }else if(i<8){
                    secondSemPanel.add(new JLabel("        "+course.getModulesOfTheCourse(i)));
                    secondSemPanel.add(new JLabel(course.getModuleCodes(i)));
                    secondSemPanel.add(new JLabel(course.getModuleInstructorName(i)));
                    secondSemPanel.add(new JLabel(student.getMarksSecuredInModule(i).toString()));
                }
            }
        } 
        else if(student.getLevel() == 2){
            firstSemPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#212121")), "Semester 3: "));
            secondSemPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#212121")), "Semester 4: "));
            for(int i=8; i<16; i++){
                if(i<12){
                    firstSemPanel.add(new JLabel("        "+course.getModulesOfTheCourse(i)));
                    firstSemPanel.add(new JLabel(course.getModuleCodes(i)));
                    firstSemPanel.add(new JLabel(course.getModuleInstructorName(i)));
                    firstSemPanel.add(new JLabel(student.getMarksSecuredInModule(i).toString()));
                }else if(i<16){
                    secondSemPanel.add(new JLabel("        "+course.getModulesOfTheCourse(i)));
                    secondSemPanel.add(new JLabel(course.getModuleCodes(i)));
                    secondSemPanel.add(new JLabel(course.getModuleInstructorName(i)));
                    secondSemPanel.add(new JLabel(student.getMarksSecuredInModule(i).toString()));
                }
            }
        } 
        else if(student.getLevel() == 3){
            firstSemPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#212121")), "Semester 5: "));
            secondSemPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#212121")), "Semester 6: "));
            for(int i=16; i<course.getNoOfModules(); i++){
                if(student.getEnrolledStatusOfModule(i)){
                    if(i<20){
                        firstSemPanel.add(new JLabel("        "+course.getModulesOfTheCourse(i)));
                        firstSemPanel.add(new JLabel(course.getModuleCodes(i)));
                        firstSemPanel.add(new JLabel(course.getModuleInstructorName(i)));
                        firstSemPanel.add(new JLabel(student.getMarksSecuredInModule(i).toString()));
                    }else if(i < course.getNoOfModules()){
                        secondSemPanel.add(new JLabel("        "+course.getModulesOfTheCourse(i)));
                        secondSemPanel.add(new JLabel(course.getModuleCodes(i)));
                        secondSemPanel.add(new JLabel(course.getModuleInstructorName(i)));
                        secondSemPanel.add(new JLabel(student.getMarksSecuredInModule(i).toString()));
                    }
                }    
            }
            secondSemPanel.add(new JLabel(""));
            secondSemPanel.add(new JLabel(""));
            secondSemPanel.add(new JLabel(""));
            secondSemPanel.add(new JLabel(""));
        }

        JButton printResultButton = new JButton("View Latest Result");
        printResultButton.setBackground(Color.decode("#f0bc2e"));
        printResultButton.setForeground(Color.white);
        printResultButton.setFont(new Font("sansserif", 1, 16));
        printResultButton.setBorder(BorderFactory.createLineBorder(Color.decode("#212121")));


        JPanel printResultButtonPanel = new JPanel(new GridLayout(1, 5));
        printResultButtonPanel.setBackground(Color.white);
        printResultButtonPanel.add(printResultButton);
        printResultButtonPanel.add(new JLabel(""));
        printResultButtonPanel.add(new JLabel(""));
        printResultButtonPanel.add(new JLabel(""));
        printResultButtonPanel.add(new JLabel(""));
        printResultButtonPanel.add(new JLabel(""));

        printResultButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = student.getStudentName()+" ("+student.getUserId()+") Result Year "+student.getLevel();
                saveResult(student.getLevel(), filename);
                createResultWindow(student.getLevel(), filename);
            }
        });

        displayModulesPanel.add(firstSemPanel);
        displayModulesPanel.add(secondSemPanel);
        
        detailsPanel.add(displayModulesPanel);
        detailsPanel.add(printResultButtonPanel);

        
    }



    private void saveResult(int level, String filename){

        int startIndex = 0;
        int endIndex = 8;
        int sem = 1;
        if(level == 2){
            startIndex = 8;
            endIndex = 16;
            sem = 3;
        } else if(level == 3){
            startIndex = 16;
            endIndex = 24;
            sem = 5;
        }

        try {
            File resultFile = new File("src/Files/Results/"+filename+".txt");
            if (resultFile.createNewFile()) {
              System.out.println("File created: " + resultFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        try {

            FileWriter writer = new FileWriter("src/Files/Results/"+filename+".txt");
            int totalMarks = 0;
            int failCount = 0;
            writer.write("                                  *** Result Slip of the Year"+student.getLevel()+" ***\n");
            writer.write("\nName: " + student.getStudentName() + "                Level: " + level+"                Course: "+student.getCourseEnrolledIn()); 

            writer.write("\n\n\n\nSemester: "+sem);  
            for(int i=startIndex; i<(startIndex+4); i++){
                totalMarks += student.getMarksSecuredInModule(i);
                writer.write("\n        "+course.getModulesOfTheCourse(i)+"    :    "+student.getMarksSecuredInModule(i));
                if(student.getMarksSecuredInModule(i) < 40){
                    failCount ++;
                }
            }
            if(failCount >= 2){
                student.setFailStatus("Failed");
            }
            writer.write("\n\nTotal        :          " + totalMarks);
            writer.write("\n\nStatus: " + student.getFailStatus());


            totalMarks = 0;
            failCount = 0;
            writer.write("\nSemester: "+(++sem));  
            for(int i=(startIndex+4); i<endIndex; i++){
                if(student.getEnrolledStatusOfModule(i)){
                    totalMarks += student.getMarksSecuredInModule(i);
                    writer.write("\n        "+course.getModulesOfTheCourse(i)+"       :        "+student.getMarksSecuredInModule(i));
                    if(student.getMarksSecuredInModule(i) < 40){
                        failCount ++;
                    }
                }
            }
            if(failCount >= 2){
                student.setFailStatus("Failed");
            }
            writer.write("\nTotal        :         " + totalMarks);
            writer.write("\n\nStatus: " + student.getFailStatus());
            writer.close();
            System.out.println("Successfully Printed in file.");
            printResult(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }   

    private void printResult(String filename){
        try {
            File inputFile = new File("src/Files/Results/"+filename+".txt");
            Scanner fileReader = new Scanner(inputFile);
            while (fileReader.hasNextLine()) {
              String data = fileReader.nextLine();
              System.out.println(data);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    private void createResultWindow(int level, String fileName){


        JFrame resultFrame = new JFrame();
        resultFrame.setSize(580, 500);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        resultFrame.setTitle("Vew Result");



        JPanel moduleResultPanel = new JPanel();
        moduleResultPanel.setLayout(new BoxLayout(moduleResultPanel, 1));
        
            try {
                File inputFile = new File("src/Files/Results/"+fileName+".txt");
                Scanner fileReader = new Scanner(inputFile);
                while (fileReader.hasNextLine()) {
                  String data = fileReader.nextLine();
                  JLabel dataLabel = new JLabel(data);
                  dataLabel.setFont(new Font("sansserif", 0
                  , 16));
                  moduleResultPanel.add(dataLabel);
                }
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


        resultFrame.add(moduleResultPanel);
        resultFrame.setVisible(true);

    }
















    private void changeColorOfSelected(){

        if(viewProfile.isSelected()){
            viewProfile.setBackground(Color.decode("#212121"));
            viewProfile.setForeground(Color.WHITE);
            viewCourses.setBackground(Color.WHITE);
            viewCourses.setForeground(Color.decode("#212121"));
            viewModules.setBackground(Color.WHITE);
            viewModules.setForeground(Color.decode("#212121"));
            dashBoard.setBackground(Color.WHITE);
            dashBoard.setForeground(Color.decode("#212121"));
        } else if (viewCourses.isSelected()) {
            viewProfile.setBackground(Color.WHITE);
            viewProfile.setForeground(Color.decode("#212121"));
            viewCourses.setBackground(Color.decode("#212121"));
            viewCourses.setForeground(Color.WHITE);
            viewModules.setBackground(Color.WHITE);
            viewModules.setForeground(Color.decode("#212121"));
            dashBoard.setBackground(Color.WHITE);
            dashBoard.setForeground(Color.decode("#212121"));
        } else if (viewModules.isSelected()) {
            viewProfile.setBackground(Color.WHITE);
            viewProfile.setForeground(Color.decode("#212121"));
            viewCourses.setBackground(Color.WHITE);
            viewCourses.setForeground(Color.decode("#212121"));
            viewModules.setBackground(Color.decode("#212121"));
            viewModules.setForeground(Color.WHITE);
            dashBoard.setBackground(Color.WHITE);
            dashBoard.setForeground(Color.decode("#212121"));
        } else if (dashBoard.isSelected()) {
            viewProfile.setBackground(Color.WHITE);
            viewProfile.setForeground(Color.decode("#212121"));
            viewCourses.setBackground(Color.WHITE);
            viewCourses.setForeground(Color.decode("#212121"));
            viewModules.setBackground(Color.WHITE);
            viewModules.setForeground(Color.decode("#212121"));
            dashBoard.setBackground(Color.decode("#212121"));
            dashBoard.setForeground(Color.WHITE);
        }

    }

}
