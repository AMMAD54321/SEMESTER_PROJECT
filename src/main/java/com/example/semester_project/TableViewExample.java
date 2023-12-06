package com.example.semester_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableViewExample extends Application {

    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        TableView<Person> tableView = new TableView<>();

        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Person, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        tableView.getColumns().addAll(nameColumn, ageColumn);

        ObservableList<Person> data = FXCollections.observableArrayList();
        data.add(new Person("John Doe", 25));
        data.add(new Person("Jane Doe", 30));

        tableView.setItems(data);

        primaryStage.setScene(new Scene(tableView, 400, 300));
        primaryStage.setTitle("TableView Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

