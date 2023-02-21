package com.github.harlikodasma.exchangerateportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeRateDto {

    private LocalDate date;
    private String currencyAlphabeticCode;
    private BigDecimal rateAgainstEur;
}
