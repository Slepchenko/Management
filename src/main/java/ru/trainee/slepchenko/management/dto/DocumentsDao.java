package ru.trainee.slepchenko.management.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;
import ru.trainee.slepchenko.management.model.PaymentRequest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class DocumentsDao {

    private static final String FILE_PATH = "/data/file.txt";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Map<String, List<?>> documents = newInitDocument();

    public void add(String key, Object doc) {
        if ("INVOICE".equals(key)) {
            List<Invoice> invoiceList = (List<Invoice>) documents.get("INVOICE");
            invoiceList.add((Invoice) doc);
        }
        if ("PAYMENT".equals(key)) {
            List<Payment> paymentList = (List<Payment>) documents.get("PAYMENT");
            paymentList.add((Payment) doc);
        }
        if ("PAYMENT_REQUEST".equals(key)) {
            List<PaymentRequest> paymentRequestList = (List<PaymentRequest>) documents.get("PAYMENT_REQUEST");
            paymentRequestList.add((PaymentRequest) doc);
        }
    }

    public void delete(Map<String, List<?>> delDocs) {

    }

    public void save() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), documents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        documents = newInitDocument();
        try {
            documents = objectMapper.readValue(new File(FILE_PATH), new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
             new HashMap<>();
        }
    }

    private Map<String, List<?>> newInitDocument() {
        return Map.of(
                "INVOICE", new ArrayList<Invoice>(),
                "PAYMENT", new ArrayList<Payment>(),
                "PAYMENT_REQUEST", new ArrayList<PaymentRequest>());
    }

}
