package com.example.semester_project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Player {
    private StringProperty playerID;
    private StringProperty shirtNumber;
    private StringProperty position;
    private StringProperty contractDate;
    private StringProperty name;
    private StringProperty nationality;
    private StringProperty goals;
    private StringProperty assists;
    private StringProperty redCard;
    private StringProperty yellowCard;
    private StringProperty health;
    private StringProperty age;
    private StringProperty clubName;
    public Player() {
    }
    public Player(String playerID, String name, String clubName) {
        this.playerID = new SimpleStringProperty(playerID);
        this.name = new SimpleStringProperty(name);
        this.clubName = new SimpleStringProperty(clubName);
    }

    public Player(String playerID, String shirtNumber, String position, String contractDate, String name, String nationality, String age) {
        this.playerID = new SimpleStringProperty(playerID);
        this.shirtNumber = new SimpleStringProperty(shirtNumber);
        this.position = new SimpleStringProperty(position);
        this.contractDate = new SimpleStringProperty(contractDate);
        this.name = new SimpleStringProperty(name);
        this.nationality = new SimpleStringProperty(nationality);
        this.age = new SimpleStringProperty(age);
    }

    public Player(String name, String nationality, String goals, String assists, String red, String yellow, String health, String age) {
        this.name = new SimpleStringProperty(name);
        this.nationality = new SimpleStringProperty(nationality);
        this.goals = new SimpleStringProperty(goals);
        this.assists = new SimpleStringProperty(assists);
        this.redCard = new SimpleStringProperty(red);
        this.yellowCard = new SimpleStringProperty(yellow);
        this.health = new SimpleStringProperty(health);
        this.age = new SimpleStringProperty(age);
    }

    public String getPlayerID() {
        return playerID.get();
    }

    public String getShirtNumber() {
        return shirtNumber.get();
    }

    public String getPosition() {
        return position.get();
    }

    public String getContractDate() {
        return contractDate.get();
    }

    public String getName() {
        return name.get();
    }

    public String getNationality() {
        return nationality.get();
    }

    public String getGoals() {
        return goals.get();
    }

    public String getAssists() {
        return assists.get();
    }

    public String getRedCard() {
        return redCard.get();
    }

    public String getYellowCard() {
        return yellowCard.get();
    }

    public String getHealth() {
        return health.get();
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty playerIDProperty() {
        return playerID;
    }

    public StringProperty shirtNumberProperty() {
        return shirtNumber;
    }

    public StringProperty positionProperty() {
        return position;
    }

    public StringProperty contractDateProperty() {
        return contractDate;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }

    public StringProperty goalsProperty() {
        return goals;
    }

    public StringProperty assistsProperty() {
        return assists;
    }

    public StringProperty redCardProperty() {
        return redCard;
    }

    public StringProperty yellowCardProperty() {
        return yellowCard;
    }

    public StringProperty healthProperty() {
        return health;
    }

    public StringProperty ageProperty() {
        return age;
    }
    public String getClubName() {
        return clubName.get();
    }

    public StringProperty clubNameProperty() {
        return clubName;
    }
}
