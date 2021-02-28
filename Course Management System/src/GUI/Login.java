package GUI;

import javax.swing.*;

import Bodies.Admin;
import DBHelpers.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login implements GUI{

    private JFrame window = new JFrame();
    private JTextField usernameTextField = new JTextField(20);
    private JPasswordField passwordTextField = new JPasswordField(20);
    private String[] userTypes = {"Student", "Teacher", "Administrator"};
    private JComboBox<String> selectUserType = new JComboBox<>(userTypes);
    private Boolean isValidLogin = false;
    private Admin admin = new Admin();
    private ManagementDatabase db = new ManagementDatabase();


    @Override
    public void showFrame(String username, String password, String userType){
        window = new JFrame();
        Image icon = Toolkit.getDefaultToolkit().getImage("src/GUI/TitleLogo.png");    
        window.setIconImage(icon);   
        window.setTitle("Course Management System");
        window.setSize(1400, 800);
        window.setExtendedState(Frame.MAXIMIZED_BOTH);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(3);
        JButton logoutButton = new JButton("Log Out");

        if(userType == "Student"){
            StudentPanel panel1 = new StudentPanel(username, password, logoutButton);
            window.add(panel1);
        } else if(userType == "Teacher"){
            TeacherPanel panel2 = new TeacherPanel(username, password, logoutButton);
            window.add(panel2);
        } else if(userType == "Administrator"){
            AdminPanel panel3 = new AdminPanel(logoutButton);
            window.add(panel3);
        }
        
        logoutButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int logout = JOptionPane.showConfirmDialog(window, "Are you sure you want to logout?", "Confirm Logout", 1);
                if(logout == JOptionPane.YES_OPTION){
                    window.dispose();
                    showLoginFrame();
                }
            }
        });
        
        window.setVisible(true);
        
    }

    public void showLoginFrame(){

        JFrame loginFrame = new JFrame("Log in");
        loginFrame.setSize(450, 250);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Course Management System");
        title.setFont(new Font("sansserif", 1, 24));
        titlePanel.add(title);


        JButton loginButton = new JButton("Login");
        loginButton.setBackground(Color.decode("#212121"));
        loginButton.setForeground(Color.white);
        loginButton.setFont(new Font("sansserif", 1, 16));
        JButton registerButton = new JButton("Register");
        registerButton.setBackground(Color.decode("#212121"));
        registerButton.setForeground(Color.white);
        registerButton.setFont(new Font("sansserif", 1, 16));

        
        

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(4, 2, 8, 10));
        bodyPanel.add(new JLabel("Username :"));
        bodyPanel.add(usernameTextField);
        bodyPanel.add(new JLabel("Password :"));
        bodyPanel.add(passwordTextField);
        bodyPanel.add(new JLabel("User Type :"));
        bodyPanel.add(selectUserType);
        bodyPanel.add(registerButton);
        bodyPanel.add(loginButton);

        JPanel mainPanel = new JPanel();
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(bodyPanel, BorderLayout.SOUTH);
        loginFrame.add(mainPanel);
        loginFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());
                String userType = (String) selectUserType.getSelectedItem();
                if(userType != "Administrator"){
                    try {
                        Scanner sc = new Scanner(new FileReader("src/Files/LogIn.txt"));
                        while(sc.hasNextLine()){
                            Scanner sc2 = new Scanner(sc.nextLine());
                            while(sc2.hasNext()){
                                String id = sc2.next();
                                String pw = sc2.next();
                                String type = sc2.next();
                                if(username.equals(id) && password.equals(pw) && userType.equals(type)){
                                    isValidLogin = true;
                                    break;
                                }
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        Scanner sc = new Scanner(new FileReader("src/Files/Admin.txt"));
                        while(sc.hasNext()){
                            String adminId = sc.next();
                            String pw = sc.next();
                            admin.setAdminId(adminId);
                            admin.setPassword(pw);
                            if(username.equals(admin.getAdminId()) && password.equals(admin.getPassword())){
                                isValidLogin = true;
                                userType = "Administrator";
                            }
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    
                }
                
                if(isValidLogin == true){
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                    selectUserType.setSelectedIndex(0);
                    loginFrame.dispose();
                    showFrame(username, password, userType);
                } else{
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
                }
            }
        });


        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                showRegistrationFrame();
            }
        });

        
    }


    private void showRegistrationFrame(){

        JFrame registrationWindow = new JFrame("Registration");
        registrationWindow.setLocation(300, 50);
        registrationWindow.setSize(500, 600);
        registrationWindow.setDefaultCloseOperation(2);
  
  

        JPanel editDetailsPanel = new JPanel();
        editDetailsPanel.setLayout(new BoxLayout(editDetailsPanel, 1));
        editDetailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#ffbf12")), "Registration: "));



        JPanel editDetailsTitlePanel = new JPanel();
        JLabel editDetailTitle = new JLabel("Register as a Student");
        editDetailTitle.setFont(new Font("SansSerif", 1, 24));
        editDetailsTitlePanel.add(editDetailTitle, BorderLayout.CENTER);
        editDetailsTitlePanel.setBackground(Color.decode("#212121"));
        editDetailTitle.setForeground(Color.WHITE);

        ResultSet modulesSet = db.getCourses();
        int i=0;
        try {
            while(modulesSet.next()){
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String[] courses = new String[i];
        String[] courseTags = new String[i];
        modulesSet = db.getCourses();
        i=0;
        try {
            while(modulesSet.next()){
                courses[i] = modulesSet.getString("courseName");
                courseTags[i] = modulesSet.getString("courseTag");
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        String[] levels = {"1", "2", "3"};
        JComboBox<String> coursesAvailable = new JComboBox<>(courses);
        JComboBox<String> levelComboBox = new JComboBox<>(levels);

        JPanel editDetailsBodyPanel = new JPanel();
        editDetailsBodyPanel.setLayout(new GridLayout(8, 2, 0, 32));
        JLabel nameJLabel = new JLabel("    Name: ");
        JTextField nameTextField = new JTextField();
        JLabel phoneJLabel = new JLabel("    Phone No: ");
        JTextField phoneTextField = new JTextField();
        JLabel addressJLabel = new JLabel("    Address: ");
        JTextField addressTextField = new JTextField();
        JLabel passwordJLabel = new JLabel("    Password: ");
        JPasswordField passwordTextField = new JPasswordField();
        JLabel confirmPasswordJLabel = new JLabel("    Confirm Password: ");
        JPasswordField confirmPasswordTextField = new JPasswordField();
        JLabel coursesAvailableJLabel = new JLabel("    Choose Course: ");
        JLabel chooseLevelLabel = new JLabel("    Level: ");

        editDetailsBodyPanel.add(new JLabel(""));
        editDetailsBodyPanel.add(new JLabel(""));
        editDetailsBodyPanel.add(nameJLabel);
        editDetailsBodyPanel.add(nameTextField);
        editDetailsBodyPanel.add(phoneJLabel);
        editDetailsBodyPanel.add(phoneTextField);
        editDetailsBodyPanel.add(addressJLabel);
        editDetailsBodyPanel.add(addressTextField);
        editDetailsBodyPanel.add(coursesAvailableJLabel);
        editDetailsBodyPanel.add(coursesAvailable);
        editDetailsBodyPanel.add(chooseLevelLabel);
        editDetailsBodyPanel.add(levelComboBox);
        editDetailsBodyPanel.add(passwordJLabel);
        editDetailsBodyPanel.add(passwordTextField);
        editDetailsBodyPanel.add(confirmPasswordJLabel);
        editDetailsBodyPanel.add(confirmPasswordTextField);
                

        JPanel buttonsPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("SansSerif", 1, 16));
        submitButton.setBackground(Color.decode("#f0bc2e"));
        submitButton.setForeground(Color.white);
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", 1, 16));
        backButton.setBackground(Color.decode("#f0bc2e"));
        backButton.setForeground(Color.white);
                
        buttonsPanel.add(backButton, BorderLayout.NORTH);
        buttonsPanel.add(submitButton, BorderLayout.LINE_END);

        editDetailsPanel.add(editDetailsTitlePanel);
        editDetailsPanel.add(editDetailsBodyPanel);
        editDetailsPanel.add(buttonsPanel);

        registrationWindow.add(editDetailsPanel);

        registrationWindow.setVisible(true);

        
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = nameTextField.getText();
                String phoneNo = phoneTextField.getText();
                String address = addressTextField.getText();
                String courseTag = courseTags[coursesAvailable.getSelectedIndex()];
                String level = (String) levelComboBox.getSelectedItem();
                String password1 = String.valueOf(passwordTextField.getPassword());
                String password2 = String.valueOf(confirmPasswordTextField.getPassword());
                if(password1.equals(password2)){
                    if(!password1.isEmpty() && !studentName.isEmpty() && !address.isEmpty() && !phoneNo.isEmpty()){
                        String userId = generateUserId();
                        db.saveUserDetails(level, studentName, courseTag, "Fail", userId, "Student", password1, address, phoneNo);
                        JOptionPane.showMessageDialog(null, "Your username is : " + userId + "\nYour Password is " + password1 + "\n\nNote down these credentials or else you wont be able to login!!\n\n", "IMPORTANT MESSAGE!!!!!", 1);
                        registrationWindow.dispose();
                        showLoginFrame();
                    } else{
                        JOptionPane.showMessageDialog(registrationWindow, "Please fill in all the boxes!", "Empty Inputs", JOptionPane.ERROR_MESSAGE);
                    }
                } else{
                    JOptionPane.showMessageDialog(registrationWindow, "Passwords do not match! Please try again.", "Passwords Invalid", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                registrationWindow.dispose();
                showLoginFrame();
            }
        });


    }


    private String generateUserId(){

        int idNumber = 1;
        
        try {
            Scanner sc = new Scanner(new FileReader("src/Files/LogIn.txt"));
            
            while(sc.hasNextLine()){
                Scanner sc2 = new Scanner(sc.nextLine());
                while(sc2.hasNext()){
                    sc2.next();
                    sc2.next();
                    String type = sc2.next();
                    if(type.equals("Student")){
                        idNumber++;
                    }
                }
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if(idNumber < 10){
            return "ST0000"+idNumber;
        } else{
            return "ST000"+idNumber;
        }

    }

    



}