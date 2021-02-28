package main;

import java.io.*;
import DBHelpers.DBUtils;
import GUI.*;

public class Main {

    /* If you want to view the application with the dummy datas, here are the credientials for login
    
        
    *****Login For Student*****

        username = {ST00001, ST00002, ST00003, ST00004}
        password = {1234, 1234, 1234, 1234}


        *****For Teacher****
        username = {TH00001, TH00002, TH00003, TH00004, TH00005, TH00006}
        password = {1234, 1234, 1234, 1234}


        ****For Admin****
        username = AD00001
        password = password@admin

    
    */
    
    public static void main(String[] args) {
        //connect to the database
        new DBUtils();
        //create a new admin
        createAdmin();
        //run the gui of the program
        startGUI();
    }

    public static void startGUI(){
        new Login().showLoginFrame();
    }

    public static void createAdmin(){
        //setting up the log in data for admin
        System.out.println("All the datas are dummy datas. In order to view/edit and add the datas, login using the admin account.");
        System.out.println("Admin Username: AD00001\tAdmin Password: password@admin");
        System.out.println("If you want to continue as a student. You can register on your own too...");
        try {
            FileWriter file = new FileWriter("src/Files/Admin.txt", false);
            file.write("AD00001 password@admin");
            file.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
