package com.example.semester_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PageForScheduleButton implements Initializable {
    @FXML
    Button AddOur;
    @FXML
    Button AddOther;
    @FXML
    private TableColumn<Matches, Integer> OtherMatchID;

    @FXML
    private TableColumn<Matches, String> OtherHome;

    @FXML
    private TableColumn<Matches, String> OtherAway;

    @FXML
    private TableColumn<Matches, LocalDate> OtherDate;

    @FXML
    private TableView<Matches> OtherTable;

    @FXML
    private TableColumn<Matches, Integer> OurMatchID;

    @FXML
    private TableColumn<Matches, String> OurHome;

    @FXML
    private TableColumn<Matches, String> OurAway;

    @FXML
    private TableColumn<Matches, LocalDate> OurDate;

    @FXML
    private TableView<Matches> OurTableView;


    @FXML
    Button Back;
    private static final String HOVERED_BUTTON_STYLE =
            "-fx-background-color: #FC2680; -fx-text-fill: #331B50;";
    private static final String IDLE_BUTTON_STYLE =
            "-fx-background-color: #331B50; -fx-text-fill: white;";


    @FXML
    public void Back(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Get the stage from the event source
        Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();

        // Set the new scene to the existing stage
        stage.setScene(scene);
        stage.setTitle("Semester Project");
    }
    public void BackOnMouseEntered(MouseEvent e){
        Back.setStyle(HOVERED_BUTTON_STYLE);
    }
    public void BackOnMouseExit(MouseEvent e){
        Back.setStyle(IDLE_BUTTON_STYLE);
    }
    @FXML
    public void AddOur(ActionEvent e) throws SQLException {
        final LocalDate[] selectedDate = {null};
        final String[] selectedTeamType = {null};
        final String[] selectedOpposingTeam = {null};

        // Set up the radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton homeTeamRadioButton = new RadioButton("Home Team");
        homeTeamRadioButton.setToggleGroup(toggleGroup);
        homeTeamRadioButton.setSelected(true);
        RadioButton awayTeamRadioButton = new RadioButton("Away Team");
        awayTeamRadioButton.setToggleGroup(toggleGroup);

        // Set up labels for instructions
        Label teamTypeLabel = new Label("Select Team Type:");
        Label opposingTeamLabel = new Label("Select Opposing Team:");
        Label dateLabel = new Label("Select Date:");

        // Set up the combo box for opposing teams
        ComboBox<String> opposingTeamsComboBox = new ComboBox<>();
        List<String> clubName = ClubDAO.getClubs(1);
        for (int i = 0; i < clubName.size(); i++) {
            opposingTeamsComboBox.getItems().add(clubName.get(i));
        }

        // Set up the date picker
        DatePicker datePicker = new DatePicker();
        datePicker.setEditable(false);

        // Set up the layout
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));

        vbox.getChildren().addAll(teamTypeLabel, homeTeamRadioButton, awayTeamRadioButton,
                opposingTeamLabel, opposingTeamsComboBox, dateLabel, datePicker);

        hbox.getChildren().add(vbox);

        // Create a custom dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Add Match");

        // Add OK button
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.getDialogPane().setContent(hbox);

        // Handle the result
        Optional<ButtonType> result = dialog.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == okButtonType) {
                // Check if all required fields are filled
                if (toggleGroup.getSelectedToggle() == null || opposingTeamsComboBox.getValue() == null || datePicker.getValue() == null) {
                    Alert validationAlert = new Alert(Alert.AlertType.WARNING);
                    validationAlert.setTitle("Validation Error");
                    validationAlert.setHeaderText(null);
                    validationAlert.setContentText("Please fill in all the required fields.");
                    validationAlert.showAndWait();
                } else {
                    try {
                        // Try to parse the selected date
                        LocalDate currentDate = LocalDate.now();
                        selectedDate[0] = datePicker.getValue();

                        if (selectedDate[0].isBefore(currentDate)) {
                            // Show an alert for an invalid date
                            Alert invalidDateAlert = new Alert(Alert.AlertType.WARNING);
                            invalidDateAlert.setTitle("Validation Error");
                            invalidDateAlert.setHeaderText(null);
                            invalidDateAlert.setContentText("Please select a future date.");
                            invalidDateAlert.showAndWait();
                        } else {
                            // Proceed with processing the data
                            selectedTeamType[0] = toggleGroup.getSelectedToggle() == homeTeamRadioButton ? "Home Team" : "Away Team";
                            selectedOpposingTeam[0] = opposingTeamsComboBox.getValue();
                            String formattedDate = selectedDate[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                            // Print the selected values using println
                            System.out.println("Selected Team Type: " + selectedTeamType[0]);
                            System.out.println("Selected Opposing Team: " + selectedOpposingTeam[0]);
                            System.out.println("Selected Date: " + formattedDate);

                            // Do something with the selected values (e.g., show another alert)
                            Alert resultAlert = new Alert(Alert.AlertType.INFORMATION);
                            resultAlert.setTitle("Match Information");
                            resultAlert.setHeaderText("Match Details");
                            resultAlert.setContentText("Selected Team: " + selectedTeamType[0] +
                                    "\nOpposing Team: " + selectedOpposingTeam[0] +
                                    "\nDate: " + formattedDate);
                            resultAlert.showAndWait();
                        }
                    } catch (DateTimeParseException ex) {
                        // Show an alert for an invalid date format
                        Alert invalidDateAlert = new Alert(Alert.AlertType.WARNING);
                        invalidDateAlert.setTitle("Validation Error");
                        invalidDateAlert.setHeaderText(null);
                        invalidDateAlert.setContentText("Invalid date format. Please select a valid date.");
                        invalidDateAlert.showAndWait();
                    }
                }
            }
        });

        // Now you can use the selected values outside the AddOur method
        // For example, you can use selectedTeamType, selectedOpposingTeam, and selectedDate in other parts of your project.
        if (selectedTeamType[0] != null && selectedOpposingTeam[0] != null && selectedDate[0] != null) {
            System.out.println("Selected Team Type outside the method: " + selectedTeamType[0]);
            System.out.println("Selected Opposing Team outside the method: " + selectedOpposingTeam[0]);
            System.out.println("Selected Date outside the method: " + selectedDate[0]);
            if(selectedTeamType[0].equals("Away Team")){
               // our.getItems().add(selectedDate[0]+"     "+selectedOpposingTeam[0]+" VS "+"Tottenham Hotspur");
               setAddOurTable();
                MatchesDAO.insertMatch(selectedDate[0],selectedOpposingTeam[0],"Tottenham Hotspur");
            }
            if (selectedTeamType[0].equals("Home Team")){
               // our.getItems().add(selectedDate[0]+"     "+"Tottenham Hotspur"+" VS "+selectedOpposingTeam[0]);
                MatchesDAO.insertMatch(selectedDate[0],"Tottenham Hotspur",selectedOpposingTeam[0]);
            }
        }
    }


    @FXML
    public void AddOther(ActionEvent e) {

        // Set up the combo boxes for selecting Home and Away teams
        ComboBox<String> homeTeamComboBox = new ComboBox<>();
       // homeTeamComboBox.getItems().addAll("Team A", "Team B", "Team C");  // Add your actual team names
        List<String> clubName = ClubDAO.getClubs(1);
        for (int i = 0; i < clubName.size(); i++) {
            homeTeamComboBox.getItems().add(clubName.get(i));
        }

        ComboBox<String> awayTeamComboBox = new ComboBox<>();
        //awayTeamComboBox.getItems().addAll("Team X", "Team Y", "Team Z");  // Add your actual team names
        for (int i = 0; i < clubName.size(); i++) {
            awayTeamComboBox.getItems().add(clubName.get(i));
        }
        // Set up the date picker
        DatePicker datePicker = new DatePicker();
        datePicker.setEditable(false);

        // Set up labels for instructions
        Label homeTeamLabel = new Label("Select Home Team:");
        Label awayTeamLabel = new Label("Select Away Team:");
        Label dateLabel = new Label("Select Date:");

        // Set up the layout
        VBox vbox = new VBox(10);
        HBox hbox = new HBox(10);

        vbox.getChildren().addAll(homeTeamLabel, homeTeamComboBox, awayTeamLabel, awayTeamComboBox, dateLabel, datePicker);
        hbox.getChildren().add(vbox);

        // Create a custom dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Add Match");

        // Add OK button
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        dialog.getDialogPane().setContent(hbox);

        // Handle the result
        dialog.setResultConverter(buttonType -> {
            if (buttonType == okButtonType) {
                return buttonType;
            }
            return null;
        });

        Optional<ButtonType> result = dialog.showAndWait();
        String[] selectedHomeTeam = {null};
        String[] selectedAwayTeam = {null};
        LocalDate[] selectedDate = {null};

        result.ifPresent(buttonType -> {
            if (buttonType == okButtonType) {
                // Check if all required fields are filled
                if (homeTeamComboBox.getValue() == null || awayTeamComboBox.getValue() == null || datePicker.getValue() == null) {
                    Alert validationAlert = new Alert(Alert.AlertType.WARNING);
                    validationAlert.setTitle("Validation Error");
                    validationAlert.setHeaderText(null);
                    validationAlert.setContentText("Please fill in all the required fields.");
                    validationAlert.showAndWait();
                } else {
                    // Check if the home and away teams are different
                    selectedHomeTeam[0] = homeTeamComboBox.getValue();
                    selectedAwayTeam[0] = awayTeamComboBox.getValue();

                    if (selectedHomeTeam[0].equals(selectedAwayTeam[0])) {
                        Alert teamErrorAlert = new Alert(Alert.AlertType.WARNING);
                        teamErrorAlert.setTitle("Validation Error");
                        teamErrorAlert.setHeaderText(null);
                        teamErrorAlert.setContentText("Please select different teams for home and away.");
                        teamErrorAlert.showAndWait();
                    } else {
                        try {
                            // Rest of your existing code
                            LocalDate currentDate = LocalDate.now();
                            selectedDate[0] = datePicker.getValue();

                            if (selectedDate[0].isBefore(currentDate)) {
                                // Show an alert for an invalid date
                                // Rest of your existing code
                            } else {
                                // Proceed with processing the data
                                // Rest of your existing code
                            }
                        } catch (DateTimeParseException ex) {
                            // Show an alert for an invalid date format
                            // Rest of your existing code
                        }
                    }
                }
            }
        });

        // Now you can use the selected variables outside the block
        if (selectedHomeTeam[0] != null && selectedAwayTeam[0] != null && selectedDate[0] != null) {
            // Use the selectedHomeTeam, selectedAwayTeam, and selectedDate as needed
            System.out.println("Selected Home Team: " + selectedHomeTeam[0]);
            System.out.println("Selected Away Team: " + selectedAwayTeam[0]);
            System.out.println("Selected Date: " + selectedDate[0]);
           // other.getItems().add(selectedDate[0]+"     "+selectedHomeTeam[0]+" VS "+selectedAwayTeam[0]);
            OtherTable.refresh();
            setAddOther();
            MatchesDAO.insertMatch(selectedDate[0],selectedHomeTeam[0],selectedAwayTeam[0]);
        }

    }

    private void showItemAlert(String itemName) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Item Information");
        alert.setHeaderText("Clicked item:");
        alert.setContentText(itemName);
        alert.showAndWait();
    }
    @FXML
    public void AddOurMouseEntered(MouseEvent e){
        AddOur.setStyle(HOVERED_BUTTON_STYLE);
    }
    @FXML
    public void AddOtherMouseEntered(MouseEvent e){
        AddOther.setStyle(HOVERED_BUTTON_STYLE);
    }
    @FXML
    public void AddOurMouseExited(MouseEvent e){
        AddOur.setStyle(IDLE_BUTTON_STYLE);
    }
    @FXML
    public void AddOtherMouseExited(MouseEvent e){
        AddOther.setStyle(IDLE_BUTTON_STYLE);
    }




    @FXML
    void OtherMouseClicked(MouseEvent event) {
        Matches matches = OtherTable.getSelectionModel().getSelectedItem();

        if (matches != null) {
            if (!MatchesDAO.isMatchIncomplete(matches.getMatchId())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Can't Enter More Information");
                alert.showAndWait();
            } else {
                showCustomDialog(1);
            }
        } else {
            // Handle the case where no match is selected
            System.out.println("No match selected.");
        }
    }

    @FXML
    void OurMouseClicked(MouseEvent event) {
        Matches matches = OurTableView.getSelectionModel().getSelectedItem();

        if (matches != null) {
            if (!MatchesDAO.isMatchIncomplete(matches.getMatchId())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Can't Enter More Information");
                alert.showAndWait();
            } else {
                showCustomDialog(2);
            }
        } else {
            // Handle the case where no match is selected
            System.out.println("No match selected.");
        }
    }


    private void showCustomDialog(int i) {
        Matches matches;  // Initialize matches to null

        if (i == 1) {
            matches = OtherTable.getSelectionModel().getSelectedItem();
        } else if (i == 2) {
            matches = OurTableView.getSelectionModel().getSelectedItem();
        } else {
            matches = null;
        }

        final Matches selectedMatch = matches;  // Use final variable

        // Perform null check for matches
        if (matches == null) {
            // Handle the case where no match is selected
            return;
        }

        // Create the custom dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Custom Dialog");

        // Set up the layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Radio buttons for selecting team type
        ToggleGroup teamToggleGroup = new ToggleGroup();
        RadioButton homeRadioButton = new RadioButton("Home");
        RadioButton awayRadioButton = new RadioButton("Away");
        homeRadioButton.setToggleGroup(teamToggleGroup);
        awayRadioButton.setToggleGroup(teamToggleGroup);

        // Labels and ComboBox for selecting players
        Label selectTeamLabel = new Label("Select Team:");
        Label selectPlayerLabel = new Label("Select Player:");
        ComboBox<String> playerComboBox = new ComboBox<>();

        // ComboBox for match events
        Label matchEventsLabel = new Label("Match Events:");
        ComboBox<String> matchEventsComboBox = new ComboBox<>();
        matchEventsComboBox.getItems().addAll("Goal", "Assist", "Yellow Card", "Red Card");

        // Add components to the grid
        grid.add(selectTeamLabel, 0, 0);
        grid.add(homeRadioButton, 1, 0);
        grid.add(awayRadioButton, 2, 0);
        grid.add(selectPlayerLabel, 0, 1);
        grid.add(playerComboBox, 1, 1, 2, 1);
        grid.add(matchEventsLabel, 0, 2);
        grid.add(matchEventsComboBox, 1, 2, 2, 1);

        // Set up the dialog buttons
        ButtonType endMatchButtonType = new ButtonType("End Match", ButtonBar.ButtonData.OK_DONE);
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(endMatchButtonType, okButtonType, cancelButtonType);

        // Enable/disable OK button based on user selection
        Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);

        // Update OK button based on user input
        teamToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            playerComboBox.getItems().clear();
            String clubName = (newValue == homeRadioButton) ? selectedMatch.getHome() : selectedMatch.getAway();
            playerComboBox.getItems().addAll(PlayerDAO.getPlayersByClub(clubName));
            okButton.setDisable(playerComboBox.getValue() == null || matchEventsComboBox.getValue() == null);
        });

        playerComboBox.valueProperty().addListener((observable, oldValue, newValue) ->
                okButton.setDisable(newValue == null || matchEventsComboBox.getValue() == null));

        matchEventsComboBox.valueProperty().addListener((observable, oldValue, newValue) ->
                okButton.setDisable(newValue == null || playerComboBox.getValue() == null));

        // Set the layout and show the dialog
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == endMatchButtonType) {
                // Handle End Match button action (if needed)
                MatchesDAO.markMatchComplete(matches.getMatchId());
            } else if (dialogButton == okButtonType) {
                // Handle OK button action
                String selectedTeam = ((RadioButton) teamToggleGroup.getSelectedToggle()).getText();
                String selectedPlayer = playerComboBox.getValue();
                String selectedEvent = matchEventsComboBox.getValue();
                MatchesDAO.insertMatchEvent(matches.getMatchId(),selectedPlayer,selectedEvent);
                System.out.println("Selected Team: " + selectedTeam);
                System.out.println("Selected Player: " + selectedPlayer);
                System.out.println("Selected Event: " + selectedEvent);
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void setAddOurTable (){
        // Initialize an ObservableList to store the data for 'OurTableView'
        ObservableList<Matches> ourMatchesList = FXCollections.observableArrayList();

        // Call the JDBC method to retrieve data from the database
        try {
            List<Matches> databaseMatches = MatchesDAO.selectMatches(); // Replace YourJDBCClassName with the actual name of your JDBC class
            ourMatchesList.addAll(databaseMatches);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Set the ObservableList as the data source for 'OurTableView'
        OurTableView.setItems(ourMatchesList);
        OurTableView.refresh();

    }
    private void setAddOther(){
        // Initialize an ObservableList to store the data for 'OurTableView'
        ObservableList<Matches> otherMatchesList = FXCollections.observableArrayList();

        // Call the JDBC method to retrieve data from the database
        try {
            List<Matches> databaseMatches = MatchesDAO.selectMatches(1); // Replace YourJDBCClassName with the actual name of your JDBC class
            otherMatchesList.addAll(databaseMatches);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Set the ObservableList as the data source for 'OurTableView'
        OtherTable.setItems(otherMatchesList);
        OtherTable.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Cell binding for 'OtherTable'
        OtherMatchID.setCellValueFactory(cellData -> cellData.getValue().matchIdProperty().asObject());
        OtherHome.setCellValueFactory(cellData -> cellData.getValue().homeProperty());
        OtherAway.setCellValueFactory(cellData -> cellData.getValue().awayProperty());
        OtherDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        setAddOurTable();
        // Cell binding for 'OurTableView'
        OurMatchID.setCellValueFactory(cellData -> cellData.getValue().matchIdProperty().asObject());
        OurHome.setCellValueFactory(cellData -> cellData.getValue().homeProperty());
        OurAway.setCellValueFactory(cellData -> cellData.getValue().awayProperty());
        OurDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        setAddOther();
    }
}
