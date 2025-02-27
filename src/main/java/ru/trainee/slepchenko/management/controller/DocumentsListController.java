package ru.trainee.slepchenko.management.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

public class DocumentsListController {
    @FXML
    private Button btnExit;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<?> documentList;

    @FXML
    private Button invoiceButton;

    @FXML
    private StackPane leftContainer;

    @FXML
    private Button loadButton;

    @FXML
    private Button paymentButton;

    @FXML
    private Button paymentRequestButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button viewButton;



    @FXML
    private void deleteSelected(ActionEvent event) {

    }

    @FXML
    private void exitApp() {
        System.exit(0);
    }

    @FXML
    private void goToInvoiceForm(ActionEvent event) {

    }

    @FXML
    private void goToPaymentForm(ActionEvent event) {

    }

    @FXML
    private void goToPaymentRequestForm(ActionEvent event) {

    }

    @FXML
    private void loadDocument(ActionEvent event) {

    }

    @FXML
    private void saveDocument(ActionEvent event) {

    }

    @FXML
    private void viewDocument(ActionEvent event) {

    }

    private void changeForm(Node form) {
        leftContainer.getChildren().clear();
        leftContainer.getChildren().add(form);
    }


}
