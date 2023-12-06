package com.example.semester_project;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.util.Arrays;

public class SimplePieChartApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Sample data (replace this with your actual data)
        PieChart.Data slice1 = new PieChart.Data("Category 1", 30);
        PieChart.Data slice2 = new PieChart.Data("Category 2", 20);


        PieChart pieChart = new PieChart();
        pieChart.getData().addAll(Arrays.asList(slice1, slice2));
        pieChart.setTitle("Expense Distribution");

        // Handle mouse events on the slices (optional)
        for (PieChart.Data data : pieChart.getData()) {
            data.getNode().setOnMouseEntered(event -> data.getNode().setScaleX(1.1));
            data.getNode().setOnMouseExited(event -> data.getNode().setScaleX(1.0));
        }

        Scene scene = new Scene(pieChart, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Simple Pie Chart");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
