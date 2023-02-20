package com.github.harlikodasma.exchangerateportal.service;

import com.github.harlikodasma.exchangerateportal.converter.DtoMapper;
import com.github.harlikodasma.exchangerateportal.dto.ExchangeRateDto;
import com.github.harlikodasma.exchangerateportal.repository.ExchangeRateHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateHistoryRepository exchangeRateHistoryRepository;
    private final DtoMapper dtoMapper;

    public List<ExchangeRateDto> getExchangeRateHistory(String currencyAlphabeticCode) {
        return dtoMapper.exchangeRateEntityListToDtoList(exchangeRateHistoryRepository.findAllByCurrencyAlphabeticCode(currencyAlphabeticCode));
    }
}
