package ru.trainee.slepchenko.management.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.trainee.slepchenko.management.dto.DocumentDto;
import ru.trainee.slepchenko.management.dto.DocumentHbnDto;
import ru.trainee.slepchenko.management.logic.AlertMessage;
import ru.trainee.slepchenko.management.logic.DocumentInfo;
import ru.trainee.slepchenko.management.logic.DocumentService;
import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;
import ru.trainee.slepchenko.management.model.PaymentRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DocumentsListController {
    @FXML
    private Button btnExit;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<CheckBox> documentsList;

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

    private final DocumentDto documentDto = new DocumentDto();

    private final ObservableList<CheckBox> documentItems = FXCollections.observableArrayList();

    private final DocumentHbnDto documentHbnDto = new DocumentHbnDto();

    public DocumentDto getDocumentDto() {
        return documentDto;
    }

    @FXML
    void initialize() {
        loadDocumentsFromDB();
    }

    @FXML
    void exitApp(ActionEvent event) {
        System.exit(0);
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
            saveToDB();
        }
    }

    private void saveToDB() {
        DocumentHbnDto documentHbnDto = new DocumentHbnDto();
        documentHbnDto.saveDocuments(documentDto.getDocuments());
    }

    @FXML
    void deleteSelected(ActionEvent event) {
        documentDto.delete(documentsList);
    }

    @FXML
    void viewDocument(ActionEvent event) {
        CheckBox selectedCheckBox = documentsList.getSelectionModel().getSelectedItem();
        if (selectedCheckBox == null) {
            AlertMessage.showAlert("Ошибка", "Документ не выбран!", "Выберите документ перед просмотром.");
            return;
        }

        String selectedText = selectedCheckBox.getText();
        Object selectedDocument = findDocumentByText(selectedText);
        if (selectedDocument == null) {
            AlertMessage.showAlert("Ошибка", "Документ не найден!", "Выбранный документ отсутствует в списке.");
            return;
        }
        documentInfo(selectedDocument);
    }

    public ListView<CheckBox> getDocumentsList() {
        return documentsList;
    }

    public void addInvoiceToList(Invoice invoice) {
        addDocumentToList(invoice);
    }

    public void addPaymentToList(Payment payment) {
        addDocumentToList(payment);
    }

    public void addPaymentRequestToList(PaymentRequest paymentRequest) {
        addDocumentToList(paymentRequest);
    }

    private void addDocumentToList(Object document) {
        documentDto.add(document);
        CheckBox checkBox = new CheckBox(DocumentService.getDocumentText(document));
        checkBox.setFocusTraversable(false);
        checkBox.setOnAction(event -> {
            documentsList.getSelectionModel().clearSelection();
            documentsList.getSelectionModel().select(checkBox);
        });
        documentItems.add(checkBox);
        documentsList.setItems(documentItems);
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
        documentItems.clear();

        for (Object doc : documentDto.getDocuments()) {
            CheckBox checkBox = new CheckBox(DocumentService.getDocumentText(doc));
            checkBox.setFocusTraversable(false);
            checkBox.setOnAction(event -> {
                documentsList.getSelectionModel().clearSelection();
                documentsList.getSelectionModel().select(checkBox);
            });
            documentItems.add(checkBox);
        }
        documentsList.setItems(documentItems);
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

    private Object findDocumentByText(String text) {
        for (Object doc : documentDto.getDocuments()) {
            if (DocumentService.getDocumentText(doc).equals(text)) {
                return doc;
            }
        }
        return null;
    }

    private void documentInfo(Object doc) {
        if (doc == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/documentInfo.fxml"));
            VBox infoBox = loader.load();
            DocumentInfoController controller = loader.getController();

            controller.setDocumentsListController(this);
            controller.setDocumentDetails(DocumentInfo.getDocumentInfo(doc));

            leftContainer.getChildren().clear();
            leftContainer.getChildren().add(infoBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void returnToDocumentList() {
        leftContainer.getChildren().clear();
        leftContainer.getChildren().add(documentsList);
    }

    private void loadDocumentsFromDB() {
        DocumentHbnDto documentHbnDto = new DocumentHbnDto();
        List<Object> documents = documentHbnDto.loadDocuments();

        for (Object doc : documents) {
            addDocumentToList(doc);
        }
    }

}
