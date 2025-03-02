package ru.trainee.slepchenko.management.dto;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ru.trainee.slepchenko.management.config.HibernateSessionFactory;
import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;
import ru.trainee.slepchenko.management.model.PaymentRequest;

import java.util.ArrayList;
import java.util.List;

public class DocumentHbnDto {

    public void saveDocuments(List<Object> documents) {
        try (Session session = HibernateSessionFactory.sf().openSession()) {
            Transaction transaction = session.beginTransaction();

            for (Object doc : documents) {
                if (doc instanceof Invoice invoice) {
                    session.persist(invoice);
                }
                if (doc instanceof Payment payment) {
                    session.persist(payment);
                }
                if (doc instanceof PaymentRequest request) {
                    session.persist(request);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object> loadDocuments() {
        List<Object> documents = new ArrayList<>();

        try (Session session = HibernateSessionFactory.sf().openSession()) {
            documents.addAll(session.createQuery("FROM Invoice", Invoice.class).list());
            documents.addAll(session.createQuery("FROM Payment", Payment.class).list());
            documents.addAll(session.createQuery("FROM PaymentRequest", PaymentRequest.class).list());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return documents;
    }
}
