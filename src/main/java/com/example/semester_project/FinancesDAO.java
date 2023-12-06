package com.example.semester_project;

import javafx.scene.control.Alert;

import java.math.BigInteger;
import java.sql.*;

public class FinancesDAO {
    private static final String JDBC_URL = "jdbc:mysql:///semesterproject";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";
    public static void SetBudget(){
        // JDBC URL, username, and password of MySQL server


        // SQL query to update IsDefault to true
        String sqlQuery = "UPDATE budget SET IsDefault = true WHERE TransactionID = 1;";

        try (
                // Establish a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Create a statement
                Statement statement = connection.createStatement()
        ) {
            // Execute the update query
            int rowsAffected = statement.executeUpdate(sqlQuery);

            // Print the number of rows affected
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void setBudget(BigInteger bigInteger) {
        // SQL query to insert values
        String sqlQuery = "INSERT INTO budget (TotalBudget, RemainingBudget) VALUES (?, ?)";

        try (
                // Establish a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        ) {
            // Set parameters for the prepared statement
            preparedStatement.setObject(1, bigInteger);
            preparedStatement.setObject(2, bigInteger);

            // Execute the insert query
            int rowsAffected = preparedStatement.executeUpdate();

            // Print the number of rows affected
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean isDefault(int transactionId) {
        boolean isDefault = false;

        // SQL query to retrieve IsDefault value
        String sqlQuery = "SELECT IsDefault FROM budget WHERE TransactionID = ?";

        try (
                // Establish a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        ) {
            // Set parameter for the prepared statement
            preparedStatement.setInt(1, transactionId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if there is a result
                if (resultSet.next()) {
                    // Retrieve the boolean value
                    isDefault = resultSet.getBoolean("IsDefault");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDefault;
    }
    public static long getBudgetSpent(int transactionId) {
        long budgetSpent = 0;

        // SQL query to retrieve BudgetSpent value
        String sqlQuery = "SELECT BudgetSpent FROM budget WHERE TransactionID = ?";

        try (
                // Establish a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        ) {
            // Set parameter for the prepared statement
            preparedStatement.setInt(1, transactionId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if there is a result
                if (resultSet.next()) {
                    // Retrieve the BudgetSpent value
                    budgetSpent = resultSet.getLong("BudgetSpent");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return budgetSpent;
    }

    public static long getRemainingBudget(int transactionId) {
        long remainingBudget = 0;

        // SQL query to retrieve RemainingBudget value
        String sqlQuery = "SELECT RemainingBudget FROM budget WHERE TransactionID = ?";

        try (
                // Establish a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        ) {
            // Set parameter for the prepared statement
            preparedStatement.setInt(1, transactionId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if there is a result
                if (resultSet.next()) {
                    // Retrieve the RemainingBudget value
                    remainingBudget = resultSet.getLong("RemainingBudget");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return remainingBudget;
    }

    public static void updateBudget(long amount, int transactionId) {
        // Check if the given amount is greater than RemainingBudget
        if (amount > getRemainingBudget(transactionId,1)) {
            //System.out.println("Error: The amount is greater than the Remaining Budget.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Error: The amount is greater than the Remaining Budget.");
            alert.showAndWait();
            return;
        }

        // SQL query to update BudgetSpent and RemainingBudget
        String sqlQuery = "UPDATE budget SET BudgetSpent = BudgetSpent + ?, RemainingBudget = RemainingBudget - ? WHERE TransactionID = ?";

        try (
                // Establish a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        ) {
            // Set parameters for the prepared statement
            preparedStatement.setLong(1, amount);
            preparedStatement.setLong(2, amount);
            preparedStatement.setInt(3, transactionId);

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            // Print the number of rows affected
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static long getRemainingBudget(int transactionId,int i) {
        long remainingBudget = 0;

        // SQL query to retrieve RemainingBudget value
        String sqlQuery = "SELECT RemainingBudget FROM budget WHERE TransactionID = ?";

        try (
                // Establish a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        ) {
            // Set parameter for the prepared statement
            preparedStatement.setInt(1, transactionId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if there is a result
                if (resultSet.next()) {
                    // Retrieve the RemainingBudget value
                    remainingBudget = resultSet.getLong("RemainingBudget");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return remainingBudget;
    }
    public static void addProfit(long amount, int transactionId) {
        // SQL query to add profit to RemainingBudget
        String sqlQuery = "UPDATE budget SET RemainingBudget = RemainingBudget + ? WHERE TransactionID = ?";

        try (
                // Establish a connection
                Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

                // Create a prepared statement
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)
        ) {
            // Set parameters for the prepared statement
            preparedStatement.setLong(1, amount);
            preparedStatement.setInt(2, transactionId);

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            // Print the number of rows affected
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

