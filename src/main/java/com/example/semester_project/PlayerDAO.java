package com.example.semester_project;

import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    public static ArrayList<Integer> Shortlisted=new ArrayList<>();
    private static final String URL = "jdbc:mysql:///semesterproject";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    private static final String SELECT_PLAYER_ATTRIBUTES =
            "SELECT PlayerName, Nationality, Age, Goals, Assists, Reds, Yellows, Health FROM Player " +
                    "WHERE ClubName = (SELECT ClubName FROM Club WHERE ClubName = 'Tottenham Hotspur');";
    private static final String SELECT_PLAYERS_BY_CLUB = "SELECT * FROM player WHERE ClubName = ?";
    private static final String SELECT_PLAYERS_BY_PlayerName= "SELECT * FROM player WHERE PlayerName = ?";
    private static final String Query_For_Squad_Table =
            "SELECT p.PlayerID, p.PlayerName AS Name, p.ShirtNumber, p.PreferredPosition AS Position,\n" +
                    "       p.Nationality, s.Date AS ContractDate, p.Age\n" +
                    "FROM player p\n" +
                    "JOIN contracts s ON p.PlayerID = s.PlayerID\n" +
                    "WHERE p.ClubName = 'Tottenham Hotspur';\n";

    public static List<Player> getPlayerAttributes() {
        List<Player> players = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_PLAYER_ATTRIBUTES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Print im in result loop");
                String playerName = resultSet.getString("PlayerName");
                String nationality = resultSet.getString("Nationality");
                int age = resultSet.getInt("Age");
                int goals = resultSet.getInt("Goals");
                int assists = resultSet.getInt("Assists");
                int reds = resultSet.getInt("Reds");
                int yellows = resultSet.getInt("Yellows");
                String health = resultSet.getString("Health");

                Player player = new Player(playerName, nationality, String.valueOf(goals), String.valueOf(assists),
                        String.valueOf(reds), String.valueOf(yellows), health, String.valueOf(age));
                players.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Exception");
            e.printStackTrace();
        } finally {
            closeConnections();
        }

        return players;
    }

    public static List<Player> getPlayerAttributes(int i) {
        List<Player> players = new ArrayList<>();

        try {
            System.out.println("In the Connection");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(Query_For_Squad_Table);
            resultSet = preparedStatement.executeQuery();
            System.out.println("Going in while");
            while (resultSet.next()) {
                System.out.println("Hello Data is being Fetched");
                String playerID = resultSet.getString("PlayerID");
                String playerName = resultSet.getString("Name");
                String shirtNumber = resultSet.getString("ShirtNumber");
                String position = resultSet.getString("Position");
                String nationality = resultSet.getString("Nationality");
                String contractDate = resultSet.getString("ContractDate");
                String age = resultSet.getString("Age");

                Player player = new Player(playerID, shirtNumber, position, contractDate, playerName, nationality, age);
                players.add(player);
            }
        } catch (SQLException e) {
            System.out.println("Exception");
            e.printStackTrace();
        } finally {
            closeConnections();
        }

        return players;
    }


    public static List<String> getPlayersByClub(String clubName) {
        List<String> playerNames = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_PLAYERS_BY_CLUB);
            preparedStatement.setString(1, clubName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String playerName = resultSet.getString("PlayerName");
                playerNames.add(playerName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }

        return playerNames;
    }

    private static void closeConnections() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updatePlayer(int playerID, int shirtNumber, int age, String preferredPosition) {
        String updateQuery = "UPDATE player SET ShirtNumber = ?, Age = ?, PreferredPosition = ? WHERE PlayerID = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            // Set parameters
            preparedStatement.setInt(1, shirtNumber);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, preferredPosition);
            preparedStatement.setInt(4, playerID);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Player information updated successfully.");
            } else {
                System.out.println("No player found with PlayerID: " + playerID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Method to get all players
    public static List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            // Modify the query to select all players
            String selectAllPlayersQuery = "SELECT * FROM Player where ClubName!='Tottenham Hotspur'";
            preparedStatement = connection.prepareStatement(selectAllPlayersQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Extract player details from the result set
                String playerName = resultSet.getString("PlayerName");
                String PlayerID = resultSet.getString("PlayerID");
                String Club = resultSet.getString("ClubName");
                // ... (add other player attributes as needed)

                // Create a Player object and add it to the list
                Player player = new Player(PlayerID, playerName,Club);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }

        return players;
    }
    public static List<Player> getPlayersByClub(String clubName,int i) {
        List<Player> players = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_PLAYERS_BY_PlayerName);
            preparedStatement.setString(1, clubName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Extract player details from the result set
                String playerName = resultSet.getString("PlayerName");
                String PlayerID = resultSet.getString("PlayerID");
                String Club = resultSet.getString("ClubName");
                // ... (add other player attributes as needed)

                // Create a Player object and add it to the list
                Player player = new Player(PlayerID, playerName,Club);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }

        return players;
    }

}
