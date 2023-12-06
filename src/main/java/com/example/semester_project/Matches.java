package com.example.semester_project;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Matches {
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<>();
    private final SimpleIntegerProperty matchId = new SimpleIntegerProperty();
    private final StringProperty home = new SimpleStringProperty();
    private final StringProperty away = new SimpleStringProperty();

    // Constructors, getters, and setters...

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public int getMatchId() {
        return matchId.get();
    }

    public SimpleIntegerProperty matchIdProperty() {
        return matchId;
    }

    public String getHome() {
        return home.get();
    }

    public StringProperty homeProperty() {
        return home;
    }

    public String getAway() {
        return away.get();
    }

    public StringProperty awayProperty() {
        return away;
    }
}

