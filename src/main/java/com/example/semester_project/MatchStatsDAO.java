package com.example.semester_project;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchStatsDAO {
    private static Connection connection;

    private static final String URL = "jdbc:mysql:///semesterproject";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    public static List<MatchStats> getMatchStats() throws SQLException {
        List<MatchStats> matchStatsList = new ArrayList<>();
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT\n" +
                        "    m.Date,\n" +
                        "    CONCAT(m.HomeTeam, ' vs ', m.AwayTeam) AS Team,\n" +
                        "    SUM(CASE WHEN e.Event = 'Red Card' AND p.ClubName = m.HomeTeam THEN 1 ELSE 0 END) AS RedHome,\n" +
                        "    SUM(CASE WHEN e.Event = 'Yellow Card' AND p.ClubName = m.HomeTeam THEN 1 ELSE 0 END) AS YellowHome,\n" +
                        "    SUM(CASE WHEN e.Event = 'Goal' AND p.ClubName = m.HomeTeam THEN 1 ELSE 0 END) AS GoalsHome,\n" +
                        "    SUM(CASE WHEN e.Event = 'Red Card' AND p.ClubName = m.AwayTeam THEN 1 ELSE 0 END) AS RedAway,\n" +
                        "    SUM(CASE WHEN e.Event = 'Yellow Card' AND p.ClubName = m.AwayTeam THEN 1 ELSE 0 END) AS YellowAway,\n" +
                        "    SUM(CASE WHEN e.Event = 'Goal' AND p.ClubName = m.AwayTeam THEN 1 ELSE 0 END) AS GoalsAway\n" +
                        "FROM\n" +
                        "    matches m\n" +
                        "    LEFT JOIN matchevent e ON m.MatchID = e.MatchID\n" +
                        "    LEFT JOIN player p ON e.PlayerID = p.PlayerID\n" +
                        "GROUP BY\n" +
                        "    m.MatchID, m.Date, m.HomeTeam, m.AwayTeam\n" +
                        "ORDER BY\n" +
                        "    m.Date;\n")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MatchStats matchStats = new MatchStats();
                    matchStats.setDate(resultSet.getDate("Date"));
                    matchStats.setTeam(resultSet.getString("Team"));
                    matchStats.setRedHome(resultSet.getInt("RedHome"));
                    matchStats.setYellowHome(resultSet.getInt("YellowHome"));
                    matchStats.setGoalsHome(resultSet.getInt("GoalsHome"));
                    matchStats.setRedAway(resultSet.getInt("RedAway"));
                    matchStats.setYellowAway(resultSet.getInt("YellowAway"));
                    matchStats.setGoalsAway(resultSet.getInt("GoalsAway"));

                    matchStatsList.add(matchStats);
                }
            }
        }

        return matchStatsList;
    }
}

