package com.example.semester_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PageForTransferHubButton implements Initializable {
    @FXML
    private DatePicker SelectContractDate;


    @FXML
    private Button Back;

    @FXML
    private ComboBox<String> FromTeam;

    @FXML
    private Button InConfirm;

    @FXML
    private TextField InMoney;

    @FXML
    private TextField InPlayer;

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
    private ComboBox<String> OutTransfer;
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
        String in = InPlayer.getText();
        String mon=InMoney.getText();
        LocalDate currentDate = LocalDate.now();
        if(PlayerDAO.isPlayerExistsForOtherClubs(Integer.parseInt(InPlayer.getText()))){
            System.out.println("Okay Well Done");
            if(SelectContractDate.getValue().isAfter(currentDate)){
            if(SelectContractDate.getValue()!=null&&in.matches("\\d+")&&mon.matches("\\d+")&&FromTeam.getValue()!=null && !InPlayer.getText().isEmpty() && !InMoney.getText().isEmpty()){
                System.out.println("Checked");
            }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Fill in all the Options");
                alert.showAndWait();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Player Not Available for Player ID = "+InPlayer.getText());
            alert.showAndWait();
        }
    }

    @FXML
    void OutConfirm(ActionEvent event) {
        String out=OutName.getText();
        String mon=OutMoney.getText();
        if(PlayerDAO.isPlayerExists(Integer.parseInt(OutName.getText()))){
            System.out.println("Okay Well Done");
            if(out.matches("\\d+")&&mon.matches("\\d+")&&OutTransfer.getValue()!=null && !OutName.getText().isEmpty() && !OutMoney.getText().isEmpty()){
                System.out.println("Checked");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Fill in all the Options");
                alert.showAndWait();
            }
        }
        else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Player Not Available for Player ID = "+OutName.getText());
        alert.showAndWait();
        }
    }
    @FXML
    void InPlayer(ActionEvent event) {

    }
    @FXML
    void OutPlayer(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> Clubs= FXCollections.observableArrayList(PlayerDAO.getAllTeamsExceptTottenham());
        FromTeam.getItems().addAll(Clubs);
        OutTransfer.getItems().addAll(Clubs);
    }
}
