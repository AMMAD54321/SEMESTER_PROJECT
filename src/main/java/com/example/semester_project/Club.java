package com.example.semester_project;

import java.sql.*;
import java.util.List;

public class Club {

    static List<String> clubName=ClubDAO.getClubs();


    public Club() throws SQLException {

    }

    public Club(List<String> clubName) throws SQLException {
        this.clubName = clubName;
    }
}
