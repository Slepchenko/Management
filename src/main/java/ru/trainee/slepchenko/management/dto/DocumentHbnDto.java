package ru.trainee.slepchenko.management.dto;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;
import ru.trainee.slepchenko.management.model.PaymentRequest;

import java.util.List;

public class DocumentHbnDto {

    public void saveDocuments(List<Object> documents) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            for (Object doc : documents) {
//                if (doc instanceof Invoice invoice) {
//                    session.persist(invoice);
//                }
//                if (doc instanceof Payment payment) {
//                    session.persist(payment);
//                }
//                if (doc instanceof PaymentRequest request) {
//                    session.persist(request);
//                }
//            }
//
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
