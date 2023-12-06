package com.example.semester_project;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Date;

public class MatchStats {
    private final SimpleObjectProperty<Date> date = new SimpleObjectProperty<>();
    private final SimpleStringProperty team = new SimpleStringProperty();
    private final SimpleIntegerProperty redHome = new SimpleIntegerProperty();
    private final SimpleIntegerProperty yellowHome = new SimpleIntegerProperty();
    private final SimpleIntegerProperty goalsHome = new SimpleIntegerProperty();
    private final SimpleIntegerProperty redAway = new SimpleIntegerProperty();
    private final SimpleIntegerProperty yellowAway = new SimpleIntegerProperty();
    private final SimpleIntegerProperty goalsAway = new SimpleIntegerProperty();

    public MatchStats() {
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public String getTeam() {
        return team.get();
    }

    public SimpleStringProperty teamProperty() {
        return team;
    }

    public void setTeam(String team) {
        this.team.set(team);
    }

    public int getRedHome() {
        return redHome.get();
    }

    public SimpleIntegerProperty redHomeProperty() {
        return redHome;
    }

    public void setRedHome(int redHome) {
        this.redHome.set(redHome);
    }

    public int getYellowHome() {
        return yellowHome.get();
    }

    public SimpleIntegerProperty yellowHomeProperty() {
        return yellowHome;
    }

    public void setYellowHome(int yellowHome) {
        this.yellowHome.set(yellowHome);
    }

    public int getGoalsHome() {
        return goalsHome.get();
    }

    public SimpleIntegerProperty goalsHomeProperty() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome.set(goalsHome);
    }

    public int getRedAway() {
        return redAway.get();
    }

    public SimpleIntegerProperty redAwayProperty() {
        return redAway;
    }

    public void setRedAway(int redAway) {
        this.redAway.set(redAway);
    }

    public int getYellowAway() {
        return yellowAway.get();
    }

    public SimpleIntegerProperty yellowAwayProperty() {
        return yellowAway;
    }

    public void setYellowAway(int yellowAway) {
        this.yellowAway.set(yellowAway);
    }

    public int getGoalsAway() {
        return goalsAway.get();
    }

    public SimpleIntegerProperty goalsAwayProperty() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway.set(goalsAway);
    }
}
