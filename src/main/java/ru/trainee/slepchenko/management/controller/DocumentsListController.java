package ru.trainee.slepchenko.management.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
        changeForm(createInvoiceForm());
    }

    @FXML
    private void goToPaymentForm(ActionEvent event) {
        changeForm(createPaymentForm());
    }

    @FXML
    private void goToPaymentRequestForm(ActionEvent event) {
        changeForm(createPaymentRequestForm());
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

    private VBox createInvoiceForm() {
        VBox form = new VBox(10);
        form.getChildren().addAll(
                new Label("Номер:"), new TextField(),
                new Label("Дата:"), new DatePicker(),
                new Label("Пользователь:"), new TextField(),
                new Label("Сумма:"), new TextField(),
                new Label("Валюта:"), new TextField(),
                new Label("Курс Валюты:"), new TextField(),
                new Label("Товар:"), new TextField(),
                new Label("Количество:"), new TextField()
        );
        return form;
    }

    private VBox createPaymentForm() {
        VBox form = new VBox(10);
        form.getChildren().addAll(
                new Label("Номер:"), new TextField(),
                new Label("Дата:"), new DatePicker(),
                new Label("Пользователь:"), new TextField(),
                new Label("Сумма:"), new TextField(),
                new Label("Сотрудник:"), new TextField()
        );
        return form;
    }

    private VBox createPaymentRequestForm() {
        VBox form = new VBox(10);
        form.getChildren().addAll(
                new Label("Номер:"), new TextField(),
                new Label("Дата:"), new DatePicker(),
                new Label("Пользователь:"), new TextField(),
                new Label("Контрагент:"), new TextField(),
                new Label("Сумма:"), new TextField(),
                new Label("Валюта:"), new TextField(),
                new Label("Курс Валюты:"), new TextField(),
                new Label("Комиссия:"), new TextField()
        );
        return form;
    }
}
