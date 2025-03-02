package ru.trainee.slepchenko.management.logic;

import javafx.scene.control.Alert;

public class AlertMessage {
    public static void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
