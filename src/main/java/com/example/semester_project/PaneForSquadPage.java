package com.example.semester_project;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaneForSquadPage implements Initializable {

    @FXML
    private TableColumn<Player, String> PID;

    @FXML
    private TableColumn<Player, String> Name;

    @FXML
    private TableColumn<Player, String> Nationality;

    @FXML
    private TableColumn<Player, String> Age;

    @FXML
    private TableColumn<Player, String> ShirtNumber;

    @FXML
    private TableColumn<Player, String> Position;

    @FXML
    private TableColumn<Player, String> Date;  // Assuming this is the contract date column

    @FXML
    private TableView<Player> SquadManagement;
    @FXML
    private Button ReNew;
    private static final String HOVERED_BUTTON_STYLE =
            "-fx-background-color: #FC2680; -fx-text-fill: #331B50;";
    private static final String IDLE_BUTTON_STYLE =
            "-fx-background-color: #331B50; -fx-text-fill: white;";

    @FXML
    void Entered(MouseEvent event) {
        ReNew.setStyle(HOVERED_BUTTON_STYLE);
    }

    @FXML
    void Exited(MouseEvent event) {
        ReNew.setStyle(IDLE_BUTTON_STYLE);
    }

    @FXML
    void ReNew(ActionEvent event) {
        // Create a custom alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Renew Contract");
        alert.setHeaderText("Enter Player Details");

        // Create Player ID components
        Label playerIDLabel = new Label("Player ID:");
        TextField playerIDTextField = new TextField();
        playerIDTextField.setPromptText("Enter Player ID");

        // Create Contract Money components
        Label contractMoneyLabel = new Label("Contract Money:");
        TextField contractMoneyTextField = new TextField();
        contractMoneyTextField.setPromptText("Enter Contract Money");

        // Create ComboBox for money units
        ComboBox<String> moneyUnitsComboBox = new ComboBox<>();
        moneyUnitsComboBox.getItems().addAll("Thousands", "Millions", "Billions");
        moneyUnitsComboBox.setValue("Thousands");

        // Create Date Picker for Contract Date
        Label contractDateLabel = new Label("Contract Date:");
        DatePicker contractDatePicker = new DatePicker();

        // Set the content of the alert
        alert.getDialogPane().setContent(new ScrollPane() {{
            setHbarPolicy(ScrollBarPolicy.NEVER);
            setVbarPolicy(ScrollBarPolicy.ALWAYS);
            setFitToWidth(true);
            setContent(new VBox(10) {{
                getChildren().addAll(
                        playerIDLabel, playerIDTextField,
                        contractMoneyLabel, contractMoneyTextField, moneyUnitsComboBox,
                        contractDateLabel, contractDatePicker
                );
            }});
        }});

        // Add OK and Cancel buttons
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        // Show the alert and wait for user input
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == okButton) {
            // Validate Player ID and Contract Money (digits only)
            if (!playerIDTextField.getText().matches("\\d+") || !contractMoneyTextField.getText().matches("\\d+")) {
                showAlert("Invalid input. Player ID and Contract Money must be numeric.");
                return;
            }

            // Validate Contract Date
            try {
                LocalDate contractDate = contractDatePicker.getValue();

                // Check if the contract date is not in the past
                if (contractDate.isBefore(LocalDate.now())) {
                    showAlert("Invalid date. Contract date cannot be in the past.");
                    return;
                }

                // Additional validation logic for the contract date if needed
            } catch (Exception e) {
                showAlert("Invalid date format. Please enter a valid date.");
                return;
            }

            // Process the entered data (you can add your logic here)
            System.out.println("Player ID: " + playerIDTextField.getText());
            System.out.println("Contract Money: " + contractMoneyTextField.getText() + " " + moneyUnitsComboBox.getValue());
            System.out.println("Contract Date: " + contractDatePicker.getValue()); }
    }

    // Utility method to show an alert
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void OnClickingTable() {
        // Get the selected player from the table
        Player selectedPlayer = SquadManagement.getSelectionModel().getSelectedItem();

        if (selectedPlayer != null) {
            // Create a custom alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Player Information");
            alert.setHeaderText("Player Details");

            // Create labels for Player ID, Name, Nationality, and Contract Date
            Label playerIDLabel = new Label("Player ID: " + selectedPlayer.getPlayerID());
            Label nameLabel = new Label("Name: " + selectedPlayer.getName());
            Label nationalityLabel = new Label("Nationality: " + selectedPlayer.getNationality());
            Label contractDateLabel = new Label("Contract Date: " + selectedPlayer.getContractDate());

            // Create text fields for Position, Shirt Number, and Age
            TextField positionTextField = new TextField(selectedPlayer.getPosition());
            TextField shirtNumberTextField = new TextField(selectedPlayer.getShirtNumber());
            TextField ageTextField = new TextField(selectedPlayer.getAge());

            // Set the content of the alert
            alert.getDialogPane().setContent(new ScrollPane() {{
                setHbarPolicy(ScrollBarPolicy.NEVER);
                setVbarPolicy(ScrollBarPolicy.ALWAYS);
                setFitToWidth(true);
                setContent(new VBox(10) {{
                    getChildren().addAll(
                            playerIDLabel, nameLabel, nationalityLabel, contractDateLabel,
                            new Label("Position:"), positionTextField,
                            new Label("Shirt Number:"), shirtNumberTextField,
                            new Label("Age:"), ageTextField
                    );
                }});
            }});

            // Add OK and Cancel buttons
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);

            // Show the alert and wait for user input
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == okButton) {
                // Validate Position, Shirt Number, and Age
                if (!positionTextField.getText().matches("[a-zA-Z]+") ||
                        !shirtNumberTextField.getText().matches("\\d+") ||
                        !ageTextField.getText().matches("\\d+")) {
                    showAlert("Invalid input. Position should be alphabets, Shirt Number and Age must be numeric.");
                    return;
                }

                // Process the entered data (you can add your logic here)
                System.out.println("Position: " + positionTextField.getText());
                System.out.println("Shirt Number: " + shirtNumberTextField.getText());
                System.out.println("Age: " + ageTextField.getText());
                PlayerDAO.updatePlayer(
                        Integer.parseInt(selectedPlayer.getPlayerID()),
                        Integer.parseInt(shirtNumberTextField.getText()),
                        Integer.parseInt(ageTextField.getText()),
                        positionTextField.getText()
                );

                // Refresh the TableView to reflect the changes
                SquadManagement.getItems().setAll(PlayerDAO.getPlayerAttributes(1));
                SquadManagement.refresh();
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Assuming you have a ObservableList<Player> playersList containing your data
        ObservableList<Player> playersList = FXCollections.observableArrayList(PlayerDAO.getPlayerAttributes(1));

        // Set up cell value factories
        PID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlayerID()));
        Name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        Nationality.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNationality()));
        Age.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAge()));
        ShirtNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getShirtNumber()));
        Position.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));
        Date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContractDate()));

        // Set the items to the table
        SquadManagement.setItems(playersList);
    }
}
