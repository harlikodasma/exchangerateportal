package com.github.harlikodasma.exchangerateportal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "EXCHANGE_RATE_HISTORY", schema = "PUBLIC")
public class ExchangeRateHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "CURRENCY_ALPHABETIC_CODE", nullable = false, length = 1048576)
    private String currencyAlphabeticCode;

    @Column(name = "RATE_AGAINST_EUR", nullable = false, precision = 100000)
    private BigDecimal rateAgainstEur;

}
