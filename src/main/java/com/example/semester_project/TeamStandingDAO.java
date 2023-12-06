package com.example.semester_project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamStandingDAO {
    private static final String JDBC_URL = "jdbc:mysql:///semesterproject";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public static List<TeamStanding> getTeamStandings() {
        List<TeamStanding> standings = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE `standings` AS s\n" +
                             "SET\n" +
                             "    `MatchesPlayed` = COALESCE((SELECT COUNT(*) FROM `matches` m WHERE m.`HomeTeam` = s.`ClubName` OR m.`AwayTeam` = s.`ClubName`), 0),\n" +
                             "    `Win` = COALESCE((SELECT COUNT(*) FROM `matches` m WHERE (m.`HomeTeam` = s.`ClubName` OR m.`AwayTeam` = s.`ClubName`) AND m.`MatchCompleted` = 1 AND (\n" +
                             "        (m.`HomeTeam` = s.`ClubName` AND m.`HomeGoals` > m.`AwayGoals`) OR\n" +
                             "        (m.`AwayTeam` = s.`ClubName` AND m.`AwayGoals` > m.`HomeGoals`)\n" +
                             "    )), 0),\n" +
                             "    `Draw` = COALESCE((SELECT COUNT(*) FROM `matches` m WHERE (m.`HomeTeam` = s.`ClubName` OR m.`AwayTeam` = s.`ClubName`) AND m.`MatchCompleted` = 1 AND m.`HomeGoals` = m.`AwayGoals`), 0),\n" +
                             "    `Lose` = COALESCE((SELECT COUNT(*) FROM `matches` m WHERE (m.`HomeTeam` = s.`ClubName` OR m.`AwayTeam` = s.`ClubName`) AND m.`MatchCompleted` = 1 AND (\n" +
                             "        (m.`HomeTeam` = s.`ClubName` AND m.`HomeGoals` < m.`AwayGoals`) OR\n" +
                             "        (m.`AwayTeam` = s.`ClubName` AND m.`AwayGoals` < m.`HomeGoals`)\n" +
                             "    )), 0),\n" +
                             "    `Points` = COALESCE((3 * `Win`) + (2 * `Draw`), 0)"
             );
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String teamName = resultSet.getString("ClubName");
                String draw = resultSet.getString("Draw");
                String lose = resultSet.getString("Lose");
                String played = resultSet.getString("MatchesPlayed");
                String points = resultSet.getString("Points");
                String win = resultSet.getString("Win");

                TeamStanding teamStanding = new TeamStanding(teamName, draw, lose, played, points, win);
                standings.add(teamStanding);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return standings;
    }
}

