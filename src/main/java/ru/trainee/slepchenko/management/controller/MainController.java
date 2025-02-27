package ru.trainee.slepchenko.management.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button goToAppButton;

    @FXML
    private void goToApp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/documentsList.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Документооборот");
            stage.setScene(new Scene(root));
            stage.show();
            ((Stage) goToAppButton.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
