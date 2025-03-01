package ru.trainee.slepchenko.management.dto;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.trainee.slepchenko.management.config.HibernateSessionFactory;
import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.PaymentRequest;

import java.util.List;
import java.util.Optional;

public class PaymentRequestDaoHbn implements PaymentRequestDao {
    @Override
    public void save(PaymentRequest paymentRequest) {
        try (Session session = HibernateSessionFactory.sf().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(paymentRequest);
            transaction.commit();
        }
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Optional<PaymentRequest> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<PaymentRequest> findAll() {
        try (Session session = HibernateSessionFactory.sf().openSession()) {
            return session.createQuery("FROM PaymentRequest", PaymentRequest.class).list();
        }
    }
}
