package com.github.harlikodasma.exchangerateportal.service;

import com.github.harlikodasma.exchangerateportal.dto.CurrencyCalculateDto;
import com.github.harlikodasma.exchangerateportal.dto.CurrencyDto;
import com.github.harlikodasma.exchangerateportal.dto.ExchangeRateDto;
import com.github.harlikodasma.exchangerateportal.dto.xml.CurrencyListXmlDto;
import com.github.harlikodasma.exchangerateportal.exceptions.PortalServiceRestClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import static com.github.harlikodasma.exchangerateportal.constants.ErrorConstants.ERROR_EXTERNAL_SERVICE_RESPONSE;
import static com.github.harlikodasma.exchangerateportal.constants.PortalConstants.CURRENCY_LIST_FULL_URI;
import static java.math.RoundingMode.HALF_EVEN;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final ExchangeRateService exchangeRateService;
    private static final RestTemplate restTemplate = new RestTemplate();

    public List<CurrencyDto> getCurrencyList() {
        CurrencyListXmlDto response = restTemplate.getForObject(CURRENCY_LIST_FULL_URI, CurrencyListXmlDto.class);
        if (response != null) {
            return xmlCurrenciesToDtoList(response);
        }
        throw new PortalServiceRestClientException(ERROR_EXTERNAL_SERVICE_RESPONSE);
    }

    public CurrencyCalculateDto calculateCurrencyRate(BigDecimal amount, String currencyAlphabeticCode) {
        List<ExchangeRateDto> exchangeRateHistory = exchangeRateService.getExchangeRateHistory(currencyAlphabeticCode);
        ExchangeRateDto latestExchangeRate = exchangeRateHistory
                .stream().max(Comparator.comparing(ExchangeRateDto::getDate))
                .orElseThrow(() -> new PortalServiceRestClientException("Latest exchange rate could not be found for calculation."));
        BigDecimal calculation = latestExchangeRate.getRateAgainstEur()
                .multiply(amount)
                .setScale(2, HALF_EVEN);

        return new CurrencyCalculateDto(calculation, latestExchangeRate.getRateAgainstEur());
    }

    private List<CurrencyDto> xmlCurrenciesToDtoList(CurrencyListXmlDto currencyListXmlDto) {
        return currencyListXmlDto.getCurrencyList().stream().map(
                        currency -> new CurrencyDto(currency.getAlphabeticCode(), currency.getCurrencyName().get(1).getValue()))
                .toList();
    }
}
