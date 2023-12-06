package com.example.semester_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PaneForFinancesPage implements Initializable {
    @FXML
    PieChart Total;
    @FXML
    Label CurrentBudget;
    @FXML
    Label Spent;
    @FXML
    Button ManageFinances;
    @FXML
    Button AddProfit;
    private static final String HOVERED_BUTTON_STYLE =
            "-fx-background-color: #FC2680; -fx-text-fill: #331B50;";
    private static final String IDLE_BUTTON_STYLE =
            "-fx-background-color: #331B50; -fx-text-fill: white;";
    @FXML
    public void ManageFinances(){
        if(!FinancesDAO.isDefault(1)){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Budget Entered Already");
            alert.showAndWait();
        }
        else{
        TextInputDialog budgetDialog = new TextInputDialog();
        budgetDialog.setTitle("Enter Budget");
        budgetDialog.setHeaderText(null);
        budgetDialog.setContentText("Enter budget:");

        // Create a ComboBox for selecting the unit
        ComboBox<String> unitComboBox = new ComboBox<>();
        unitComboBox.getItems().addAll("Thousand", "Million", "Billion");
        unitComboBox.setValue("Thousand");

        // Create a custom dialog layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Select Budget Unit:"), 0, 0);
        grid.add(unitComboBox, 1, 0);
        grid.add(new Label("Budget Amount:"), 0, 1);
        grid.add(budgetDialog.getEditor(), 1, 1);

        // Set the custom layout to the dialog
        budgetDialog.getDialogPane().setContent(grid);

        // Set up the result converter to convert the input to a Double
        budgetDialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String input = budgetDialog.getEditor().getText();
                try {
                    double budget = Double.parseDouble(input);
                    // Convert the budget based on the selected unit
                    switch (unitComboBox.getValue()) {
                        case "Thousand":
                            budget *= 1_000;
                            break;
                        case "Million":
                            budget *= 1_000_000;
                            break;
                        case "Billion":
                            budget *= 1_000_000_000;
                            break;
                    }

                    FinancesDAO.SetBudget();
                    FinancesDAO.setBudget(BigInteger.valueOf((long) budget));
                    // Display the budget in the console (you can replace this with your actual logic)
                    System.out.println("Entered Budget: " + budget);
                } catch (NumberFormatException e) {
                    // Handle the case where the input is not a valid number
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid number for the budget.");
                    alert.showAndWait();
                }

            }
            return null;
        });

        budgetDialog.showAndWait();
    }
    }
    public void ManageFinancesOnMouseEntered(MouseEvent e){
        ManageFinances.setStyle(HOVERED_BUTTON_STYLE);
    }
    public void ManageFinancesOnMouseExit(MouseEvent e){
        ManageFinances.setStyle(IDLE_BUTTON_STYLE);
    }

    @FXML
    public void AddProfit() {
        TextInputDialog profitDialog = new TextInputDialog();
        profitDialog.setTitle("Enter Profit");
        profitDialog.setHeaderText(null);
        profitDialog.setContentText("Enter profit:");

        // Create a ComboBox for selecting the unit
        ComboBox<String> unitComboBox = new ComboBox<>();
        unitComboBox.getItems().addAll("Thousand", "Million");
        unitComboBox.setValue("Thousand");

        // Create a custom dialog layout
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Select Profit Unit:"), 0, 0);
        grid.add(unitComboBox, 1, 0);
        grid.add(new Label("Profit Amount:"), 0, 1);
        grid.add(profitDialog.getEditor(), 1, 1);

        // Set the custom layout to the dialog
        profitDialog.getDialogPane().setContent(grid);

        // Set up the result converter to convert the input to a Double
        profitDialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                String input = profitDialog.getEditor().getText();
                try {
                    double profit = Double.parseDouble(input);
                    // Convert the profit based on the selected unit
                    switch (unitComboBox.getValue()) {
                        case "Thousand":
                            profit *= 1_000;
                            break;
                        case "Million":
                            profit *= 1_000_000;
                            break;
                    }

                    FinancesDAO.addProfit((long) profit,1);
                    // Display the profit in the console (you can replace this with your actual logic)
                    System.out.println("Entered Profit: " + profit);
                } catch (NumberFormatException e) {
                    // Handle the case where the input is not a valid number
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid number for the profit.");
                    alert.showAndWait();
                }
            }
            return null;
        });

        profitDialog.showAndWait();
    }

    public void AddProfitOnMouseEntered(MouseEvent e){
        AddProfit.setStyle(HOVERED_BUTTON_STYLE);
    }
    public void AddProfitOnMouseExited(MouseEvent e){
        AddProfit.setStyle(IDLE_BUTTON_STYLE);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Sample data (replace this with your actual data)
        PieChart.Data slice1 = new PieChart.Data("Spent", FinancesDAO.getBudgetSpent(1));
        PieChart.Data slice2 = new PieChart.Data("Current Budget", FinancesDAO.getRemainingBudget(1));

        CurrentBudget.setText(String.valueOf(FinancesDAO.getRemainingBudget(1)));
        Spent.setText(String.valueOf(FinancesDAO.getBudgetSpent(1)));

        Total.getData().addAll(Arrays.asList(slice1, slice2));
        Total.setTitle("Expense Distribution");

        // Handle mouse events on the slices (optional)
        for (PieChart.Data data : Total.getData()) {
            data.getNode().setOnMouseEntered(event -> data.getNode().setScaleX(1.1));
            data.getNode().setOnMouseExited(event -> data.getNode().setScaleX(1.0));
        }
    }
}
