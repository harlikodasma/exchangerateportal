package com.github.harlikodasma.exchangerateportal.controller;

import com.github.harlikodasma.exchangerateportal.dto.CurrencyCalculateDto;
import com.github.harlikodasma.exchangerateportal.dto.CurrencyDto;
import com.github.harlikodasma.exchangerateportal.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public List<CurrencyDto> getCurrencyList() {
        return currencyService.getCurrencyList();
    }

    @GetMapping("calculate")
    public CurrencyCalculateDto calculateCurrencyRate(@RequestParam BigDecimal amount, @RequestParam String currencyAlphabeticCode) {
        return currencyService.calculateCurrencyRate(amount, currencyAlphabeticCode);
    }
}
