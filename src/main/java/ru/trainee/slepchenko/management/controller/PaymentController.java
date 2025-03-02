package ru.trainee.slepchenko.management.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import ru.trainee.slepchenko.management.model.Payment;

import java.time.LocalDate;

public class PaymentController {

    @FXML
    private TextField amountField;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField employeeField;

    @FXML
    private StackPane leftContainer;

    @FXML
    private TextField numberField;

    @FXML
    private Button okButton;

    @FXML
    private TextField userField;

    private ListView<String> paymentListView;

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

            Payment payment = new Payment();
            payment.setNumber(number);
            payment.setDate(date);
            payment.setUser(userField.getText());
            payment.setAmount(Double.parseDouble(amountField.getText()));
            payment.setEmployee(employeeField.getText());

            if (documentsListController != null) {
                documentsListController.addPaymentToList(payment);
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

}
