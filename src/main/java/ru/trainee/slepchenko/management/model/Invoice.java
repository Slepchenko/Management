package ru.trainee.slepchenko.management.model;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    private LocalDate date;

    @Column(name = "user_name")
    private String user;

    private double amount;

    private String currency;

    @Column(name = "exchange_rate")
    private double exchangeRate;

    private String product;

    private double quantity;

}
