package com.example.semester_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PageForTransferHubButton {

    @FXML
    private Button Back;

    @FXML
    private ComboBox<?> FromTeam;

    @FXML
    private Button InConfirm;

    @FXML
    private TextField InMoney;

    @FXML
    private ComboBox<?> MoneyUnitIn;

    @FXML
    private ComboBox<?> MoneyUnitOut;

    @FXML
    private Button OutConfirm;

    @FXML
    private TextField OutMoney;

    @FXML
    private TextField OutName;

    @FXML
    private Label OutPlayerID;

    @FXML
    private Label OutPlayerID1;

    @FXML
    private ComboBox<?> OutTransfer;

    @FXML
    private Label PlayerName;

    @FXML
    private Label PlayerPostion;

    @FXML
    private ComboBox<?> ShortList;

    private static final String HOVERED_BUTTON_STYLE =
            "-fx-background-color: #FC2680; -fx-text-fill: #331B50;";
    private static final String IDLE_BUTTON_STYLE =
            "-fx-background-color: #331B50; -fx-text-fill: white;";
    @FXML
    void Back(ActionEvent e) throws IOException {
        FootBallClubManagementSystem.WhichPage='t';
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
    @FXML
    void InConfirm(ActionEvent event) {

    }

    @FXML
    void OutConfirm(ActionEvent event) {

    }
}
