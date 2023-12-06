package com.example.semester_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StandingsDAO {
    private static final String JDBC_URL = "jdbc:mysql:///semesterproject";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    // Method to retrieve standings data from the database
    public static List<TeamStanding> getStandings() {
        List<TeamStanding> standingsList = new ArrayList<>();

        // Database connection code (you need to replace these with your actual database connection details)
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Standings ORDER BY Points DESC");
             ResultSet resultSet = statement.executeQuery()) {

            // Iterate through the result set and create TeamStanding objects
            while (resultSet.next()) {
                String teamName = resultSet.getString("ClubName");
                String draw = String.valueOf(resultSet.getInt("Draw"));
                String lose = String.valueOf(resultSet.getInt("Lose"));
                String played = String.valueOf(resultSet.getInt("MatchesPlayed"));
                String points = String.valueOf(resultSet.getInt("Points"));
                String win = String.valueOf(resultSet.getInt("Win"));

                TeamStanding teamStanding = new TeamStanding(teamName, draw, lose, played, points, win);
                standingsList.add(teamStanding);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        }

        return standingsList;
    }
    public static void  updateTeamStandings() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE `standings` AS s\n" +
                             "SET\n" +
                             "    `MatchesPlayed` = COALESCE((SELECT COUNT(*) FROM `matches` m WHERE m.`HomeTeam` = s.`ClubName` OR m.`AwayTeam` = s.`ClubName`), 0),\n" +
                             "    `Win` = COALESCE((SELECT COUNT(*) FROM `matches` m WHERE (m.`HomeTeam` = s.`ClubName` OR m.`AwayTeam` = s.`ClubName`) AND m.`MatchCompleted` = 1 AND m.`HomeTeam` = s.`ClubName`), 0),\n" +
                             "    `Draw` = COALESCE((SELECT COUNT(*) FROM `matches` m WHERE (m.`HomeTeam` = s.`ClubName` OR m.`AwayTeam` = s.`ClubName`) AND m.`MatchCompleted` = 1 AND (m.`HomeTeam` = s.`ClubName` OR m.`AwayTeam` = s.`ClubName`)), 0),\n" +
                             "    `Lose` = COALESCE((SELECT COUNT(*) FROM `matches` m WHERE (m.`HomeTeam` = s.`ClubName` OR m.`AwayTeam` = s.`ClubName`) AND m.`MatchCompleted` = 1 AND m.`AwayTeam` = s.`ClubName`), 0),\n" +
                             "    `Points` = COALESCE((3 * `Win`) + (2 * `Draw`), 0);\n"
             )) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
