package com.example.semester_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PaneForHomePage {
    @FXML
    Button Standings;
    @FXML
    Button Schedule;
    @FXML
    Button PlayerStats;
    @FXML
    Button MatchStats;

    private static final String HOVERED_BUTTON_STYLE =
            "-fx-background-color: #FC2680; -fx-text-fill: #331B50;";
    private static final String IDLE_BUTTON_STYLE =
            "-fx-background-color: #331B50; -fx-text-fill: white;";

    @FXML
    public void StandingsButton(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageForStandingsButton.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("Semester Project");
    }

    @FXML
    public void StandingsButtonOnMouseEntered(MouseEvent e) {
        Standings.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    public void StandingsButtonOnMouseExited(MouseEvent e) {
        Standings.setStyle(IDLE_BUTTON_STYLE);
    }

    @FXML
    public void ScheduleButton(ActionEvent e) throws IOException {
        System.out.println("Schedule");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageForScheduleButton.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("Semester Project");
    }

    @FXML
    public void ScheduleButtonOnMouseEntered(MouseEvent e) {
        Schedule.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    public void ScheduleButtonOnMouseExited(MouseEvent e) {
        Schedule.setStyle(IDLE_BUTTON_STYLE);
    }

    @FXML
    public void PlayerStatsButton(ActionEvent e) throws IOException {
        System.out.println("Players");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageForPlayerStats.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("Semester Project");
    }

    @FXML
    public void PlayerStatsButtonOnMouseEntered(MouseEvent e) {
        PlayerStats.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    public void PlayerStatsButtonOnMouseExited(MouseEvent e) {
        PlayerStats.setStyle(IDLE_BUTTON_STYLE);
    }

    @FXML
    public void MatchStatsButton(ActionEvent e) throws IOException {
        System.out.println("Match");
        System.out.println("Players");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageForMatchStats.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("Semester Project");
    }

    @FXML
    public void MatchStatsButtonOnMouseEntered(MouseEvent e) {
        MatchStats.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    public void MatchStatsButtonOnMouseExited(MouseEvent e) {
        MatchStats.setStyle(IDLE_BUTTON_STYLE);

    }
}
