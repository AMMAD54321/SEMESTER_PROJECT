package com.example.semester_project;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchesDAO {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql:///semesterproject";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    // SQL query for inserting a match
    private static final String INSERT_MATCH = "INSERT INTO Matches ( Date, HomeTeam, AwayTeam) VALUES (?, ?, ?)";
    private static final String Get_Matches ="SELECT *\n" +
            "FROM matches\n" +
            "WHERE HomeTeam = 'Tottenham Hotspur' OR AwayTeam = 'Tottenham Hotspur';\n";

    private static final String Get_Other_Matches="SELECT *\n" +
            "FROM matches\n" +
            "WHERE HomeTeam != 'Tottenham Hotspur' AND AwayTeam != 'Tottenham Hotspur';\n";
    // Method to insert a match into the database
    public static void insertMatch(LocalDate date, String homeTeam, String awayTeam) {
        try {
            // Open a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Prepare the statement for insertion
            preparedStatement = connection.prepareStatement(INSERT_MATCH);

            preparedStatement.setDate(1, java.sql.Date.valueOf(date));
            preparedStatement.setString(2, homeTeam);
            preparedStatement.setString(3, awayTeam);

            // Execute the insert statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<Matches> selectMatches() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Matches> matches = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(Get_Matches);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Matches match = new Matches();

                // Set the properties of the Matches object
                match.matchIdProperty().set(resultSet.getInt("MatchID"));
                match.dateProperty().set(resultSet.getDate("Date").toLocalDate());
                match.homeProperty().set(resultSet.getString("HomeTeam"));
                match.awayProperty().set(resultSet.getString("AwayTeam"));

                matches.add(match);
            }
        } finally {
            // Close resources in the reverse order of their creation to avoid resource leaks
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return matches;
    }
    public static List<Matches> selectMatches(int i) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Matches> matches = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(Get_Other_Matches);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Matches match = new Matches();

                // Set the properties of the Matches object
                match.matchIdProperty().set(resultSet.getInt("MatchID"));
                match.dateProperty().set(resultSet.getDate("Date").toLocalDate());
                match.homeProperty().set(resultSet.getString("HomeTeam"));
                match.awayProperty().set(resultSet.getString("AwayTeam"));

                matches.add(match);
            }
        } finally {
            // Close resources in the reverse order of their creation to avoid resource leaks
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return matches;
    }

    public static void insertMatchEvent(int matchID, String playerName, String event) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Connect to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Step 2: Find PlayerID for the given PlayerName
            String findPlayerIDQuery = "SELECT PlayerID, Goals, Assists, Reds, Yellows FROM player WHERE PlayerName = ?";
            preparedStatement = connection.prepareStatement(findPlayerIDQuery);
            preparedStatement.setString(1, playerName);
            resultSet = preparedStatement.executeQuery();

            // Check if the player exists
            if (!resultSet.next()) {
                System.out.println("Player with name " + playerName + " not found.");
                return;
            }

            int playerID = resultSet.getInt("PlayerID");
            int goals = resultSet.getInt("Goals");
            int assists = resultSet.getInt("Assists");
            int reds = resultSet.getInt("Reds");
            int yellows = resultSet.getInt("Yellows");

            // Step 3: Insert a new row into matchevent table
            String insertMatchEventQuery = "INSERT INTO matchevent (MatchID, PlayerID, Event) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertMatchEventQuery);
            preparedStatement.setInt(1, matchID);
            preparedStatement.setInt(2, playerID);
            preparedStatement.setString(3, event);
            preparedStatement.executeUpdate();

            // Step 4: Update player statistics based on the event
            switch (event.toLowerCase()) {
                case "goal":
                    goals++;
                    break;
                case "assist":
                    assists++;
                    break;
                case "red card":
                    reds++;
                    break;
                case "yellow card":
                    yellows++;
                    break;
                // Add more cases for other events if needed

                default:
                    // Handle unrecognized events if needed
            }

            // Step 5: Update player statistics in the player table
            String updatePlayerStatsQuery = "UPDATE player SET Goals = ?, Assists = ?, Reds = ?, Yellows = ? WHERE PlayerID = ?";
            preparedStatement = connection.prepareStatement(updatePlayerStatsQuery);
            preparedStatement.setInt(1, goals);
            preparedStatement.setInt(2, assists);
            preparedStatement.setInt(3, reds);
            preparedStatement.setInt(4, yellows);
            preparedStatement.setInt(5, playerID);
            preparedStatement.executeUpdate();

            System.out.println("Match Event successfully inserted and player statistics updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 6: Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Method to check if MatchCompleted is false for a specific match
    public static boolean isMatchIncomplete(int matchID) {
        try {
            // Open a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // SQL query to check if MatchCompleted is false
            String sql = "SELECT MatchCompleted FROM matches WHERE MatchID = ? AND MatchCompleted = 0";

            // Create a PreparedStatement
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, matchID);

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Check if the result set is not empty
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle exceptions appropriately in a real application
        } finally {
            // Close the connection in the finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // Method to update MatchCompleted for a specific match
    public static void markMatchComplete(int matchID) {
        try {
            // Open a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // SQL query to update MatchCompleted to true
            String sql = "UPDATE matches SET MatchCompleted = 1 WHERE MatchID = ?";

            // Create a PreparedStatement
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, matchID);
                System.out.println("Success");
                // Execute the update
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately in a real application
        } finally {
            // Close the connection in the finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
