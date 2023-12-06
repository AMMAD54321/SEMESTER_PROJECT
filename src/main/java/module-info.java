module com.example.semester_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.semester_project to javafx.fxml;
    exports com.example.semester_project;
}