package GUI;

import DBHelpers.*;
import Bodies.Teacher;

import java.sql.*;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class TeacherPanel extends JPanel {

    private JPanel optionsLeftTeacher = new JPanel();
    private JPanel optionsRightTeacher = new JPanel();


    private JPanel dashBoardPanel;
    private JRadioButton dashBoard;
    private JLabel topTextOfViewPanel;
    private JPanel detailsPanel;
    private ButtonGroup optionsGroup;
    private Teacher teacher;
    private String[] myModules;
    private String[] myModuleCodes;
    private ManagementDatabase db = new ManagementDatabase();

    public TeacherPanel(String userId, String password, JButton logoutButton) {

        teacher = new Teacher(userId, password);

        ResultSet resultSet = db.getTeacherModules(teacher.getTeacherName());
        int i = 0;
        try {
            while (resultSet.next()) {
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        myModules = new String[i];
        myModuleCodes = new String[i];

        resultSet = db.getTeacherModules(teacher.getTeacherName());
        i = 0;
        try {
            while (resultSet.next()) {
                myModules[i] = resultSet.getString("moduleName");
                myModuleCodes[i] = resultSet.getString("moduleCode");
                System.out.println(myModuleCodes[i] + " " + myModules[i]);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setLayout(new BoxLayout(this, 0));

        optionsLeftTeacher.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#ffbf12")), "Options: "));
        optionsLeftTeacher.setLayout(new GridLayout(2, 1));
        optionsLeftTeacher.setMaximumSize(new DimensionUIResource(500, 1080));
        JPanel optionsLeftTeacherTop = new JPanel();
        optionsLeftTeacherTop.setLayout(new GridLayout(4, 1));
        dashBoardPanel = new JPanel();
        dashBoardPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        dashBoardPanel.setLayout(new GridLayout(1, 1));

        dashBoard = new JRadioButton("Dashboard", true);
        dashBoard.setBackground(Color.decode("#212121"));
        dashBoard.setForeground(Color.WHITE);

        Font fontForOptions = new Font("SansSerif", 0, 24);
        dashBoard.setFont(fontForOptions);

        optionsGroup = new ButtonGroup();
        optionsGroup.add(dashBoard);

        dashBoardPanel.add(dashBoard);

        logoutButton.setBackground(Color.decode("#f0bc2e"));
        logoutButton.setForeground(Color.white);
        logoutButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.decode("#212121")));

        optionsLeftTeacherTop.add(dashBoardPanel);

        JPanel optionsLeftTeacherBottom = new JPanel();
        optionsLeftTeacherBottom.setLayout(new BoxLayout(optionsLeftTeacherBottom, 1));
        JPanel logoPanel = new JPanel();
        logoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        logoPanel.setBackground(Color.WHITE);
        ImageIcon logo = new ImageIcon("src/GUI/Logo.png");
        JLabel image = new JLabel();
        image.setIcon(logo);
        logoPanel.add(image, BorderLayout.CENTER);
        JPanel logoutButtonPanel = new JPanel(new GridLayout(1, 1));
        logoutButtonPanel.add(logoutButton);
        optionsLeftTeacherBottom.add(logoPanel);
        optionsLeftTeacherBottom.add(logoutButtonPanel);
        optionsLeftTeacher.add(optionsLeftTeacherTop);
        optionsLeftTeacher.add(optionsLeftTeacherBottom);

        optionsRightTeacher.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#ffbf12")), "View: "));
        optionsRightTeacher.setLayout(new BoxLayout(optionsRightTeacher, 1));

        JPanel topViewPanel = new JPanel();
        topTextOfViewPanel = new JLabel();
        topTextOfViewPanel.setForeground(Color.decode("#230030"));
        topTextOfViewPanel.setFont(new Font("Sans Serif", 1, 40));
        detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));

        dashBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColorOfSelected();
                detailsPanel.removeAll();
                topTextOfViewPanel.setText("Welcome " + teacher.getTeacherName() + "!");
                addDashboardToDetailsPanel();
            }

        });


        dashBoard.doClick();

        topViewPanel.add(topTextOfViewPanel, BorderLayout.CENTER);
        optionsRightTeacher.add(topViewPanel);
        optionsRightTeacher.add(detailsPanel);

        add(optionsLeftTeacher);
        add(optionsRightTeacher);
    }

    private void addDashboardToDetailsPanel() {

        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLoweredSoftBevelBorder(), "Info: "));
        infoPanel.setLayout(new BoxLayout(infoPanel, 1));
        infoPanel.setBackground(Color.white);

        JPanel infoTitlePanel = new JPanel();
        infoTitlePanel.setBackground(Color.white);
        JLabel infoTitleText = new JLabel("Your assigned modules: ");
        infoTitleText.setFont(new Font("sansserif", 1, 24));
        infoTitlePanel.add(infoTitleText, BorderLayout.CENTER);

        JButton manageGradesButton1 = new JButton("Manage Grades");
        JButton manageGradesButton2 = new JButton("Manage Grades");
        JButton manageGradesButton3 = new JButton("Manage Grades");
        JButton manageGradesButton4 = new JButton("Manage Grades");
        manageGradesButton1.setBackground(Color.decode("#f0bc2e"));
        manageGradesButton1.setForeground(Color.WHITE);
        manageGradesButton2.setBackground(Color.decode("#f0bc2e"));
        manageGradesButton2.setForeground(Color.WHITE);
        manageGradesButton3.setBackground(Color.decode("#f0bc2e"));
        manageGradesButton3.setForeground(Color.WHITE);
        manageGradesButton4.setBackground(Color.decode("#f0bc2e"));
        manageGradesButton4.setForeground(Color.WHITE);
        manageGradesButton1.setFont(new Font("sansserif", 1, 16));
        manageGradesButton2.setFont(new Font("sansserif", 1, 16));
        manageGradesButton3.setFont(new Font("sansserif", 1, 16));
        manageGradesButton4.setFont(new Font("sansserif", 1, 16));

        manageGradesButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeFrameForStudentGrades(myModules[0], myModuleCodes[0]);
            }
        });
        manageGradesButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeFrameForStudentGrades(myModules[1], myModuleCodes[1]);
            }
        });
        manageGradesButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeFrameForStudentGrades(myModules[2], myModuleCodes[2]);
            }
        });
        manageGradesButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeFrameForStudentGrades(myModules[3], myModuleCodes[3]);
            }
        });

        JPanel panelForButtons1 = new JPanel();
        JPanel panelForButtons2 = new JPanel();
        JPanel panelForButtons3 = new JPanel();
        JPanel panelForButtons4 = new JPanel();
        panelForButtons1.setBackground(Color.white);
        panelForButtons2.setBackground(Color.white);
        panelForButtons3.setBackground(Color.white);
        panelForButtons4.setBackground(Color.white);
        panelForButtons1.add(manageGradesButton1, BorderLayout.CENTER);
        panelForButtons2.add(manageGradesButton2, BorderLayout.CENTER);
        panelForButtons3.add(manageGradesButton3, BorderLayout.CENTER);
        panelForButtons4.add(manageGradesButton4, BorderLayout.CENTER);
        ArrayList<JPanel> buttonPanelList = new ArrayList<JPanel>();
        buttonPanelList.add(panelForButtons1);
        buttonPanelList.add(panelForButtons2);
        buttonPanelList.add(panelForButtons3);
        buttonPanelList.add(panelForButtons4);

        JPanel infoBodyPanel = new JPanel(new GridLayout(7, 3, 0, 16));
        infoBodyPanel.setBackground(Color.white);
        infoBodyPanel.add(new JLabel(""));
        infoBodyPanel.add(new JLabel(""));
        infoBodyPanel.add(new JLabel(""));
        infoBodyPanel.add(new JLabel("        Module Name"));
        infoBodyPanel.add(new JLabel("Status"));
        infoBodyPanel.add(new JLabel("                                Actions"));

        for (int i = 0; i < myModules.length; i++) {
            infoBodyPanel.add(new JLabel("        " + myModules[i]));
            infoBodyPanel.add(new JLabel("Teaching"));
            infoBodyPanel.add(buttonPanelList.get(i));
        }

        JPanel bottomPanel = new JPanel(new GridLayout(5, 1, 0, 32));
        



        infoPanel.add(infoTitlePanel);
        infoPanel.add(infoBodyPanel);
        infoPanel.add(bottomPanel);
        detailsPanel.add(infoPanel);

    }









    private void makeFrameForStudentGrades(String moduleName, String moduleCode) {

        int frameHeight = 500;
        int frameWidth = 900;
        String[] columnNames = {"Student Id", "Name", "Marks", "Remarks"};
        Object[][] rowData = {};
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        table.setModel(model);



        JFrame frame = new JFrame("Manage Grades of Module: " + moduleName);
        frame.setSize(frameWidth, frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        refreshTable(moduleName, moduleCode, model);

        JTextField nameTextField = new JTextField();
        JTextField studentIdTextField = new JTextField();
        nameTextField.setEditable(false);
        studentIdTextField.setEditable(false);
        JTextField marksTextField = new JTextField();
        JTextArea remarksTextArea = new JTextArea();

        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Color.decode("#f0bc2e"));
        saveButton.setForeground(Color.white);
        saveButton.setFont(new Font("SansSerif", 1, 12));
        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(Color.decode("#f0bc2e"));
        clearButton.setForeground(Color.white);
        clearButton.setFont(new Font("SansSerif", 1, 12));

        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Edit: "));
        formPanel.setLayout(new GridLayout(5, 2, 0, 0));
        formPanel.add(new JLabel("    Student Id"));
        formPanel.add(studentIdTextField);
        formPanel.add(new JLabel("    Name"));
        formPanel.add(nameTextField);
        formPanel.add(new JLabel("    Marks"));
        formPanel.add(marksTextField);
        formPanel.add(new JLabel("    Remarks"));
        formPanel.add(remarksTextArea);
        formPanel.add(clearButton);
        formPanel.add(saveButton);

        
        table.setDefaultEditor(Object.class, null);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int rownumber = table.getSelectedRow();
                    studentIdTextField.setText((String)model.getValueAt(rownumber, 0));
                    nameTextField.setText((String)model.getValueAt(rownumber, 1));
                    marksTextField.setText((String)model.getValueAt(rownumber, 2));
                    remarksTextArea.setText((String)model.getValueAt(rownumber, 3));
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


        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String marks = marksTextField.getText();
                String remarks = remarksTextArea.getText();
                String studentId = studentIdTextField.getText();
                if(!marks.isEmpty() || !remarks.isEmpty()){
                    db.editStudentMarks(marks, remarks, studentId, moduleCode);
                    refreshTable(moduleName, moduleCode, model);
                }
            }
        });


        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                studentIdTextField.setText("");
                nameTextField.setText("");
                marksTextField.setText("");
                remarksTextArea.setText("");
            }
        });



        JScrollPane pane = new JScrollPane(table);
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createTitledBorder("View Details"));
        leftPanel.setLayout(new GridLayout(1, 1));
        leftPanel.add(pane);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, 1));
        rightPanel.add(formPanel);
        ImageIcon logo = new ImageIcon("src/GUI/Logo.png");
        JLabel image = new JLabel();
        image.setIcon(logo);
        JPanel imagePanel = new JPanel();
        imagePanel.add(image);
        rightPanel.add(imagePanel);
        rightPanel.add(new JPanel(new GridLayout(5, 1, 0, 8)));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        frame.add(mainPanel);
        frame.setVisible(true);

    }

    public void refreshTable(String moduleName, String moduleCode, DefaultTableModel model){
        ResultSet resultSet = db.getStudentsInModule(moduleCode);
        try {
            model.setRowCount(0);
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("studentId"),
                        resultSet.getString("studentName"),
                        resultSet.getString("marksSecured"),
                        resultSet.getString("remarks")
                    });
                }
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
    }






    private void changeColorOfSelected(){

            

    }
}
