package ru.trainee.slepchenko.management.dto;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.trainee.slepchenko.management.config.HibernateSessionFactory;
import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;

import java.util.List;
import java.util.Optional;

public class PaymentDaoHbn implements PaymentDao{

    @Override
    public void save(Payment payment) {
        try (Session session = HibernateSessionFactory.sf().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(payment);
            transaction.commit();
        }
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Optional<Payment> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Payment> findAll() {
        try (Session session = HibernateSessionFactory.sf().openSession()) {
            return session.createQuery("FROM Payment", Payment.class).list();
        }
    }
}
