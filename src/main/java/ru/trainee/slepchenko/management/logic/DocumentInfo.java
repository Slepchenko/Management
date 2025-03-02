package ru.trainee.slepchenko.management.logic;

import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;
import ru.trainee.slepchenko.management.model.PaymentRequest;

public class DocumentInfo {

    public static String getDocumentInfo(Object doc) {
        StringBuilder info = new StringBuilder();
        String sep = System.lineSeparator();
        if (doc instanceof Invoice invoice) {
            info.append("Тип: Накладная").append(sep)
                    .append("Дата: ").append(invoice.getDate()).append(sep)
                    .append("Номер: ").append(invoice.getNumber()).append(sep)
                    .append("Пользователь: ").append(invoice.getUser()).append(sep)
                    .append("Сумма: ").append(invoice.getAmount()).append(" ").append(invoice.getCurrency()).append(sep)
                    .append("Курс валюты: ").append(invoice.getExchangeRate()).append(sep)
                    .append("Товар: ").append(invoice.getProduct()).append(sep)
                    .append("Количество: ").append(invoice.getQuantity());
        } else if (doc instanceof Payment payment) {
            info.append("Тип: Платёжка").append(sep)
                    .append("Дата: ").append(payment.getDate()).append(sep)
                    .append("Номер: ").append(payment.getNumber()).append(sep)
                    .append("Пользователь: ").append(payment.getUser()).append(sep)
                    .append("Сумма: ").append(payment.getAmount()).append(sep)
                    .append("Сотрудник: ").append(payment.getEmployee());
        } else if (doc instanceof PaymentRequest request) {
            info.append("Тип: Заявка на оплату").append(sep)
                    .append("Дата: ").append(request.getDate()).append(sep)
                    .append("Номер: ").append(request.getNumber()).append(sep)
                    .append("Пользователь: ").append(request.getUser()).append(sep)
                    .append("Контрагент: ").append(request.getContractor()).append(sep)
                    .append("Сумма: ").append(request.getAmount()).append(" ").append(request.getCurrency()).append(sep)
                    .append("Курс валюты: ").append(request.getExchangeRate()).append(sep)
                    .append("Комиссия: ").append(request.getCommission());
        } else {
            info.append("Неизвестный документ");
        }
        return info.toString();
    }

}
