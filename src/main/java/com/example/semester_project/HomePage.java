package com.example.semester_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


//import static jdk.jpackage.internal.WixAppImageFragmentBuilder.ShortcutsFolder.Desktop;

public class HomePage implements Initializable {
    @FXML
    Polygon polygon;

    @FXML
    Button home, squad, transfers, finances,info;

    @FXML
    Pane pane1;

    @FXML
    AnchorPane pane2;

    @FXML
    HBox buttons_area;

    boolean[] buttons = new boolean[5];

    private void movePolygon(double toX) {
        polygon.setTranslateX(toX);
    }

    private void setButtonStyle(Button selectedButton) {
        Arrays.asList(home, squad, transfers, finances).forEach(button -> {
            button.setStyle("-fx-background-color: #354881;");
        });
        selectedButton.setStyle("-fx-background-color: #2f275c;");
    }

    private void loadFXML(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        pane1.getChildren().setAll((Pane) fxmlLoader.load());
    }

    @FXML
    public void SquadPage() throws IOException {
        movePolygon(60);
        setButtonStyle(squad);
        loadFXML("PaneForSquadPage.fxml");
    }

    @FXML
    public void TransfersPage() throws IOException {
        movePolygon(150);
        setButtonStyle(transfers);
        loadFXML("PaneForTransferPage.fxml");
    }

    @FXML
    public void FinancePage() throws IOException {
        movePolygon(240);
        setButtonStyle(finances);
        loadFXML("PaneForFinancesPage.fxml");
    }


    @FXML
    public void HomePage() throws IOException {
        movePolygon(0);
        setButtonStyle(home);
        loadFXML("PaneForHomePage.fxml");
    }
    @FXML
    public void info(ActionEvent event){
        try {
            Desktop.getDesktop().browse(new URI("http://localhost:8080/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switch (FootBallClubManagementSystem.WhichPage){
            case 'h':
                home.fire();
                break;
            case 's':
                squad.fire();
                break;
            case 't':
                transfers.fire();
                break;
            case 'f':
                finances.fire();
        }
    }
}
