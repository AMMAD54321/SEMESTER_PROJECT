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

public class PageForPlayerStats implements Initializable {

    @FXML
    private Button Back;

    @FXML
    private TableView<Player> PlayerStats;

    @FXML
    private TableColumn<Player, String> name;

    @FXML
    private TableColumn<Player, String> nationality;

    @FXML
    private TableColumn<Player, String> goals;

    @FXML
    private TableColumn<Player, String> assists;

    @FXML
    private TableColumn<Player, String> RedCard;

    @FXML
    private TableColumn<Player, String> YellowCard;

    @FXML
    private TableColumn<Player, String> PhysicalHealth;

    @FXML
    private TableColumn<Player, String> age;

    @FXML
    void Back(ActionEvent event) throws IOException {
        FootBallClubManagementSystem.WhichPage='h';
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) Back.getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("My Semester Project");
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
        // Initialize your columns here (similar to your existing initialize method)
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nationality.setCellValueFactory(cellData -> cellData.getValue().nationalityProperty());
        goals.setCellValueFactory(cellData -> cellData.getValue().goalsProperty());
        assists.setCellValueFactory(cellData -> cellData.getValue().assistsProperty());
        RedCard.setCellValueFactory(cellData -> cellData.getValue().redCardProperty());
        YellowCard.setCellValueFactory(cellData -> cellData.getValue().yellowCardProperty());
        PhysicalHealth.setCellValueFactory(cellData -> cellData.getValue().healthProperty());
        age.setCellValueFactory(cellData -> cellData.getValue().ageProperty());

        // Example data
        if (PlayerDAO.getPlayerAttributes() != null) {
            ObservableList<Player> data = FXCollections.observableArrayList(PlayerDAO.getPlayerAttributes());

            // Set the data to the table
            PlayerStats.setItems(data);
        } else {
            // Handle the case where the Players list is null (e.g., display an error message)
            System.err.println("Error: Player.Players list is null.");
        }
    }


    private static final String HOVERED_BUTTON_STYLE =
            "-fx-background-color: #FC2680; -fx-text-fill: #331B50;";
    private static final String IDLE_BUTTON_STYLE =
            "-fx-background-color: #331B50; -fx-text-fill: white;";
}



