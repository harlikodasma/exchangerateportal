package com.github.harlikodasma.exchangerateportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrencyDto {

    private String code;
    private String name;
}
