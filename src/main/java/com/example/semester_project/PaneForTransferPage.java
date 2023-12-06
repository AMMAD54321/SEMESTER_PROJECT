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

public class PaneForTransferPage {
    @FXML
    Button SearchPlayers;
    @FXML
    Button TransfersHub;
    @FXML
    Button TransfersHistory;

    private static final String HOVERED_BUTTON_STYLE =
            "-fx-background-color: #FC2680; -fx-text-fill: #331B50;";
    private static final String IDLE_BUTTON_STYLE =
            "-fx-background-color: #331B50; -fx-text-fill: white;";


    @FXML
    public void SearchPlayers(ActionEvent e) throws IOException {
        System.out.println("Search Players");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageForSearchPlayersButton.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("Semester Project");
    }

    @FXML
    public void SearchPlayersButtonOnMouseEntered(MouseEvent e) {
        SearchPlayers.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    public void SearchPlayersButtonOnMouseExited(MouseEvent e) {
        SearchPlayers.setStyle(IDLE_BUTTON_STYLE);
    }

    @FXML
    public void TransfersHub(ActionEvent e) throws IOException {
        System.out.println("Transfers Hub");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageForTransferHubButton.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("Semester Project");
    }

    @FXML
    public void TransfersHubButtonOnMouseEntered(MouseEvent e) {
        TransfersHub.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    public void TransfersHubButtonOnMouseExited(MouseEvent e) {
        TransfersHub.setStyle(IDLE_BUTTON_STYLE);
    }

    @FXML
    public void TransfersHistory(ActionEvent e) throws IOException {
        System.out.println("Transfers History");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PageForTransferHistoryButton.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("Semester Project");
    }

    @FXML
    public void TransfersHistoryButtonOnMouseEntered(MouseEvent e) {
        TransfersHistory.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    public void TransfersHistoryButtonOnMouseExited(MouseEvent e) {
        TransfersHistory.setStyle(IDLE_BUTTON_STYLE);
    }
}
