package com.example.semester_project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeamStanding {
    private final StringProperty draw;
    private final StringProperty lose;
    private final StringProperty played;
    private final StringProperty points;
    private final StringProperty teamName;
    private final StringProperty win;

    public TeamStanding(String teamName, String draw, String lose, String played, String points, String win) {
        this.teamName = new SimpleStringProperty(teamName);
        this.draw = new SimpleStringProperty(draw);
        this.lose = new SimpleStringProperty(lose);
        this.played = new SimpleStringProperty(played);
        this.points = new SimpleStringProperty(points);
        this.win = new SimpleStringProperty(win);
    }

    // Getters for properties
    public StringProperty drawProperty() {
        return draw;
    }

    public StringProperty loseProperty() {
        return lose;
    }

    public StringProperty playedProperty() {
        return played;
    }

    public StringProperty pointsProperty() {
        return points;
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public StringProperty winProperty() {
        return win;
    }
}
