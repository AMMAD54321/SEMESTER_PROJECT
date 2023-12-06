package com.example.semester_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        final String url ="jdbc:mysql:///semesterproject";
        final String user="root";
        final String password="12345";
        Connection con=  DriverManager.getConnection(url,user,password);
        if(con==null){
            System.out.println("JDBC Connection not established");
            return;
        }
        else {
            System.out.println("JDBC Connection successful");
        }
        con.close();
    }
}
