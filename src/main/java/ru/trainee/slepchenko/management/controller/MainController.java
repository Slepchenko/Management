package ru.trainee.slepchenko.management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private Button button;

    @FXML
    private void goToAppButtonClick() {
        System.out.println("Кнопка переводит на другое окно");
    }
}
