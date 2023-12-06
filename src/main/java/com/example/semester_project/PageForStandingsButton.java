package com.example.semester_project;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PageForStandingsButton implements Initializable {

    @FXML
    private Button Back;
    @FXML
    TableView <TeamStanding> TeamStandings;

    @FXML
    private TableColumn<TeamStanding, String> Draw;

    @FXML
    private TableColumn<TeamStanding, String> Lose;

    @FXML
    private TableColumn<TeamStanding, String> Played;

    @FXML
    private TableColumn<TeamStanding, String> Points;

    @FXML
    private TableColumn<TeamStanding, String> TeamName;

    @FXML
    private TableColumn<TeamStanding, String> Win;

    private static final String HOVERED_BUTTON_STYLE =
            "-fx-background-color: #FC2680; -fx-text-fill: #331B50;";
    private static final String IDLE_BUTTON_STYLE =
            "-fx-background-color: #331B50; -fx-text-fill: white;";

    @FXML
    public void Back(ActionEvent e) throws IOException {
        FootBallClubManagementSystem.WhichPage='h';
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("Semester Project");
    }

    @FXML
    public void BackOnMouseEntered(MouseEvent e) {
        Back.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    public void BackOnMouseExit(MouseEvent e) {
        Back.setStyle(IDLE_BUTTON_STYLE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize your columns here (similar to your existing initialize method)
        Draw.setCellValueFactory(cellData -> cellData.getValue().drawProperty());
        Lose.setCellValueFactory(cellData -> cellData.getValue().loseProperty());
        Played.setCellValueFactory(cellData -> cellData.getValue().playedProperty());
        Points.setCellValueFactory(cellData -> cellData.getValue().pointsProperty());
        TeamName.setCellValueFactory(cellData -> cellData.getValue().teamNameProperty());
        Win.setCellValueFactory(cellData -> cellData.getValue().winProperty());

        // Example data
//        ObservableList<TeamStanding> data = FXCollections.observableArrayList(StandingsDAO.getStandings());
        if (StandingsDAO.getStandings() != null) {
            StandingsDAO.updateTeamStandings();
            ObservableList<TeamStanding> data = FXCollections.observableArrayList(StandingsDAO.getStandings());

            // Set the data to the table
            TeamStandings.setItems(data);
        } else {
            // Handle the case where the Players list is null (e.g., display an error message)
            System.err.println("Error: Standings  list is null.");
        }

        // Set the data to the table
        // Assuming you have a TableView named "TeamStandings" (similar to "PlayerStats" in the previous example)
//        TeamStandings.setItems(data);
    }
}


