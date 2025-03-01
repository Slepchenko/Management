package ru.trainee.slepchenko.management.logic;

import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;
import ru.trainee.slepchenko.management.model.PaymentRequest;

public class SerializeDocument {
    private String serializeDocument(Object document) {

        if (document instanceof Invoice invoice) {
            return "INVOICE;" + invoice.getNumber() + ";" + invoice.getDate() + ";" + invoice.getUser() + ";" +
                    invoice.getAmount() + ";" + invoice.getCurrency() + ";" + invoice.getExchangeRate() + ";" +
                    invoice.getProduct() + ";" + invoice.getQuantity();
        } else if (document instanceof Payment payment) {
            return "PAYMENT;" + payment.getNumber() + ";" + payment.getDate() + ";" + payment.getUser() + ";" +
                    payment.getAmount() + ";" + payment.getEmployee();
        } else if (document instanceof PaymentRequest request) {
            return "PAYMENT_REQUEST;" + request.getNumber() + ";" + request.getDate() + ";" + request.getUser() + ";" +
                    request.getContractor() + ";" + request.getAmount() + ";" + request.getCurrency() + ";" +
                    request.getExchangeRate() + ";" + request.getCommission();
        }
        return "";
    }

//    private Object deserializeDocument(String line) {
//        String[] parts = line.split(";");
//        if (parts.length == 0) return null;
//
//        switch (parts[0]) {
//            case "INVOICE":
//                return new Invoice(parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), parts[5],
//                        Double.parseDouble(parts[6]), parts[7], Double.parseDouble(parts[8]));
//            case "PAYMENT":
//                return new Payment(parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), parts[5]);
//            case "PAYMENT_REQUEST":
//                return new PaymentRequest(parts[1], parts[2], parts[3], parts[4], Double.parseDouble(parts[5]),
//                        parts[6], Double.parseDouble(parts[7]), Double.parseDouble(parts[8]));
//            default:
//                return null;
//        }
//    }
}
