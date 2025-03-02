package ru.trainee.slepchenko.management.dto;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import ru.trainee.slepchenko.management.logic.DocumentService;

import java.io.*;
import java.util.*;

public class DocumentDto {

    private final List<Object> documents;

    public DocumentDto() {
        this.documents = new ArrayList<>();
    }

    public List<Object> getDocuments() {
        return documents;
    }

    public void add(Object doc) {
        documents.add(doc);
    }

    public void save(String filePath) {
        if (filePath == null) {
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Object doc : documents) {
                writer.write(DocumentService.serializeDocument(doc));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String filePath) {
        if (filePath == null) {
            return;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        documents.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Object document = DocumentService.deserializeDocument(line);
                if (document != null) {
                    documents.add(document);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(ListView<CheckBox> documentsList) {
        List<CheckBox> selectedCheckBox = new ArrayList<>();
        List<String> selectedDocs = new ArrayList<>();
        for (CheckBox checkBox : documentsList.getItems()) {
            if (checkBox.isSelected()) {
                selectedDocs.add(checkBox.getText());
                selectedCheckBox.add(checkBox);
            }
        }
        documentsList.getItems().removeAll(selectedCheckBox);
        getDocuments().removeIf(doc -> selectedDocs.contains(DocumentService.getDocumentText(doc)));
    }

}
