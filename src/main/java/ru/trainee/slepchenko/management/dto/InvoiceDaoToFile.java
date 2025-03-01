package ru.trainee.slepchenko.management.dto;

import ru.trainee.slepchenko.management.model.Invoice;

import java.util.List;
import java.util.Optional;

public class InvoiceDaoToFile implements InvoiceDao {
    @Override
    public void save(Invoice invoice) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Optional<Invoice> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Invoice> findAll() {
        return null;
    }
}
