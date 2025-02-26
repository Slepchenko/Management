package ru.trainee.slepchenko.management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    private LocalDate date;

    @Column(name = "user_name")
    private String user;

    private String contractor;

    private double amount;

    private String currency;

    @Column(name = "exchange_rate")
    private double exchangeRate;

    private double commission;

}
