package ru.trainee.slepchenko.management.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.trainee.slepchenko.management.dto.DocumentDto;
import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;
import ru.trainee.slepchenko.management.model.PaymentRequest;

import java.io.File;
import java.util.Objects;

public class DocumentsListController {
    @FXML
    private Button btnExit;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<String> documentsList;

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

    private DocumentDto documentDto = new DocumentDto();

    public DocumentDto getDocumentDto() {
        return documentDto;
    }

    @FXML
    void deleteSelected(ActionEvent event) {

    }

    @FXML
    void exitApp(ActionEvent event) {

    }

    @FXML
    void goToInvoiceForm(ActionEvent event) {
        loadForm("/invoiceForm.fxml", "INVOICE");
    }

    @FXML
    void goToPaymentForm(ActionEvent event) {
        loadForm("/paymentForm.fxml", "PAYMENT");
    }

    @FXML
    void goToPaymentRequestForm(ActionEvent event) {
        loadForm("/paymentRequestForm.fxml", "PAYMENT_REQUEST");
    }

    @FXML
    void loadDocument(ActionEvent event) {
        String filePath = chooseFileLoad();
        if (filePath != null) {
            documentDto.load(filePath);
            updateDocumentsList();
        }
    }

    @FXML
    void saveDocument(ActionEvent event) {
        String filePath = chooseFileSave();
        if (filePath != null) {
            documentDto.save(filePath);
        }
    }

    @FXML
    void viewDocument(ActionEvent event) {

    }

    public ListView<String> getDocumentsList() {
        return documentsList;
    }

    public void addInvoiceToList(Invoice invoice) {
        documentDto.add(invoice);
        if (documentsList != null) {
            documentsList.getItems().add("Накладная от "
                    + invoice.getDate()
                    + " номер "
                    + invoice.getNumber());
            refreshDocumentsList();
        }
    }

    public void addPaymentToList(Payment payment) {
        documentDto.add(payment);
        if (documentsList != null) {
            documentsList.getItems().add("Платёжка от "
                    + payment.getDate()
                    + " номер "
                    + payment.getNumber());
            refreshDocumentsList();
        }
    }

    public void addPaymentRequestToList(PaymentRequest paymentRequest) {
        documentDto.add(paymentRequest);
        if (documentsList != null) {
            documentsList.getItems().add("Заявка на оплату от "
                    + paymentRequest.getDate()
                    + " номер "
                    + paymentRequest.getNumber());
            refreshDocumentsList();
        }
    }

    public void refreshDocumentsList() {
        documentsList.refresh();
    }


    private void loadForm(String path, String documentType) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(path)));
            Node form = loader.load();
            Object controller = loader.getController();
            if ("INVOICE".equals(documentType)) {
                ((InvoiceController) controller).setDocumentsListController(this, leftContainer);
            }
            if ("PAYMENT".equals(documentType)) {
                ((PaymentController) controller).setDocumentsListController(this, leftContainer);
            }
            if ("PAYMENT_REQUEST".equals(documentType)) {
                ((PaymentRequestController) controller).setDocumentsListController(this, leftContainer);
            }

            leftContainer.getChildren().clear();
            leftContainer.getChildren().add(form);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateDocumentsList() {
        documentsList.getItems().clear();

        for (Object doc : documentDto.getDocuments()) {
            if (doc instanceof Invoice invoice) {
                documentsList.getItems().add("Накладная от " + invoice.getDate() + " номер " + invoice.getNumber());
            }
            if (doc instanceof Payment payment) {
                documentsList.getItems().add("Платёжка от " + payment.getDate() + " номер " + payment.getNumber());
            }
            if (doc instanceof PaymentRequest paymentRequest) {
                documentsList.getItems().add("Заявка на оплату от " + paymentRequest.getDate() + " номер " + paymentRequest.getNumber());
            }
        }
    }

    private String chooseFileSave() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите папку");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));

        File selectedFile = fileChooser.showSaveDialog(new Stage());
        return (selectedFile != null) ? selectedFile.getAbsolutePath() : null;
    }

    private String chooseFileLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Текстовые файлы", "*.txt"));

        File selectedFile = fileChooser.showOpenDialog(new Stage());
        return (selectedFile != null) ? selectedFile.getAbsolutePath() : null;
    }

}
