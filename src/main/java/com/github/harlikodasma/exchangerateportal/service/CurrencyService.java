package com.github.harlikodasma.exchangerateportal.service;

import com.github.harlikodasma.exchangerateportal.dto.CurrencyDto;
import com.github.harlikodasma.exchangerateportal.dto.xml.CurrencyListXmlDto;
import com.github.harlikodasma.exchangerateportal.exceptions.PortalServiceRestClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.github.harlikodasma.exchangerateportal.constants.ErrorConstants.ERROR_EXTERNAL_SERVICE_RESPONSE;
import static com.github.harlikodasma.exchangerateportal.constants.PortalConstants.CURRENCY_LIST_FULL_URI;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private static final RestTemplate restTemplate = new RestTemplate();

    public List<CurrencyDto> getCurrencyList() {
        CurrencyListXmlDto response = restTemplate.getForObject(CURRENCY_LIST_FULL_URI, CurrencyListXmlDto.class);
        if (response != null) {
            return xmlCurrenciesToDto(response);
        }
        throw new PortalServiceRestClientException(ERROR_EXTERNAL_SERVICE_RESPONSE);
    }

    private List<CurrencyDto> xmlCurrenciesToDto(CurrencyListXmlDto currencyListXmlDto) {
        return currencyListXmlDto.getCurrencyList().stream().map(
                        currency -> new CurrencyDto(currency.getAlphabeticCode(), currency.getCurrencyName().get(1).getValue()))
                .toList();
    }
}
