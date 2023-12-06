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
import java.util.Optional;
import java.util.ResourceBundle;

public class PageForSearchPlayersButton implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private TableView<Player> SearchedPlayers;

    @FXML
    private TableColumn<Player, String> PlayerID;

    @FXML
    private TableColumn<Player, String> Name;

    @FXML
    private TableColumn<Player, String> ClubName;



    @FXML
    private Button Search;

    @FXML
    private TextField SearchPlayer;
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
    void Search(ActionEvent event) {
        if (SearchPlayer == null || SearchPlayer.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Entered Nothing");
            alert.showAndWait();
        } else {
            ObservableList<Player> players = FXCollections.observableArrayList(PlayerDAO.getPlayersByClub(SearchPlayer.getText(), 1));
            // Clear the existing items and add the new items
            SearchedPlayers.getItems().clear();
            SearchedPlayers.getItems().addAll(players);
        }
    }


    @FXML
    void OnClicked(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmation");
        alert.setContentText("Do you really want to shortlist the player?");
        Player player=SearchedPlayers.getSelectionModel().getSelectedItem();
        Optional<ButtonType> result = alert.showAndWait();

        // Check if the user clicked OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Your code to execute when the user clicks OK
            System.out.println("User clicked OK. Shortlisting player...");
            PlayerDAO.Shortlisted.add(Integer.parseInt(player.getPlayerID()));
            // Add your logic here
        } else {
            // Your code to execute when the user clicks Cancel or closes the dialog
            System.out.println("User clicked Cancel or closed the dialog.");
            // Add your logic here
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        PlayerID.setCellValueFactory(cellDate->cellDate.getValue().playerIDProperty());
        ClubName.setCellValueFactory(cellDate->cellDate.getValue().clubNameProperty());
        ObservableList<Player> players= FXCollections.observableArrayList(PlayerDAO.getAllPlayers());
        SearchedPlayers.setItems(players);

    }
}
