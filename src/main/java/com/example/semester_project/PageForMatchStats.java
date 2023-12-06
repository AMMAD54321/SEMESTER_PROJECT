package com.example.semester_project;

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
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class PageForMatchStats implements Initializable {

    @FXML
    private TableView<MatchStats> matchStatsTable;

    @FXML
    private TableColumn<MatchStats, Date> dateColumn;

    @FXML
    private TableColumn<MatchStats, String> teamColumn;

    @FXML
    private TableColumn<MatchStats, Integer> redHomeColumn;

    @FXML
    private TableColumn<MatchStats, Integer> yellowHomeColumn;

    @FXML
    private TableColumn<MatchStats, Integer> goalsHomeColumn;

    @FXML
    private TableColumn<MatchStats, Integer> redAwayColumn;

    @FXML
    private TableColumn<MatchStats, Integer> yellowAwayColumn;

    @FXML
    private TableColumn<MatchStats, Integer> goalsAwayColumn;


    @FXML
    private Button Back;


    private static final String HOVERED_BUTTON_STYLE =
            "-fx-background-color: #FC2680; -fx-text-fill: #331B50;";
    private static final String IDLE_BUTTON_STYLE =
            "-fx-background-color: #331B50; -fx-text-fill: white;";

    @FXML
    void Back(ActionEvent e) throws IOException {
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
    void BackOnMouseEntered(MouseEvent event) {
        Back.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    void BackOnMouseExit(MouseEvent event) {
        Back.setStyle(IDLE_BUTTON_STYLE);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Bind columns to MatchStats properties
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        teamColumn.setCellValueFactory(cellData -> cellData.getValue().teamProperty());
        redHomeColumn.setCellValueFactory(cellData -> cellData.getValue().redHomeProperty().asObject());
        yellowHomeColumn.setCellValueFactory(cellData -> cellData.getValue().yellowHomeProperty().asObject());
        goalsHomeColumn.setCellValueFactory(cellData -> cellData.getValue().goalsHomeProperty().asObject());
        redAwayColumn.setCellValueFactory(cellData -> cellData.getValue().redAwayProperty().asObject());
        yellowAwayColumn.setCellValueFactory(cellData -> cellData.getValue().yellowAwayProperty().asObject());
        goalsAwayColumn.setCellValueFactory(cellData -> cellData.getValue().goalsAwayProperty().asObject());

        // Load data into the table
        loadData();
    }
    private void loadData() {
        try {
            ObservableList<MatchStats> matchStatsList = FXCollections.observableArrayList(MatchStatsDAO.getMatchStats());
            matchStatsTable.setItems(matchStatsList);
        } catch (SQLException e) {
            // Handle exceptions
        }
    }
}
