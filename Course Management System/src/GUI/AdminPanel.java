package GUI;

import Bodies.Admin;
import DBHelpers.*;
import Courses.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AdminPanel extends JPanel {

    private JPanel optionsLeftAdmin = new JPanel();
    private JPanel optionsRightAdmin = new JPanel();

    private JPanel dashBoardPanel;
    private JRadioButton dashBoard;
    private JLabel topTextOfViewPanel;
    private JPanel detailsPanel;
    private ButtonGroup optionsGroup;
    private ManagementDatabase db = new ManagementDatabase();
    private Admin admin = new Admin();

    public AdminPanel(JButton logoutButton) {

        setLayout(new BoxLayout(this, 0));

        optionsLeftAdmin.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#ffbf12")), "Options: "));
        optionsLeftAdmin.setLayout(new GridLayout(2, 1));
        optionsLeftAdmin.setMaximumSize(new DimensionUIResource(500, 1080));
        JPanel optionsLeftAdminTop = new JPanel();
        optionsLeftAdminTop.setLayout(new GridLayout(4, 1));
        dashBoardPanel = new JPanel();
        dashBoardPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        dashBoardPanel.setLayout(new GridLayout(1, 1));

        dashBoard = new JRadioButton("Dashboard                   ", true);
        dashBoard.setBackground(Color.decode("#212121"));
        dashBoard.setForeground(Color.WHITE);

        Font fontForOptions = new Font("SansSerif", 0, 24);
        dashBoard.setFont(fontForOptions);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(dashBoard);

        dashBoardPanel.add(dashBoard);

        logoutButton.setBackground(Color.decode("#2d1038"));
        logoutButton.setForeground(Color.white);
        logoutButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.decode("#212121")));

        optionsLeftAdminTop.add(dashBoardPanel);

        JPanel optionsLeftAdminBottom = new JPanel();
        optionsLeftAdminBottom.setLayout(new BoxLayout(optionsLeftAdminBottom, 1));
        JPanel logoPanel = new JPanel();
        logoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        logoPanel.setBackground(Color.WHITE);
        ImageIcon logo = new ImageIcon("src/GUI/Logo.png");
        JLabel image = new JLabel();
        image.setIcon(logo);
        logoPanel.add(image, BorderLayout.CENTER);
        JPanel logoutButtonPanel = new JPanel(new GridLayout(1, 1));
        logoutButtonPanel.add(logoutButton);
        optionsLeftAdminBottom.add(logoPanel);
        optionsLeftAdminBottom.add(logoutButtonPanel);
        optionsLeftAdmin.add(optionsLeftAdminTop);
        optionsLeftAdmin.add(optionsLeftAdminBottom);

        optionsRightAdmin.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#ffbf12")), "View: "));
        optionsRightAdmin.setLayout(new BoxLayout(optionsRightAdmin, 1));

        JPanel topViewPanel = new JPanel();
        topTextOfViewPanel = new JLabel();
        topTextOfViewPanel.setForeground(Color.decode("#230030"));
        topTextOfViewPanel.setFont(new Font("Sans Serif", 1, 40));
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));
        topTextOfViewPanel.setText("Welcome " + admin.getAdminName() + "!");

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLoweredSoftBevelBorder(), "Functionalities: "));
        infoPanel.setLayout(new BoxLayout(infoPanel, 1));
        infoPanel.setBackground(Color.white);

        JPanel infoTitlePanel = new JPanel();
        infoTitlePanel.setBackground(Color.white);
        JLabel infoTitleText = new JLabel("Manage The Bodies: ");
        infoTitleText.setFont(new Font("sansserif", 1, 32));
        infoTitlePanel.add(infoTitleText, BorderLayout.CENTER);

        JButton manageStudentButton = new JButton("Manage Students");
        JButton manageTeacherButton = new JButton("Manage Teachers");
        JButton manageCourseButton = new JButton("Manage Courses");
        manageStudentButton.setBackground(Color.decode("#f0bc2e"));
        manageStudentButton.setForeground(Color.WHITE);
        manageTeacherButton.setBackground(Color.decode("#f0bc2e"));
        manageTeacherButton.setForeground(Color.WHITE);
        manageCourseButton.setBackground(Color.decode("#f0bc2e"));
        manageCourseButton.setForeground(Color.WHITE);
        manageStudentButton.setFont(new Font("sansserif", 1, 16));
        manageTeacherButton.setFont(new Font("sansserif", 1, 16));
        manageCourseButton.setFont(new Font("sansserif", 1, 16));

        JPanel panelForButton1 = new JPanel();
        JPanel panelForButton2 = new JPanel();
        JPanel panelForButton3 = new JPanel();
        panelForButton1.setBackground(Color.white);
        panelForButton1.add(manageStudentButton);
        panelForButton2.setBackground(Color.white);
        panelForButton2.add(manageTeacherButton);
        panelForButton3.setBackground(Color.white);
        panelForButton3.add(manageCourseButton);

        JLabel studentLabel = new JLabel("                View and manage Students");
        JLabel teacherLabel = new JLabel("                Manage The Instructors");
        JLabel courseLabel = new JLabel("                View / Add and manage courses of this university");
        studentLabel.setFont(new Font("sansserif", 1, 16));
        teacherLabel.setFont(new Font("sansserif", 1, 16));
        courseLabel.setFont(new Font("sansserif", 1, 16));

        JPanel infoBodyPanel = new JPanel(new GridLayout(6, 2, -16, 32));
        infoBodyPanel.setBackground(Color.white);

        infoBodyPanel.add(studentLabel);
        infoBodyPanel.add(panelForButton1);
        infoBodyPanel.add(teacherLabel);
        infoBodyPanel.add(panelForButton2);
        infoBodyPanel.add(courseLabel);
        infoBodyPanel.add(panelForButton3);
        infoBodyPanel.add(new JLabel(""));
        infoBodyPanel.add(new JLabel(""));
        infoPanel.add(infoTitlePanel);
        infoPanel.add(infoBodyPanel);
        detailsPanel.add(infoPanel);
        topViewPanel.add(topTextOfViewPanel, BorderLayout.CENTER);
        optionsRightAdmin.add(topViewPanel);
        optionsRightAdmin.add(detailsPanel);
        add(optionsLeftAdmin);
        add(optionsRightAdmin);

        manageStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageStudentFrame();
            }
        });

        manageTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageTeacherFrame();
            }
        });

        manageCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manageCourseFrame();
            }
        });

        
    }

    private void manageStudentFrame() {
        // String userType = "Student";
        int frameHeight = 750;
        int frameWidth = 1300;
        String[] columnNames = { "Name", "UserName", "Level", "Course", "Performance", "Address", "Phone No.",
                "Password" };
        Object[][] rowData = {};
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        table.setModel(model);

        JFrame frame = new JFrame("Studet Management");
        frame.setSize(frameWidth, frameHeight);
        frame.setLocation(frameWidth / 2 - frameWidth / 3 - 100, frameHeight / 2 - frameHeight / 3 - 100);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        refreshStudentTable(model);

        JTextField nameTextField = new JTextField();
        JTextField usernameTextField = new JTextField();
        JTextField levelTextField = new JTextField();
        JTextField courseTextField = new JTextField();
        JTextField failStatusTextField = new JTextField();
        JTextField addressTextField = new JTextField();
        JTextField phoneNoTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JCheckBox userTypeCheckBox = new JCheckBox("Student");
        JPanel clearButtonPanel = new JPanel();
        clearButtonPanel.setLayout(new BoxLayout(clearButtonPanel, 1));
        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(Color.decode("#f0bc2e"));
        clearButton.setForeground(Color.white);
        clearButton.setFont(new Font("SansSerif", 1, 12));
        clearButtonPanel.add(clearButton);
        userTypeCheckBox.setSelected(true);
        userTypeCheckBox.setEnabled(false);
        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Edit: "));
        formPanel.setLayout(new GridLayout(9, 2, 0, 8));
        formPanel.add(new JLabel("        Full Name"));
        formPanel.add(nameTextField);
        formPanel.add(new JLabel("        Username"));
        formPanel.add(usernameTextField);
        formPanel.add(new JLabel("        Level"));
        formPanel.add(levelTextField);
        formPanel.add(new JLabel("        Course"));
        formPanel.add(courseTextField);
        formPanel.add(new JLabel("        Performance"));
        formPanel.add(failStatusTextField);
        formPanel.add(new JLabel("        Address"));
        formPanel.add(addressTextField);
        formPanel.add(new JLabel("        Phone No."));
        formPanel.add(phoneNoTextField);
        formPanel.add(new JLabel("        Password"));
        formPanel.add(passwordField);
        formPanel.add(userTypeCheckBox);
        formPanel.add(clearButtonPanel);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTextField.setText("");
                usernameTextField.setText("");
                levelTextField.setText("");
                courseTextField.setText("");
                failStatusTextField.setText("");
                addressTextField.setText("");
                phoneNoTextField.setText("");
                passwordField.setText("");
                usernameTextField.setEditable(true);
                passwordField.setEditable(true);
            }
        });

        JButton saveButton = new JButton("Save Entries");
        JButton deleteButton = new JButton("Delete Entries");
        JButton updateButton = new JButton("Update Entries");
        JButton searchButton = new JButton("Search Records");
        saveButton.setBackground(Color.decode("#2f1938"));
        saveButton.setForeground(Color.white);
        saveButton.setFont(new Font("sansserif", 1, 16));
        deleteButton.setBackground(Color.decode("#2f1938"));
        deleteButton.setForeground(Color.white);
        deleteButton.setFont(new Font("sansserif", 1, 16));
        updateButton.setBackground(Color.decode("#2f1938"));
        updateButton.setForeground(Color.white);
        updateButton.setFont(new Font("sansserif", 1, 16));
        searchButton.setBackground(Color.decode("#2f1938"));
        searchButton.setForeground(Color.white);
        searchButton.setFont(new Font("sansserif", 1, 16));

        JPanel operationsPanel = new JPanel();
        operationsPanel.setBorder(BorderFactory.createTitledBorder("Operations: "));
        operationsPanel.setLayout(new GridLayout(2, 2));
        operationsPanel.add(saveButton);
        operationsPanel.add(deleteButton);
        operationsPanel.add(updateButton);
        operationsPanel.add(searchButton);

        table.setDefaultEditor(Object.class, null);
        table.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                try {
                    int rownumber = table.getSelectedRow();
                    nameTextField.setText((String) model.getValueAt(rownumber, 0));
                    usernameTextField.setText((String) model.getValueAt(rownumber, 1));
                    levelTextField.setText((String) model.getValueAt(rownumber, 2));
                    courseTextField.setText((String) model.getValueAt(rownumber, 3));
                    failStatusTextField.setText((String) model.getValueAt(rownumber, 4));
                    addressTextField.setText((String) model.getValueAt(rownumber, 5));
                    phoneNoTextField.setText((String) model.getValueAt(rownumber, 6));
                    passwordField.setText((String) model.getValueAt(rownumber, 7));
                    usernameTextField.setEditable(false);
                    passwordField.setEditable(false);
                    db.setRowSelected((String) model.getValueAt(rownumber, 1));
                } catch (NullPointerException throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = nameTextField.getText();
                String userId = usernameTextField.getText();
                String level = levelTextField.getText();
                String courseEnrolledIn = courseTextField.getText();
                String failStatus = failStatusTextField.getText();
                String address = addressTextField.getText();
                String phoneNo = phoneNoTextField.getText();
                String userPassword = String.valueOf(passwordField.getPassword());
                String userType = userTypeCheckBox.getText();
                if (studentName.isEmpty() || userId.isEmpty() || level.isEmpty() || courseEnrolledIn.isEmpty()
                        || failStatus.isEmpty() || address.isEmpty() || address.isEmpty() || phoneNo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields before updating!");
                } else {
                    db.saveUserDetails(level, studentName, courseEnrolledIn, failStatus, userId, userType, userPassword,
                            address, phoneNo);
                    clearButton.doClick();
                    refreshStudentTable(model);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentName = nameTextField.getText();
                String userId = usernameTextField.getText();
                String level = levelTextField.getText();
                String courseEnrolledIn = courseTextField.getText();
                String failStatus = failStatusTextField.getText();
                String address = addressTextField.getText();
                String phoneNo = phoneNoTextField.getText();
                String userPassword = String.valueOf(passwordField.getPassword());
                String userType = userTypeCheckBox.getText();
                if (studentName.isEmpty() || userId.isEmpty() || level.isEmpty() || courseEnrolledIn.isEmpty()
                        || failStatus.isEmpty() || address.isEmpty() || address.isEmpty() || phoneNo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields before updating!");
                } else {
                    db.updateUserDetails(level, studentName, courseEnrolledIn, failStatus, userId, userType,
                            userPassword, address, phoneNo);
                    clearButton.doClick();
                    refreshStudentTable(model);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all the details of the user ?", "Confirm Delete", 1) == JOptionPane.YES_OPTION) {
                    db.deleteUserDetails(userTypeCheckBox.getText());
                    clearButton.doClick();
                    refreshStudentTable(model);
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter Student Code or Student Name to search: ");

                ResultSet results = db.searchStudentData(input);
                try {
                    String message = "No Record(s) found!";
                    while(results.next()) {
                        message = "Name:                                "+    results.getString("studentName") + 
                                        "\nStudent Id:                                " +results.getString("userId")+
                                        "\nLevel:                                " +results.getString("level")+
                                        "\nCourse:                             " +results.getString("courseEnrolledIn")+
                                        "\nPerformance:                                " +results.getString("failStatus")+
                                        "\nAddress:                                " +results.getString("address")+
                                        "\nPhone No.:                                " +results.getString("phoneNo");
                    }
                    JOptionPane.showMessageDialog(null, message, "Record: ", 1);
                }
                catch(SQLException throwables){
                    throwables.printStackTrace();
                }
            }
        });




        JScrollPane pane = new JScrollPane(table);
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createTitledBorder("View Details"));
        leftPanel.setLayout(new GridLayout(1, 1));
        leftPanel.add(pane);


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.add(formPanel);
        rightPanel.add(operationsPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, 0));
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
            
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void refreshStudentTable(DefaultTableModel model){
        ResultSet resultSet = db.getStudents();
        int rowCount = 0;
        try {
            model.setRowCount(rowCount);
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("studentName"),
                        resultSet.getString("userId"),
                        resultSet.getString("level"),
                        resultSet.getString("courseEnrolledIn"),
                        resultSet.getString("failStatus"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNo"),
                        resultSet.getString("userPassword")
                    });
                }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }

    }

    



    private void manageTeacherFrame(){

        int frameHeight = 750;
        int frameWidth = 1300;
        String[] columnNames = { "Teacher Name", "Username", "Module1", "Module 2", "Module 3", "Module 4", "Address", "Phone No.", "Password" };
        Object[][] rowData = {};
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        table.setModel(model);
        refreshTeacherTable(model);

        JFrame frame = new JFrame("Teacger Management");
        frame.setSize(frameWidth, frameHeight);
        frame.setLocation(frameWidth/2 - frameWidth/3 - 100, frameHeight/2 - frameHeight/3 - 120);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        JTextField nameTextField = new JTextField();
        JTextField usernameTextField = new JTextField();
        JTextField module1TextField = new JTextField();
        JTextField module2TextField = new JTextField();
        JTextField module3TextField = new JTextField();
        JTextField module4TextField = new JTextField();
        JTextField addressTextField = new JTextField();
        JTextField phoneNoTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JCheckBox userTypeCheckBox = new JCheckBox("Teacher");
        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(Color.decode("#f0bc2e"));
        clearButton.setForeground(Color.white);
        clearButton.setFont(new Font("SansSerif", 1, 12));
        userTypeCheckBox.setSelected(true);
        userTypeCheckBox.setEnabled(false);
        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Edit: "));
        formPanel.setLayout(new GridLayout(10, 2, 0, 8));
        formPanel.add(new JLabel("        Full Name"));
        formPanel.add(nameTextField);
        formPanel.add(new JLabel("        Username"));
        formPanel.add(usernameTextField);
        formPanel.add(new JLabel("        Module1"));
        formPanel.add(module1TextField);
        formPanel.add(new JLabel("        Module2"));
        formPanel.add(module2TextField);
        formPanel.add(new JLabel("        Module3"));
        formPanel.add(module3TextField);
        formPanel.add(new JLabel("        Module4"));
        formPanel.add(module4TextField);
        formPanel.add(new JLabel("        Address"));
        formPanel.add(addressTextField);
        formPanel.add(new JLabel("        Phone No."));
        formPanel.add(phoneNoTextField);
        formPanel.add(new JLabel("        Password"));
        formPanel.add(passwordField);
        formPanel.add(userTypeCheckBox);
        formPanel.add(clearButton);

        module1TextField.setEnabled(false);
        module2TextField.setEnabled(false);
        module3TextField.setEnabled(false);
        module4TextField.setEnabled(false);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTextField.setText("");
                usernameTextField.setText("");
                module1TextField.setText("");
                module2TextField.setText("");
                module3TextField.setText("");
                module4TextField.setText("");
                addressTextField.setText("");
                phoneNoTextField.setText("");
                passwordField.setText("");
                usernameTextField.setEditable(true);
                passwordField.setEditable(true);
            }
        });




        JButton saveButton = new JButton("Save Entries");
        JButton deleteButton = new JButton("Delete Entries");
        JButton updateButton = new JButton("Update Entries");
        JButton searchButton = new JButton("Search Records");
        saveButton.setBackground(Color.decode("#2d1038"));
        saveButton.setForeground(Color.white);
        saveButton.setFont(new Font("sansserif", 1, 16));
        deleteButton.setBackground(Color.decode("#2d1038"));
        deleteButton.setForeground(Color.white);
        deleteButton.setFont(new Font("sansserif", 1, 16));
        updateButton.setBackground(Color.decode("#2d1038"));
        updateButton.setForeground(Color.white);
        updateButton.setFont(new Font("sansserif", 1, 16));
        searchButton.setBackground(Color.decode("#2d1038"));
        searchButton.setForeground(Color.white);
        searchButton.setFont(new Font("sansserif", 1, 16));

        JPanel operationsPanel = new JPanel();
        operationsPanel.setBorder(BorderFactory.createTitledBorder("Operations: "));
        operationsPanel.setLayout(new GridLayout(2, 2, 0, 0));
        operationsPanel.add(saveButton);
        operationsPanel.add(deleteButton);
        operationsPanel.add(updateButton);
        operationsPanel.add(searchButton);





        table.setDefaultEditor(Object.class, null);
        table.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                try {
                    int rownumber = table.getSelectedRow();
                    nameTextField.setText((String) model.getValueAt(rownumber, 0));
                    usernameTextField.setText((String) model.getValueAt(rownumber, 1));
                    module1TextField.setText((String) model.getValueAt(rownumber, 2));
                    module2TextField.setText((String) model.getValueAt(rownumber, 3));
                    module3TextField.setText((String) model.getValueAt(rownumber, 4));
                    module4TextField.setText((String) model.getValueAt(rownumber, 5));
                    addressTextField.setText((String) model.getValueAt(rownumber, 6));
                    phoneNoTextField.setText((String) model.getValueAt(rownumber, 7));
                    passwordField.setText((String) model.getValueAt(rownumber, 8));
                    usernameTextField.setEditable(false);
                    passwordField.setEditable(false);
                    db.setRowSelected((String) model.getValueAt(rownumber, 1));
                } catch (NullPointerException throwable) {
                    throwable.printStackTrace();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}

        });



        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String teacherName = nameTextField.getText();
                String userId = usernameTextField.getText();
                usernameTextField.setEnabled(true);
                String address = addressTextField.getText();
                String phoneNo = phoneNoTextField.getText();
                String userPassword = String.valueOf(passwordField.getPassword());
                String userType = userTypeCheckBox.getText();
                if (teacherName.isEmpty() || userId.isEmpty() || address.isEmpty() || address.isEmpty() || phoneNo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields before updating!");
                } else {
                    db.saveUserDetails(teacherName, userId, userType, userPassword, address, phoneNo);
                    clearButton.doClick();
                    refreshTeacherTable(model);
                }
            }
        });


        updateButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String teacherName = nameTextField.getText();
                String userId = usernameTextField.getText();
                String address = addressTextField.getText();
                String phoneNo = phoneNoTextField.getText();
                String userPassword = String.valueOf(passwordField.getPassword());
                String userType = userTypeCheckBox.getText();
                if (teacherName.isEmpty() || userId.isEmpty() || address.isEmpty() || address.isEmpty() || phoneNo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields before updating!");
                } else {
                    db.updateUserDetails(teacherName, userId, userType, userPassword, address, phoneNo);
                    clearButton.doClick();
                    refreshTeacherTable(model);
                }
            }
        });


        deleteButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                db.deleteUserDetails(userTypeCheckBox.getText());
                clearButton.doClick();
                refreshTeacherTable(model);
            }
        });

        searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                String input = JOptionPane.showInputDialog(null, "Enter teacher id or teacher name to search: ");

                ResultSet results = db.searchTeacherData(input);
                try {
                    String message = "No Record(s) found!";
                    while(results.next()) {
                        message = "Name:                                "+    results.getString("teacherName") + 
                                        "\nTeacher Id:                                " +results.getString("userId")+
                                        "\nModule 1:                                " +results.getString("module1")+
                                        "\nModule 2:                                " +results.getString("module2")+
                                        "\nModule 3:                                " +results.getString("module3")+
                                        "\nModule 4:                                " +results.getString("module4")+
                                        "\nAddress:                                " +results.getString("address")+
                                        "\nPhone No.:                                " +results.getString("phoneNo");
                    }
                    JOptionPane.showMessageDialog(null, message, "Record: ", 1);
                }
                catch(SQLException throwables){
                    throwables.printStackTrace();
                }
                
            }
        });


        JScrollPane pane = new JScrollPane(table);
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createTitledBorder("View Details"));
        leftPanel.setLayout(new GridLayout(1, 1));
        leftPanel.add(pane);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.add(formPanel);
        rightPanel.add(operationsPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, 0));
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
            
        frame.add(mainPanel);
        frame.setVisible(true);
        
    }

    private void refreshTeacherTable(DefaultTableModel model){
        ResultSet resultSet = db.getTeacher();
        try {
            model.setRowCount(0);
            while (resultSet.next()) {
                String teacherName = resultSet.getString("teacherName");
                String teacherId = resultSet.getString("teacherId");
                String address = resultSet.getString("address");
                String phoneNo = resultSet.getString("phoneNo");
                String userPassword =  resultSet.getString("userPassword");
                ResultSet teachingModules = db.getTeacherModules(teacherName);
                String[] modules = new String[4];
                int i = 0;
                while(teachingModules.next()){
                    modules[i] = teachingModules.getString("moduleName");
                    i++;
                }
                String module1 = modules[0];
                String module2 = modules[1];
                String module3 = modules[2];
                String module4 = modules[3];
                model.addRow(new Object[]{teacherName, teacherId, module1, module2, module3, module4, address, phoneNo, userPassword});
            }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }

    }














    
    private void manageCourseFrame(){
        
        new coursesPanel();


    }
    

















class coursesPanel {

    protected JTextField courseNameTextField;
    protected JTextField levelTextField;
    protected JTextField courseTagTextField;
    protected JTextField streamTextField;
    protected JRadioButton cancelledStatusButton;
    protected ArrayList<JTextField> modsNames = new ArrayList<>(24);
    protected ArrayList<JTextField> modsCodes = new ArrayList<>(24);
    protected ArrayList<JTextField> modsSems = new ArrayList<>(24);
    protected ArrayList<JCheckBox> modsCoreStats = new ArrayList<JCheckBox>(24);
    protected ArrayList<JComboBox<String>> modsInstructors = new ArrayList<JComboBox<String>>(24);
    private ManagementDatabase db = new ManagementDatabase();
    private CourseFile courserecords = new CourseFile();
    

    public coursesPanel() throws IndexOutOfBoundsException{

        int frameHeight = 820;
        int frameWidth = 1300;
        JFrame frame = new JFrame("Add Courses:");
        frame.setSize(frameWidth, frameHeight);
        frame.setLocation(frameWidth/2 - frameWidth/3 - 120, 4);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.decode("#2f1938"));
        
        


        
        JMenu viewAllCourses = new JMenu("Show all the courses    |");
        JMenu viewActiveCourses = new JMenu("View Active Courses    |");
        JMenu viewCancelledCourses = new JMenu("View Canceled Courses");



        ArrayList<Course> list = courserecords.getCourses();
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getCancelStatus()){
                viewCancelledCourses.add(new JMenuItem(list.get(i).getCourseTag() + "                                    "));
            } else {
                viewActiveCourses.add(new JMenuItem(list.get(i).getCourseTag() + "                                    "));
            }
            viewAllCourses.add(new JMenuItem(list.get(i).getCourseTag() + "                                    "));
        }



        
        viewAllCourses.setForeground(Color.white);
        viewCancelledCourses.setForeground(Color.white);        
        viewActiveCourses.setForeground(Color.white);



        menuBar.add(viewAllCourses);
        menuBar.add(viewActiveCourses);
        menuBar.add(viewCancelledCourses);
        frame.setJMenuBar(menuBar);
        
        



        courseNameTextField = new JTextField();
        courseTagTextField = new JTextField();
        levelTextField = new JTextField();
        streamTextField = new JTextField();
        cancelledStatusButton = new JRadioButton("Cancelled");


        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createTitledBorder("Enter the course details: "));
        topPanel.setLayout(new GridLayout(1, 9));
        topPanel.add(new JLabel("    Course Name"));
        topPanel.add(courseNameTextField);
        topPanel.add(new JLabel("                Course Tag"));
        topPanel.add(courseTagTextField);
        topPanel.add(new JLabel("                Level"));
        topPanel.add(levelTextField);
        topPanel.add(new JLabel("                Stream"));
        topPanel.add(streamTextField);
        topPanel.add(new JLabel("                "));
        topPanel.add(cancelledStatusButton);


        int i=0;
        ResultSet nameResult = db.getTeacher();
        try{
            while(nameResult.next()) {
                i++;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        String[] names = new String[i];
        nameResult = db.getTeacher();
        try{
            i=0;
            while(nameResult.next()) {
                names[i] = nameResult.getString("teacherName");
                i++;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        JComboBox<String> instructors1 = new JComboBox<>(names);
        JComboBox<String> instructors2 = new JComboBox<>(names);
        JComboBox<String> instructors3 = new JComboBox<>(names);
        JComboBox<String> instructors4 = new JComboBox<>(names);
        JComboBox<String> instructors5 = new JComboBox<>(names);
        JComboBox<String> instructors6 = new JComboBox<>(names);
        JComboBox<String> instructors7 = new JComboBox<>(names);
        JComboBox<String> instructors8 = new JComboBox<>(names);
        JComboBox<String> instructors9 = new JComboBox<>(names);
        JComboBox<String> instructors10 = new JComboBox<>(names);
        JComboBox<String> instructors11 = new JComboBox<>(names);
        JComboBox<String> instructors12 = new JComboBox<>(names);
        JComboBox<String> instructors13 = new JComboBox<>(names);
        JComboBox<String> instructors14 = new JComboBox<>(names);
        JComboBox<String> instructors15 = new JComboBox<>(names);
        JComboBox<String> instructors16 = new JComboBox<>(names);
        JComboBox<String> instructors17 = new JComboBox<>(names);
        JComboBox<String> instructors18 = new JComboBox<>(names);
        JComboBox<String> instructors19 = new JComboBox<>(names);
        JComboBox<String> instructors20 = new JComboBox<>(names);
        JComboBox<String> instructors21 = new JComboBox<>(names);
        JComboBox<String> instructors22 = new JComboBox<>(names);
        JComboBox<String> instructors23 = new JComboBox<>(names);
        JComboBox<String> instructors24 = new JComboBox<>(names);
        
        JTextField moduleName1 = new JTextField();
        JTextField moduleCode1 = new JTextField();
        JTextField semester1 = new JTextField();
        JCheckBox coreStatus1 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel1 = new JPanel();
        addMoulesPanel1.setLayout(new GridLayout(1, 10));
        addMoulesPanel1.add(new JLabel("        Module Name"));
        addMoulesPanel1.add(moduleName1);
        addMoulesPanel1.add(new JLabel("        Module Code"));
        addMoulesPanel1.add(moduleCode1);
        addMoulesPanel1.add(new JLabel("        Instructor"));
        addMoulesPanel1.add(instructors1);
        addMoulesPanel1.add(new JLabel("        Semester"));
        addMoulesPanel1.add(semester1);
        addMoulesPanel1.add(coreStatus1);

        JTextField moduleName2 = new JTextField();
        JTextField moduleCode2 = new JTextField();
        JTextField semester2 = new JTextField();
        JCheckBox coreStatus2 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel2 = new JPanel();
        addMoulesPanel2.setLayout(new GridLayout(1, 10));
        addMoulesPanel2.add(new JLabel("        Module Name"));
        addMoulesPanel2.add(moduleName2);
        addMoulesPanel2.add(new JLabel("        Module Code"));
        addMoulesPanel2.add(moduleCode2);
        addMoulesPanel2.add(new JLabel("        Instructor"));
        addMoulesPanel2.add(instructors2);
        addMoulesPanel2.add(new JLabel("        Semester"));
        addMoulesPanel2.add(semester2);
        addMoulesPanel2.add(coreStatus2);

        JTextField moduleName3 = new JTextField();
        JTextField moduleCode3 = new JTextField();
        JTextField semester3 = new JTextField();
        JCheckBox coreStatus3 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel3 = new JPanel();
        addMoulesPanel3.setLayout(new GridLayout(1, 10));
        addMoulesPanel3.add(new JLabel("        Module Name"));
        addMoulesPanel3.add(moduleName3);
        addMoulesPanel3.add(new JLabel("        Module Code"));
        addMoulesPanel3.add(moduleCode3);
        addMoulesPanel3.add(new JLabel("        Instructor"));
        addMoulesPanel3.add(instructors3);
        addMoulesPanel3.add(new JLabel("        Semester"));
        addMoulesPanel3.add(semester3);
        addMoulesPanel3.add(coreStatus3);

        JTextField moduleName4 = new JTextField();
        JTextField moduleCode4 = new JTextField();
        JTextField semester4 = new JTextField();
        JCheckBox coreStatus4 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel4 = new JPanel();
        addMoulesPanel4.setLayout(new GridLayout(1, 10));
        addMoulesPanel4.add(new JLabel("        Module Name"));
        addMoulesPanel4.add(moduleName4);
        addMoulesPanel4.add(new JLabel("        Module Code"));
        addMoulesPanel4.add(moduleCode4);
        addMoulesPanel4.add(new JLabel("        Instructor"));
        addMoulesPanel4.add(instructors4);
        addMoulesPanel4.add(new JLabel("        Semester"));
        addMoulesPanel4.add(semester4);
        addMoulesPanel4.add(coreStatus4);

        JTextField moduleName5 = new JTextField();
        JTextField moduleCode5 = new JTextField();
        JTextField semester5 = new JTextField();
        JCheckBox coreStatus5 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel5 = new JPanel();
        addMoulesPanel5.setLayout(new GridLayout(1, 10));
        addMoulesPanel5.add(new JLabel("        Module Name"));
        addMoulesPanel5.add(moduleName5);
        addMoulesPanel5.add(new JLabel("        Module Code"));
        addMoulesPanel5.add(moduleCode5);
        addMoulesPanel5.add(new JLabel("        Instructor"));
        addMoulesPanel5.add(instructors5);
        addMoulesPanel5.add(new JLabel("        Semester"));
        addMoulesPanel5.add(semester5);
        addMoulesPanel5.add(coreStatus5);

        JTextField moduleName6 = new JTextField();
        JTextField moduleCode6 = new JTextField();
        JTextField semester6 = new JTextField();
        JCheckBox coreStatus6 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel6 = new JPanel();
        addMoulesPanel6.setLayout(new GridLayout(1, 10));
        addMoulesPanel6.add(new JLabel("        Module Name"));
        addMoulesPanel6.add(moduleName6);
        addMoulesPanel6.add(new JLabel("        Module Code"));
        addMoulesPanel6.add(moduleCode6);
        addMoulesPanel6.add(new JLabel("        Instructor"));
        addMoulesPanel6.add(instructors6);
        addMoulesPanel6.add(new JLabel("        Semester"));
        addMoulesPanel6.add(semester6);
        addMoulesPanel6.add(coreStatus6);

        JTextField moduleName7 = new JTextField();
        JTextField moduleCode7 = new JTextField();
        JTextField semester7 = new JTextField();
        JCheckBox coreStatus7 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel7 = new JPanel();
        addMoulesPanel7.setLayout(new GridLayout(1, 10));
        addMoulesPanel7.add(new JLabel("        Module Name"));
        addMoulesPanel7.add(moduleName7);
        addMoulesPanel7.add(new JLabel("        Module Code"));
        addMoulesPanel7.add(moduleCode7);
        addMoulesPanel7.add(new JLabel("        Instructor"));
        addMoulesPanel7.add(instructors7);
        addMoulesPanel7.add(new JLabel("        Semester"));
        addMoulesPanel7.add(semester7);
        addMoulesPanel7.add(coreStatus7);

        JTextField moduleName8 = new JTextField();
        JTextField moduleCode8 = new JTextField();
        JTextField semester8 = new JTextField();
        JCheckBox coreStatus8 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel8 = new JPanel();
        addMoulesPanel8.setLayout(new GridLayout(1, 10));
        addMoulesPanel8.add(new JLabel("        Module Name"));
        addMoulesPanel8.add(moduleName8);
        addMoulesPanel8.add(new JLabel("        Module Code"));
        addMoulesPanel8.add(moduleCode8);
        addMoulesPanel8.add(new JLabel("        Instructor"));
        addMoulesPanel8.add(instructors8);
        addMoulesPanel8.add(new JLabel("        Semester"));
        addMoulesPanel8.add(semester8);
        addMoulesPanel8.add(coreStatus8);

        JTextField moduleName9 = new JTextField();
        JTextField moduleCode9 = new JTextField();
        JTextField semester9 = new JTextField();
        JCheckBox coreStatus9 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel9 = new JPanel();
        addMoulesPanel9.setLayout(new GridLayout(1, 10));
        addMoulesPanel9.add(new JLabel("        Module Name"));
        addMoulesPanel9.add(moduleName9);
        addMoulesPanel9.add(new JLabel("        Module Code"));
        addMoulesPanel9.add(moduleCode9);
        addMoulesPanel9.add(new JLabel("        Instructor"));
        addMoulesPanel9.add(instructors9);
        addMoulesPanel9.add(new JLabel("        Semester"));
        addMoulesPanel9.add(semester9);
        addMoulesPanel9.add(coreStatus9);

        JTextField moduleName10 = new JTextField();
        JTextField moduleCode10 = new JTextField();
        JTextField semester10 = new JTextField();
        JCheckBox coreStatus10 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel10 = new JPanel();
        addMoulesPanel10.setLayout(new GridLayout(1, 10));
        addMoulesPanel10.add(new JLabel("        Module Name"));
        addMoulesPanel10.add(moduleName10);
        addMoulesPanel10.add(new JLabel("        Module Code"));
        addMoulesPanel10.add(moduleCode10);
        addMoulesPanel10.add(new JLabel("        Instructor"));
        addMoulesPanel10.add(instructors10);
        addMoulesPanel10.add(new JLabel("        Semester"));
        addMoulesPanel10.add(semester10);
        addMoulesPanel10.add(coreStatus10);

        JTextField moduleName11 = new JTextField();
        JTextField moduleCode11 = new JTextField();
        JTextField semester11 = new JTextField();
        JCheckBox coreStatus11 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel11 = new JPanel();
        addMoulesPanel11.setLayout(new GridLayout(1, 10));
        addMoulesPanel11.add(new JLabel("        Module Name"));
        addMoulesPanel11.add(moduleName11);
        addMoulesPanel11.add(new JLabel("        Module Code"));
        addMoulesPanel11.add(moduleCode11);
        addMoulesPanel11.add(new JLabel("        Instructor"));
        addMoulesPanel11.add(instructors11);
        addMoulesPanel11.add(new JLabel("        Semester"));
        addMoulesPanel11.add(semester11);
        addMoulesPanel11.add(coreStatus11);

        JTextField moduleName12 = new JTextField();
        JTextField moduleCode12 = new JTextField();
        JTextField semester12 = new JTextField();
        JCheckBox coreStatus12 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel12 = new JPanel();
        addMoulesPanel12.setLayout(new GridLayout(1, 10));
        addMoulesPanel12.add(new JLabel("        Module Name"));
        addMoulesPanel12.add(moduleName12);
        addMoulesPanel12.add(new JLabel("        Module Code"));
        addMoulesPanel12.add(moduleCode12);
        addMoulesPanel12.add(new JLabel("        Instructor"));
        addMoulesPanel12.add(instructors12);
        addMoulesPanel12.add(new JLabel("        Semester"));
        addMoulesPanel12.add(semester12);
        addMoulesPanel12.add(coreStatus12);

        JTextField moduleName13 = new JTextField();
        JTextField moduleCode13 = new JTextField();
        JTextField semester13 = new JTextField();
        JCheckBox coreStatus13 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel13 = new JPanel();
        addMoulesPanel13.setLayout(new GridLayout(1, 10));
        addMoulesPanel13.add(new JLabel("        Module Name"));
        addMoulesPanel13.add(moduleName13);
        addMoulesPanel13.add(new JLabel("        Module Code"));
        addMoulesPanel13.add(moduleCode13);
        addMoulesPanel13.add(new JLabel("        Instructor"));
        addMoulesPanel13.add(instructors13);
        addMoulesPanel13.add(new JLabel("        Semester"));
        addMoulesPanel13.add(semester13);
        addMoulesPanel13.add(coreStatus13);

        JTextField moduleName14 = new JTextField();
        JTextField moduleCode14 = new JTextField();
        JTextField semester14 = new JTextField();
        JCheckBox coreStatus14 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel14 = new JPanel();
        addMoulesPanel14.setLayout(new GridLayout(1, 10));
        addMoulesPanel14.add(new JLabel("        Module Name"));
        addMoulesPanel14.add(moduleName14);
        addMoulesPanel14.add(new JLabel("        Module Code"));
        addMoulesPanel14.add(moduleCode14);
        addMoulesPanel14.add(new JLabel("        Instructor"));
        addMoulesPanel14.add(instructors14);
        addMoulesPanel14.add(new JLabel("        Semester"));
        addMoulesPanel14.add(semester14);
        addMoulesPanel14.add(coreStatus14);

        JTextField moduleName15 = new JTextField();
        JTextField moduleCode15 = new JTextField();
        JTextField semester15 = new JTextField();
        JCheckBox coreStatus15 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel15 = new JPanel();
        addMoulesPanel15.setLayout(new GridLayout(1, 10));
        addMoulesPanel15.add(new JLabel("        Module Name"));
        addMoulesPanel15.add(moduleName15);
        addMoulesPanel15.add(new JLabel("        Module Code"));
        addMoulesPanel15.add(moduleCode15);
        addMoulesPanel15.add(new JLabel("        Instructor"));
        addMoulesPanel15.add(instructors15);
        addMoulesPanel15.add(new JLabel("        Semester"));
        addMoulesPanel15.add(semester15);
        addMoulesPanel15.add(coreStatus15);

        JTextField moduleName16 = new JTextField();
        JTextField moduleCode16 = new JTextField();
        JTextField semester16 = new JTextField();
        JCheckBox coreStatus16 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel16 = new JPanel();
        addMoulesPanel16.setLayout(new GridLayout(1, 10));
        addMoulesPanel16.add(new JLabel("        Module Name"));
        addMoulesPanel16.add(moduleName16);
        addMoulesPanel16.add(new JLabel("        Module Code"));
        addMoulesPanel16.add(moduleCode16);
        addMoulesPanel16.add(new JLabel("        Instructor"));
        addMoulesPanel16.add(instructors16);
        addMoulesPanel16.add(new JLabel("        Semester"));
        addMoulesPanel16.add(semester16);
        addMoulesPanel16.add(coreStatus16);

        JTextField moduleName17 = new JTextField();
        JTextField moduleCode17 = new JTextField();
        JTextField semester17 = new JTextField();
        JCheckBox coreStatus17 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel17 = new JPanel();
        addMoulesPanel17.setLayout(new GridLayout(1, 10));
        addMoulesPanel17.add(new JLabel("        Module Name"));
        addMoulesPanel17.add(moduleName17);
        addMoulesPanel17.add(new JLabel("        Module Code"));
        addMoulesPanel17.add(moduleCode17);
        addMoulesPanel17.add(new JLabel("        Instructor"));
        addMoulesPanel17.add(instructors17);
        addMoulesPanel17.add(new JLabel("        Semester"));
        addMoulesPanel17.add(semester17);
        addMoulesPanel17.add(coreStatus17);

        JTextField moduleName18 = new JTextField();
        JTextField moduleCode18 = new JTextField();
        JTextField semester18 = new JTextField();
        JCheckBox coreStatus18 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel18 = new JPanel();
        addMoulesPanel18.setLayout(new GridLayout(1, 10));
        addMoulesPanel18.add(new JLabel("        Module Name"));
        addMoulesPanel18.add(moduleName18);
        addMoulesPanel18.add(new JLabel("        Module Code"));
        addMoulesPanel18.add(moduleCode18);
        addMoulesPanel18.add(new JLabel("        Instructor"));
        addMoulesPanel18.add(instructors18);
        addMoulesPanel18.add(new JLabel("        Semester"));
        addMoulesPanel18.add(semester18);
        addMoulesPanel18.add(coreStatus18);

        JTextField moduleName19 = new JTextField();
        JTextField moduleCode19 = new JTextField();
        JTextField semester19 = new JTextField();
        JCheckBox coreStatus19 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel19 = new JPanel();
        addMoulesPanel19.setLayout(new GridLayout(1, 10));
        addMoulesPanel19.add(new JLabel("        Module Name"));
        addMoulesPanel19.add(moduleName19);
        addMoulesPanel19.add(new JLabel("        Module Code"));
        addMoulesPanel19.add(moduleCode19);
        addMoulesPanel19.add(new JLabel("        Instructor"));
        addMoulesPanel19.add(instructors19);
        addMoulesPanel19.add(new JLabel("        Semester"));
        addMoulesPanel19.add(semester19);
        addMoulesPanel19.add(coreStatus19);

        JTextField moduleName20 = new JTextField();
        JTextField moduleCode20 = new JTextField();
        JTextField semester20 = new JTextField();
        JCheckBox coreStatus20 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel20 = new JPanel();
        addMoulesPanel20.setLayout(new GridLayout(1, 10));
        addMoulesPanel20.add(new JLabel("        Module Name"));
        addMoulesPanel20.add(moduleName20);
        addMoulesPanel20.add(new JLabel("        Module Code"));
        addMoulesPanel20.add(moduleCode20);
        addMoulesPanel20.add(new JLabel("        Instructor"));
        addMoulesPanel20.add(instructors20);
        addMoulesPanel20.add(new JLabel("        Semester"));
        addMoulesPanel20.add(semester20);
        addMoulesPanel20.add(coreStatus20);

        JTextField moduleName21 = new JTextField();
        JTextField moduleCode21 = new JTextField();
        JTextField semester21 = new JTextField();
        JCheckBox coreStatus21 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel21 = new JPanel();
        addMoulesPanel21.setLayout(new GridLayout(1, 10));
        addMoulesPanel21.add(new JLabel("        Module Name"));
        addMoulesPanel21.add(moduleName21);
        addMoulesPanel21.add(new JLabel("        Module Code"));
        addMoulesPanel21.add(moduleCode21);
        addMoulesPanel21.add(new JLabel("        Instructor"));
        addMoulesPanel21.add(instructors21);
        addMoulesPanel21.add(new JLabel("        Semester"));
        addMoulesPanel21.add(semester21);
        addMoulesPanel21.add(coreStatus21);

        JTextField moduleName22 = new JTextField();
        JTextField moduleCode22 = new JTextField();
        JTextField semester22 = new JTextField();
        JCheckBox coreStatus22 = new JCheckBox("Core Status", true);
        JPanel addMoulesPanel22 = new JPanel();
        addMoulesPanel22.setLayout(new GridLayout(1, 10));
        addMoulesPanel22.add(new JLabel("        Module Name"));
        addMoulesPanel22.add(moduleName22);
        addMoulesPanel22.add(new JLabel("        Module Code"));
        addMoulesPanel22.add(moduleCode22);
        addMoulesPanel22.add(new JLabel("        Instructor"));
        addMoulesPanel22.add(instructors22);
        addMoulesPanel22.add(new JLabel("        Semester"));
        addMoulesPanel22.add(semester22);
        addMoulesPanel22.add(coreStatus22);

        JTextField moduleName23 = new JTextField();
        JTextField moduleCode23 = new JTextField();
        JTextField semester23 = new JTextField();
        JCheckBox coreStatus23 = new JCheckBox("Core Status");
        JPanel addMoulesPanel23 = new JPanel();
        addMoulesPanel23.setLayout(new GridLayout(1, 10));
        addMoulesPanel23.add(new JLabel("        Module Name"));
        addMoulesPanel23.add(moduleName23);
        addMoulesPanel23.add(new JLabel("        Module Code"));
        addMoulesPanel23.add(moduleCode23);
        addMoulesPanel23.add(new JLabel("        Instructor"));
        addMoulesPanel23.add(instructors23);
        addMoulesPanel23.add(new JLabel("        Semester"));
        addMoulesPanel23.add(semester23);
        addMoulesPanel23.add(coreStatus23);

        JTextField moduleName24 = new JTextField();
        JTextField moduleCode24 = new JTextField();
        JTextField semester24 = new JTextField();
        JCheckBox coreStatus24 = new JCheckBox("Core Status");
        JPanel addMoulesPanel24 = new JPanel();
        addMoulesPanel24.setLayout(new GridLayout(1, 10));
        addMoulesPanel24.add(new JLabel("        Module Name"));
        addMoulesPanel24.add(moduleName24);
        addMoulesPanel24.add(new JLabel("        Module Code"));
        addMoulesPanel24.add(moduleCode24);
        addMoulesPanel24.add(new JLabel("        Instructor"));
        addMoulesPanel24.add(instructors24);
        addMoulesPanel24.add(new JLabel("        Semester"));
        addMoulesPanel24.add(semester24);
        addMoulesPanel24.add(coreStatus24);

        

        modsNames.add(moduleName1);
        modsCodes.add(moduleCode1);
        modsSems.add(semester1);
        modsCoreStats.add(coreStatus1);
        modsInstructors.add(instructors1);
        
        modsNames.add(moduleName2);
        modsCodes.add(moduleCode2);
        modsSems.add(semester2);
        modsCoreStats.add(coreStatus2);
        modsInstructors.add(instructors2);
        
        modsNames.add(moduleName3);
        modsCodes.add(moduleCode3);
        modsSems.add(semester3);
        modsCoreStats.add(coreStatus3);
        modsInstructors.add(instructors3);
        
        modsNames.add(moduleName4);
        modsCodes.add(moduleCode4);
        modsSems.add(semester4);
        modsCoreStats.add(coreStatus4);
        modsInstructors.add(instructors4);
        
        modsNames.add(moduleName5);
        modsCodes.add(moduleCode5);
        modsSems.add(semester5);
        modsCoreStats.add(coreStatus5);
        modsInstructors.add(instructors5);
        
        modsNames.add(moduleName6);
        modsCodes.add(moduleCode6);
        modsSems.add(semester6);
        modsCoreStats.add(coreStatus6);
        modsInstructors.add(instructors6);
        
        modsNames.add(moduleName7);
        modsCodes.add(moduleCode7);
        modsSems.add(semester7);
        modsCoreStats.add(coreStatus7);
        modsInstructors.add(instructors7);
        
        modsNames.add(moduleName8);
        modsCodes.add(moduleCode8);
        modsSems.add(semester8);
        modsCoreStats.add(coreStatus8);
        modsInstructors.add(instructors8);
        
        modsNames.add(moduleName9);
        modsCodes.add(moduleCode9);
        modsSems.add(semester9);
        modsCoreStats.add(coreStatus9);
        modsInstructors.add(instructors9);
        
        modsNames.add(moduleName10);
        modsCodes.add(moduleCode10);
        modsSems.add(semester10);
        modsCoreStats.add(coreStatus10);
        modsInstructors.add(instructors10);
        
        modsNames.add(moduleName11);
        modsCodes.add(moduleCode11);
        modsSems.add(semester11);
        modsCoreStats.add(coreStatus11);
        modsInstructors.add(instructors11);
        
        modsNames.add(moduleName12);
        modsCodes.add(moduleCode12);
        modsSems.add(semester12);
        modsCoreStats.add(coreStatus12);
        modsInstructors.add(instructors12);
        
        modsNames.add(moduleName13);
        modsCodes.add(moduleCode13);
        modsSems.add(semester13);
        modsCoreStats.add(coreStatus13);
        modsInstructors.add(instructors13);
        
        modsNames.add(moduleName14);
        modsCodes.add(moduleCode14);
        modsSems.add(semester14);
        modsCoreStats.add(coreStatus14);
        modsInstructors.add(instructors14);
        
        modsNames.add(moduleName15);
        modsCodes.add(moduleCode15);
        modsSems.add(semester15);
        modsCoreStats.add(coreStatus15);
        modsInstructors.add(instructors15);
        
        modsNames.add(moduleName16);
        modsCodes.add(moduleCode16);
        modsSems.add(semester16);
        modsCoreStats.add(coreStatus16);
        modsInstructors.add(instructors16);
        
        modsNames.add(moduleName17);
        modsCodes.add(moduleCode17);
        modsSems.add(semester17);
        modsCoreStats.add(coreStatus17);
        modsInstructors.add(instructors17);
        
        modsNames.add(moduleName18);
        modsCodes.add(moduleCode18);
        modsSems.add(semester18);
        modsCoreStats.add(coreStatus18);
        modsInstructors.add(instructors18);
        
        modsNames.add(moduleName19);
        modsCodes.add(moduleCode19);
        modsSems.add(semester19);
        modsCoreStats.add(coreStatus19);
        modsInstructors.add(instructors19);
        
        modsNames.add(moduleName20);
        modsCodes.add(moduleCode20);
        modsSems.add(semester20);
        modsCoreStats.add(coreStatus20);
        modsInstructors.add(instructors20);
        
        modsNames.add(moduleName21);
        modsCodes.add(moduleCode21);
        modsSems.add(semester21);
        modsCoreStats.add(coreStatus21);
        modsInstructors.add(instructors21);
        
        modsNames.add(moduleName22);
        modsCodes.add(moduleCode22);
        modsSems.add(semester22);
        modsCoreStats.add(coreStatus22);
        modsInstructors.add(instructors22);
        
        modsNames.add(moduleName23);
        modsCodes.add(moduleCode23);
        modsSems.add(semester23);
        modsCoreStats.add(coreStatus23);
        modsInstructors.add(instructors23);
        
        modsNames.add(moduleName24);
        modsCodes.add(moduleCode24);
        modsSems.add(semester24);
        modsCoreStats.add(coreStatus24);
        modsInstructors.add(instructors24);


        
        

        JPanel bodyPanel = new JPanel();
        bodyPanel.setBorder(BorderFactory.createTitledBorder("Set the modules for the couse"));
        bodyPanel.setLayout(new GridLayout(24, 1, 0, 2));
        bodyPanel.add(addMoulesPanel1);
        bodyPanel.add(addMoulesPanel2);
        bodyPanel.add(addMoulesPanel3);
        bodyPanel.add(addMoulesPanel4);
        bodyPanel.add(addMoulesPanel5);
        bodyPanel.add(addMoulesPanel6);
        bodyPanel.add(addMoulesPanel7);
        bodyPanel.add(addMoulesPanel8);
        bodyPanel.add(addMoulesPanel9);
        bodyPanel.add(addMoulesPanel10);
        bodyPanel.add(addMoulesPanel11);
        bodyPanel.add(addMoulesPanel12);
        bodyPanel.add(addMoulesPanel13);
        bodyPanel.add(addMoulesPanel14);
        bodyPanel.add(addMoulesPanel15);
        bodyPanel.add(addMoulesPanel16);
        bodyPanel.add(addMoulesPanel17);
        bodyPanel.add(addMoulesPanel18);
        bodyPanel.add(addMoulesPanel19);
        bodyPanel.add(addMoulesPanel20);
        bodyPanel.add(addMoulesPanel21);
        bodyPanel.add(addMoulesPanel22);
        bodyPanel.add(addMoulesPanel23);
        bodyPanel.add(addMoulesPanel24);

        JScrollPane pane = new JScrollPane(bodyPanel);

    






        
        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.setBackground(Color.decode("#2f1938"));
        addCourseButton.setForeground(Color.white);
        addCourseButton.setFont(new Font("sansserif", 1, 16));
        JButton viewCoursesButton = new JButton("Click Here to View Courses!");
        viewCoursesButton.setBackground(Color.decode("#f0bc2e"));
        viewCoursesButton.setForeground(Color.white);
        viewCoursesButton.setFont(new Font("sansserif", 1, 16));
        JButton updateCourseButton = new JButton("Update Modules");
        updateCourseButton.setBackground(Color.decode("#2f1938"));
        updateCourseButton.setForeground(Color.white);
        updateCourseButton.setFont(new Font("sansserif", 1, 16));
        JButton cancelCourseButton = new JButton("Cancel Course");
        cancelCourseButton.setBackground(Color.decode("#2f1938"));
        cancelCourseButton.setForeground(Color.white);
        cancelCourseButton.setFont(new Font("sansserif", 1, 16));
        JButton deleteCourseButton = new JButton("Delete Course");
        deleteCourseButton.setBackground(Color.decode("#2f1938"));
        deleteCourseButton.setForeground(Color.white);
        deleteCourseButton.setFont(new Font("sansserif", 1, 16));
        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(Color.decode("#2f1938"));
        clearButton.setForeground(Color.white);
        clearButton.setFont(new Font("sansserif", 1, 16));
        
        JPanel tailPanel = new JPanel();
        tailPanel.setLayout(new GridLayout(2, 3, 150, 4));
        tailPanel.add(addCourseButton);
        tailPanel.add(viewCoursesButton);
        tailPanel.add(updateCourseButton);
        tailPanel.add(cancelCourseButton);
        tailPanel.add(deleteCourseButton);
        tailPanel.add(clearButton);


        addCourseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameTextField.getText();
                String courseTag = courseTagTextField.getText();
                String level = levelTextField.getText();
                String  stream = streamTextField.getText();
                Boolean[] coreStatus = {coreStatus1.isSelected(), coreStatus2.isSelected(), coreStatus3.isSelected(), coreStatus4.isSelected(), coreStatus5.isSelected(), coreStatus6.isSelected(), coreStatus7.isSelected(), coreStatus8.isSelected(), coreStatus9.isSelected(), coreStatus10.isSelected(), coreStatus11.isSelected(), coreStatus12.isSelected(), coreStatus13.isSelected(), coreStatus14.isSelected(), coreStatus15.isSelected(),  coreStatus16.isSelected(), coreStatus17.isSelected(), coreStatus18.isSelected(), coreStatus19.isSelected(), coreStatus20.isSelected(), coreStatus21.isSelected(), coreStatus22.isSelected(), coreStatus23.isSelected(), coreStatus24.isSelected()};
                String[] moduleNames = {moduleName1.getText(), moduleName2.getText(), moduleName3.getText(), moduleName4.getText(), moduleName5.getText(), moduleName6.getText(), moduleName7.getText(), moduleName8.getText(), moduleName9.getText(), moduleName10.getText(), moduleName11.getText(), moduleName12.getText(), moduleName13.getText(), moduleName14.getText(), moduleName15.getText(), moduleName16.getText(), moduleName17.getText(), moduleName18.getText(), moduleName19.getText(), moduleName20.getText(), moduleName21.getText(), moduleName22.getText(), moduleName23.getText(), moduleName24.getText()};
                String[] moduleCodes = {moduleCode1.getText(), moduleCode2.getText(), moduleCode3.getText(), moduleCode4.getText(), moduleCode5.getText(), moduleCode6.getText(), moduleCode7.getText(), moduleCode8.getText(), moduleCode9.getText(), moduleCode10.getText(), moduleCode11.getText(), moduleCode12.getText(), moduleCode13.getText(), moduleCode14.getText(), moduleCode15.getText(), moduleCode16.getText(), moduleCode17.getText(), moduleCode18.getText(), moduleCode19.getText(), moduleCode20.getText(), moduleCode21.getText(), moduleCode22.getText(), moduleCode23.getText(), moduleCode24.getText()};
                String[] semesters = {semester1.getText(), semester2.getText(), semester3.getText(), semester4.getText(), semester5.getText(), semester6.getText(), semester7.getText(), semester8.getText(), semester9.getText(), semester10.getText(), semester11.getText(), semester12.getText(), semester13.getText(), semester14.getText(), semester15.getText(), semester16.getText(), semester17.getText(), semester18.getText(), semester19.getText(), semester20.getText(), semester21.getText(), semester22.getText(), semester23.getText(), semester24.getText()};
                String[] teachers = {instructors1.getSelectedItem().toString(), instructors2.getSelectedItem().toString(), instructors3.getSelectedItem().toString(), instructors4.getSelectedItem().toString(), instructors5.getSelectedItem().toString(), instructors6.getSelectedItem().toString(), instructors7.getSelectedItem().toString(), instructors8.getSelectedItem().toString(), instructors9.getSelectedItem().toString(), instructors10.getSelectedItem().toString(), instructors11.getSelectedItem().toString(), instructors12.getSelectedItem().toString(), instructors13.getSelectedItem().toString(), instructors14.getSelectedItem().toString(), instructors15.getSelectedItem().toString(), instructors16.getSelectedItem().toString(), instructors17.getSelectedItem().toString(), instructors18.getSelectedItem().toString(), instructors19.getSelectedItem().toString(), instructors20.getSelectedItem().toString(), instructors21.getSelectedItem().toString(), instructors22.getSelectedItem().toString(), instructors23.getSelectedItem().toString(), instructors24.getSelectedItem().toString()};
                
                

                Boolean isEmpty = false;
                for(int i=0; i<coreStatus.length; i++){
                    if(moduleNames[i].isEmpty() || moduleCodes[i].isEmpty() || semesters[i].isEmpty() || teachers[i].isEmpty()){
                        isEmpty = true;
                    }
                }
                if(isEmpty==false && !courseName.isEmpty() && !courseTag.isEmpty() && !level.isEmpty() && !stream.isEmpty() ){
                    if(JOptionPane.showConfirmDialog(null, "Are you sure you want to add this course to the course list?") == JOptionPane.YES_OPTION){
                        int countError = 0;
                        for(int i=0; i<teachers.length; i++){
                            int moduleCount = 0;
                            for(int j=i+1; j<teachers.length; j++){
                                if(teachers[i] == teachers[j]){
                                    moduleCount++;
                                }
                            }
                            if(moduleCount >= 4){
                                JOptionPane.showMessageDialog(null, "An instructor cannot be assigned more than 4 modules!\nPlease make sure they are not assigned more than 4 courses!!");
                                countError = 1;
                                break;
                            }
                        }
                        if(countError == 0){
                            db.saveCourseDetails(courseName, courseTag, level, stream, moduleNames, moduleCodes, semesters, coreStatus, teachers);
                            courserecords.addNewCourse(courseName, courseTag, level, stream, false);
                            JOptionPane.showMessageDialog(null, "Successfully added the course to the list!!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please! You have to fill in all the boxes in order to create a new course!!");
                }

            }
        });


        updateCourseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameTextField.getText();
                String courseTag = courseTagTextField.getText();
                String level = levelTextField.getText();
                String  stream = streamTextField.getText();
                Boolean[] coreStatus = {coreStatus1.isSelected(), coreStatus2.isSelected(), coreStatus3.isSelected(), coreStatus4.isSelected(), coreStatus5.isSelected(), coreStatus6.isSelected(), coreStatus7.isSelected(), coreStatus8.isSelected(), coreStatus9.isSelected(), coreStatus10.isSelected(), coreStatus11.isSelected(), coreStatus12.isSelected(), coreStatus13.isSelected(), coreStatus14.isSelected(), coreStatus15.isSelected(),  coreStatus16.isSelected(), coreStatus17.isSelected(), coreStatus18.isSelected(), coreStatus19.isSelected(), coreStatus20.isSelected(), coreStatus21.isSelected(), coreStatus22.isSelected(), coreStatus23.isSelected(), coreStatus24.isSelected()};
                String[] moduleNames = {moduleName1.getText(), moduleName2.getText(), moduleName3.getText(), moduleName4.getText(), moduleName5.getText(), moduleName6.getText(), moduleName7.getText(), moduleName8.getText(), moduleName9.getText(), moduleName10.getText(), moduleName11.getText(), moduleName12.getText(), moduleName13.getText(), moduleName14.getText(), moduleName15.getText(), moduleName16.getText(), moduleName17.getText(), moduleName18.getText(), moduleName19.getText(), moduleName20.getText(), moduleName21.getText(), moduleName22.getText(), moduleName23.getText(), moduleName24.getText()};
                String[] moduleCodes = {moduleCode1.getText(), moduleCode2.getText(), moduleCode3.getText(), moduleCode4.getText(), moduleCode5.getText(), moduleCode6.getText(), moduleCode7.getText(), moduleCode8.getText(), moduleCode9.getText(), moduleCode10.getText(), moduleCode11.getText(), moduleCode12.getText(), moduleCode13.getText(), moduleCode14.getText(), moduleCode15.getText(), moduleCode16.getText(), moduleCode17.getText(), moduleCode18.getText(), moduleCode19.getText(), moduleCode20.getText(), moduleCode21.getText(), moduleCode22.getText(), moduleCode23.getText(), moduleCode24.getText()};
                String[] semesters = {semester1.getText(), semester2.getText(), semester3.getText(), semester4.getText(), semester5.getText(), semester6.getText(), semester7.getText(), semester8.getText(), semester9.getText(), semester10.getText(), semester11.getText(), semester12.getText(), semester13.getText(), semester14.getText(), semester15.getText(), semester16.getText(), semester17.getText(), semester18.getText(), semester19.getText(), semester20.getText(), semester21.getText(), semester22.getText(), semester23.getText(), semester24.getText()};
                String[] teachers = {instructors1.getSelectedItem().toString(), instructors2.getSelectedItem().toString(), instructors3.getSelectedItem().toString(), instructors4.getSelectedItem().toString(), instructors5.getSelectedItem().toString(), instructors6.getSelectedItem().toString(), instructors7.getSelectedItem().toString(), instructors8.getSelectedItem().toString(), instructors9.getSelectedItem().toString(), instructors10.getSelectedItem().toString(), instructors11.getSelectedItem().toString(), instructors12.getSelectedItem().toString(), instructors13.getSelectedItem().toString(), instructors14.getSelectedItem().toString(), instructors15.getSelectedItem().toString(), instructors16.getSelectedItem().toString(), instructors17.getSelectedItem().toString(), instructors18.getSelectedItem().toString(), instructors19.getSelectedItem().toString(), instructors20.getSelectedItem().toString(), instructors21.getSelectedItem().toString(), instructors22.getSelectedItem().toString(), instructors23.getSelectedItem().toString(), instructors24.getSelectedItem().toString()};
                Boolean isEmpty = false;
                for(int i=0; i<coreStatus.length; i++){
                    if(moduleNames[i].isEmpty() || moduleCodes[i].isEmpty() || semesters[i].isEmpty() || teachers[i].isEmpty()){
                        isEmpty = true;
                    }
                }
                if(isEmpty==false && !courseName.isEmpty() && !courseTag.isEmpty() && !level.isEmpty() && !stream.isEmpty()){
                    if(JOptionPane.showConfirmDialog(null, "Are you sure you want to update the course?") == JOptionPane.YES_OPTION){
                        int countError = 0;
                        for(int i=0; i<teachers.length; i++){
                            int moduleCount = 0;
                            for(int j=i+1; j<teachers.length; j++){
                                if(teachers[i] == teachers[j]){
                                    moduleCount++;
                                }
                            }
                            if(moduleCount >= 4){
                                JOptionPane.showMessageDialog(null, "An instructor cannot be assigned more than 4 modules!\nPlease make sure they are not assigned more than 4 courses!!");
                                countError = 1;
                                break;
                            }
                        }
                        if(countError == 0){
                            db.updateCourseDetails(courseName, courseTag, level, stream, moduleNames, moduleCodes, semesters, coreStatus, teachers);
                            JOptionPane.showMessageDialog(null, "Course updated!");
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please! You have to fill in all the boxes in order to update this course!!");
                }
            }
        });

        cancelCourseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameTextField.getText();
                String courseTag = courseTagTextField.getText();
                if(!courseName.isEmpty() && !courseTag.isEmpty()){
                    
                    if(JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this course?") == JOptionPane.YES_OPTION){
                        db.updateCourseDetails(courseTag, true);
                        courserecords.updateCourseFile(courseTag, true);
                        JOptionPane.showMessageDialog(null, "Course is Cancelled Successfully!!");
                        clearButton.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the Course Name and Tag above in order to cancel the course!");
                }
            }
        });

        deleteCourseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameTextField.getText();
                String courseTag = courseTagTextField.getText();
                if(!courseName.isEmpty() && !courseTag.isEmpty()){
                    if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this course?") == JOptionPane.YES_OPTION){
                        db.deleteCourseDetails(courseTag);
                        courserecords.deleteCourse(courseTag);
                        JOptionPane.showMessageDialog(null, "Course is deleted Successfully!!");
                        clearButton.doClick();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the Course Tag above in order to delete the course!");
                }
            }
        });

        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<modsNames.size(); i++){
                    courseNameTextField.setText("");
                    courseTagTextField.setText("");
                    levelTextField.setText("");
                    streamTextField.setText("");
                    modsNames.get(i).setText("");
                    modsCodes.get(i).setText("");
                    modsSems.get(i).setText("");
                    modsInstructors.get(i).setSelectedIndex(0);
                }
            }
        });


        viewCoursesButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                String coursename = JOptionPane.showInputDialog(null, "Please enter the course tag\n(Eg. Enter 'BCS' if you want to search for Computer Scicnce)");
                ResultSet courseResult = db.getCourses();
                try {
                    while(courseResult.next()){
                        if(coursename.equalsIgnoreCase(courseResult.getString("courseTag")) && courseResult.getBoolean("cancelStatus") == false){
                            courseNameTextField.setText(courseResult.getString("courseName"));
                            levelTextField.setText(courseResult.getString("level"));
                            courseTagTextField.setText(courseResult.getString("courseTag"));
                            streamTextField.setText(courseResult.getString("stream"));
                            cancelledStatusButton.setSelected(courseResult.getBoolean("cancelStatus"));
                            ResultSet modulesResult = db.getModules(coursename);
                            int i = 0;
                            while (modulesResult.next()) {
                                modsNames.get(i).setText(modulesResult.getString("moduleName"));
                                modsCodes.get(i).setText(modulesResult.getString("moduleCode"));
                                modsSems.get(i).setText(modulesResult.getString("semester"));
                                modsInstructors.get(i).setSelectedItem(modulesResult.getString("teacherName"));
                                modsCoreStats.get(i).setSelected(modulesResult.getBoolean("coreStatus"));
                                i++;
                            }
                        } else if (coursename.equalsIgnoreCase(courseResult.getString("courseTag")) && courseResult.getBoolean("cancelStatus") == true){
                            if(JOptionPane.showConfirmDialog(null, coursename+" course is cancelled for now.\n\nWould you like to uncancel the course?\n\n") == JOptionPane.YES_OPTION){
                                db.updateCourseDetails(coursename, false);
                                courserecords.updateCourseFile(coursename, false);
                                JOptionPane.showMessageDialog(null, coursename + "Course is now available! You can now search for the course again!!");
                                clearButton.doClick();
                            }
                        }
                        }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
            }
        });


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, 1));

        mainPanel.add(topPanel);
        mainPanel.add(pane);
        mainPanel.add(tailPanel);


        frame.add(mainPanel);




        frame.setVisible(true);
        
        }
    }
}