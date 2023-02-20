package com.github.harlikodasma.exchangerateportal.controller;

import com.github.harlikodasma.exchangerateportal.dto.CurrencyDto;
import com.github.harlikodasma.exchangerateportal.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
