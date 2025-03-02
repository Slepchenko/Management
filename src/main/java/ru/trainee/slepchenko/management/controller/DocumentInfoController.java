package ru.trainee.slepchenko.management.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DocumentInfoController {

    @FXML
    private VBox infoContainer;

    @FXML
    private Label documentDetailsLabel;

    @FXML
    private Button backButton;

    private DocumentsListController documentsListController;

    public void setDocumentsListController(DocumentsListController controller) {
        this.documentsListController = controller;
    }

    public void setDocumentDetails(String details) {
        documentDetailsLabel.setText(details);
    }

    @FXML
    private void goBack() {
        if (documentsListController != null) {
            documentsListController.returnToDocumentList();
        }
    }
}
