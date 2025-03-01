package ru.trainee.slepchenko.management.dto;

import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentDao {

    void save(Payment payment);

    void deleteById(int id);

    Optional<Payment> findById(int id);

    List<Payment> findAll();

}
