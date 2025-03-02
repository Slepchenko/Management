package ru.trainee.slepchenko.management.controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import ru.trainee.slepchenko.management.model.Invoice;

import java.time.LocalDate;

public class InvoiceController {

    @FXML
    private TextField amountField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField currencyField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField exchangeRateField;

    @FXML
    private StackPane leftContainer;

    @FXML
    private TextField numberField;

    @FXML
    private Button okButton;

    @FXML
    private TextField productField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField userField;

    private DocumentsListController documentsListController;

    public void setDocumentsListController(DocumentsListController controller, StackPane leftContainer) {
        this.documentsListController = controller;
        this.leftContainer = leftContainer;
    }


    @FXML
    void onCancel(ActionEvent event) {
        returnToDocumentsList(event);
    }

    @FXML
    void onOk(ActionEvent event) {
        try {
            String number = numberField.getText();
            LocalDate date = dateField.getValue();
            if (date == null) {
                date = LocalDate.now();
            }

            Invoice invoice = new Invoice();
            invoice.setNumber(number);
            invoice.setDate(date);
            invoice.setUser(userField.getText());
            invoice.setAmount(Double.parseDouble(amountField.getText()));
            invoice.setProduct(productField.getText());
            invoice.setQuantity(Double.parseDouble(quantityField.getText()));

            if (documentsListController != null) {
                documentsListController.addInvoiceToList(invoice);
            }
            returnToDocumentsList(event);
        } catch (Exception e) {
            showAlert("Ошибка", "Ошибка ввода", "Проверьте корректность введённых данных.");
        }
    }

    private void returnToDocumentsList(ActionEvent event) {
        if (leftContainer != null) {
            leftContainer.getChildren().clear();
            leftContainer.getChildren().add(documentsListController.getDocumentsList());
        }
    }


    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

//    private void closeWindow(ActionEvent event) {
//        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        stage.close();
//    }

}


