package ru.trainee.slepchenko.management.dto;

import ru.trainee.slepchenko.management.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceDao {

    void save(Invoice invoice);

    void deleteById(int id);

    Optional<Invoice> findById(int id);

    List<Invoice> findAll();

}
