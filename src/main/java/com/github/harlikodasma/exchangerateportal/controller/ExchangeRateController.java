package com.github.harlikodasma.exchangerateportal.controller;

import com.github.harlikodasma.exchangerateportal.dto.ExchangeRateDto;
import com.github.harlikodasma.exchangerateportal.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exchange-rate")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping
    public List<ExchangeRateDto> getExchangeRateHistory(@RequestParam String currencyAlphabeticCode) {
        return exchangeRateService.getExchangeRateHistory(currencyAlphabeticCode);
    }
}
