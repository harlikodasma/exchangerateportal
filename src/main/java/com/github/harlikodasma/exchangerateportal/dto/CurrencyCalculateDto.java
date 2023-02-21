package com.github.harlikodasma.exchangerateportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyCalculateDto {

    private BigDecimal calculatedAmount;
    private BigDecimal rateAgainstEur;
}
