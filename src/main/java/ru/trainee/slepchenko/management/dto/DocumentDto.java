package ru.trainee.slepchenko.management.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.trainee.slepchenko.management.logic.SerializeDocumentsService;
import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;
import ru.trainee.slepchenko.management.model.PaymentRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                writer.write(SerializeDocumentsService.serializeDocument(doc));
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
                Object document = SerializeDocumentsService.deserializeDocument(line);
                if (document != null) {
                    documents.add(document);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Map<String, List<?>> delDocs) {

    }

//    private Map<String, List<?>> newInitDocument() {
////        return Map.of(
////                "INVOICE", new ArrayList<Invoice>(),
////                "PAYMENT", new ArrayList<Payment>(),
////                "PAYMENT_REQUEST", new ArrayList<PaymentRequest>());
//        Map<String, List<?>> initDocs = new HashMap<>();
//        initDocs.put("INVOICE", new ArrayList<Invoice>());
//        initDocs.put("PAYMENT", new ArrayList<Payment>());
//        initDocs.put("PAYMENT_REQUEST", new ArrayList<PaymentRequest>());
//        return initDocs;
//    }

}
