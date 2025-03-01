package ru.trainee.slepchenko.management.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class PaymentRequestController {

    @FXML
    private TextField amountField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField commissionField;

    @FXML
    private TextField contractorField;

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
    private TextField userField;

    private ListView<String> paymentRequestListView;

    private DocumentsListController documentsListController;

    public void setDocumentsListController(DocumentsListController controller, StackPane leftContainer) {
        this.documentsListController = controller;
        this.leftContainer = leftContainer;
    }

    @FXML
    void onCancel(ActionEvent event) {

    }

    @FXML
    void onOk(ActionEvent event) {

    }

}
