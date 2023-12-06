package com.example.semester_project;// FootBallClubManagementSystem.java
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FootBallClubManagementSystem extends Application {

    public static char WhichPage='h';

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Semester Project");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
