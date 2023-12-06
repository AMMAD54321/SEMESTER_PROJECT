package com.example.semester_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClubDAO {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql:///semesterproject";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    // SQL query
    private static final String SELECT_CLUBS = "SELECT ClubName FROM Club";

    // Method to retrieve club names from the database
    public static List<String> getClubs() {
        List<String> clubNames = new ArrayList<>();

        try {
            // Open a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Execute the query
            preparedStatement = connection.prepareStatement(SELECT_CLUBS);
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                System.out.println("Clubs");
                String clubName = resultSet.getString("ClubName");
                clubNames.add(clubName);
            }
        } catch (SQLException e) {
            System.out.println("Exception");
            e.printStackTrace();
        } finally {
            // Close the connections
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clubNames;
    }
    public static List<String> getClubs(int i) {
        List<String> clubNames = new ArrayList<>();

        try {
            // Open a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Execute the query
            preparedStatement = connection.prepareStatement("SELECT ClubName\n" +
                    "FROM Club\n" +
                    "WHERE ClubName != 'Tottenham Hotspur';\n");
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                System.out.println("Clubs");
                String clubName = resultSet.getString("ClubName");
                clubNames.add(clubName);
            }
        } catch (SQLException e) {
            System.out.println("Exception");
            e.printStackTrace();
        } finally {
            // Close the connections
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clubNames;
    }
}
