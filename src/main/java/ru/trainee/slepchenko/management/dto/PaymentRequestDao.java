package ru.trainee.slepchenko.management.dto;

import ru.trainee.slepchenko.management.model.Invoice;
import ru.trainee.slepchenko.management.model.PaymentRequest;

import java.util.List;
import java.util.Optional;

public interface PaymentRequestDao {

    void save(PaymentRequest paymentRequest);

    void deleteById(int id);

    Optional<PaymentRequest> findById(int id);

    List<PaymentRequest> findAll();

}
