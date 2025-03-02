package ru.trainee.slepchenko.management.logic;

import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;
import ru.trainee.slepchenko.management.model.PaymentRequest;

import java.time.LocalDate;

public class DocumentService {

    public static String serializeDocument(Object doc) {
        if (doc instanceof Invoice invoice) {
            return String.join("|",
                    "INVOICE",
                    invoice.getNumber(),
                    invoice.getDate().toString(),
                    invoice.getUser(),
                    String.valueOf(invoice.getAmount()),
                    invoice.getCurrency(),
                    String.valueOf(invoice.getExchangeRate()),
                    invoice.getProduct(),
                    String.valueOf(invoice.getQuantity())
            );
        }
        if (doc instanceof Payment payment) {
            return String.join("|",
                    "PAYMENT",
                    payment.getNumber(),
                    payment.getDate().toString(),
                    payment.getUser(),
                    String.valueOf(payment.getAmount()),
                    payment.getEmployee()
            );
        }
        if (doc instanceof PaymentRequest paymentRequest) {
            return String.join("|",
                    "PAYMENT_REQUEST",
                    paymentRequest.getNumber(),
                    paymentRequest.getDate().toString(),
                    paymentRequest.getUser(),
                    paymentRequest.getContractor(),
                    String.valueOf(paymentRequest.getAmount()),
                    paymentRequest.getCurrency(),
                    String.valueOf(paymentRequest.getExchangeRate()),
                    String.valueOf(paymentRequest.getCommission())
            );
        }
        return "";
    }

    public static Object deserializeDocument(String docLine) {
        String[] fields = docLine.split("\\|");
        if (fields.length > 1) {
            LocalDate date = LocalDate.parse(fields[2]);
            try {
                switch (fields[0]) {
                    case "INVOICE" -> {
                        Invoice invoice = new Invoice();
                        invoice.setNumber(fields[1]);
                        invoice.setDate(date);
                        invoice.setUser(fields[3]);
                        invoice.setAmount(Double.parseDouble(fields[4]));
                        invoice.setCurrency(fields[5]);
                        invoice.setExchangeRate(Double.parseDouble(fields[6]));
                        invoice.setProduct(fields[7]);
                        invoice.setProduct(fields[8]);
                        return invoice;
                    }
                    case "PAYMENT" -> {
                        Payment payment = new Payment();
                        payment.setNumber(fields[1]);
                        payment.setDate(date);
                        payment.setUser(fields[3]);
                        payment.setAmount(Double.parseDouble(fields[4]));
                        payment.setEmployee(fields[5]);
                        return payment;
                    }
                    case "PAYMENT_REQUEST" -> {
                        PaymentRequest paymentRequest = new PaymentRequest();
                        paymentRequest.setNumber(fields[1]);
                        paymentRequest.setDate(date);
                        paymentRequest.setUser(fields[3]);
                        paymentRequest.setContractor(fields[4]);
                        paymentRequest.setAmount(Double.parseDouble(fields[5]));
                        paymentRequest.setCurrency(fields[6]);
                        paymentRequest.setExchangeRate(Double.parseDouble(fields[7]));
                        paymentRequest.setCommission(Double.parseDouble(fields[8]));
                        return paymentRequest;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getDocumentText(Object doc) {
        if (doc instanceof Invoice invoice) {
            return "Накладная от " + invoice.getDate() + " номер " + invoice.getNumber();
        }
        if (doc instanceof Payment payment) {
            return "Платёжка от " + payment.getDate() + " номер " + payment.getNumber();
        }
        if (doc instanceof PaymentRequest request) {
            return "Заявка на оплату от " + request.getDate() + " номер " + request.getNumber();
        }
        return "";
    }

}
