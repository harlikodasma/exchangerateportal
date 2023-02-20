package com.github.harlikodasma.exchangerateportal.service;

import com.github.harlikodasma.exchangerateportal.dto.xml.ExchangeRateListXmlDto;
import com.github.harlikodasma.exchangerateportal.dto.xml.ExchangeRateXmlDto;
import com.github.harlikodasma.exchangerateportal.exceptions.PortalServiceRestClientException;
import com.github.harlikodasma.exchangerateportal.model.ExchangeRateHistory;
import com.github.harlikodasma.exchangerateportal.repository.ExchangeRateHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.github.harlikodasma.exchangerateportal.constants.ErrorConstants.ERROR_EXTERNAL_SERVICE_RESPONSE;
import static com.github.harlikodasma.exchangerateportal.constants.PortalConstants.FX_RATES_FULL_URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetFxRatesService {

    private final ExchangeRateHistoryRepository exchangeRateHistoryRepository;
    private static final RestTemplate restTemplate = new RestTemplate();

    public void saveCurrentFxRates() {
        ExchangeRateListXmlDto response = restTemplate.getForObject(FX_RATES_FULL_URI, ExchangeRateListXmlDto.class);
        if (response != null) {
            exchangeRateHistoryRepository.saveAll(xmlExchangeRatesToDtoList(response));
            log.info("getFxRates cron job executed successfully.");
            return;
        }
        throw new PortalServiceRestClientException(ERROR_EXTERNAL_SERVICE_RESPONSE);
    }

    private List<ExchangeRateHistory> xmlExchangeRatesToDtoList(ExchangeRateListXmlDto exchangeRateListXmlDto) {
        return exchangeRateListXmlDto.getExchangeRateList().stream().map(this::generateExchangeRateEntity).toList();
    }

    private ExchangeRateHistory generateExchangeRateEntity(ExchangeRateXmlDto exchangeRateXmlDto) {
        var entity = new ExchangeRateHistory();
        entity.setDate(exchangeRateXmlDto.getDate());
        entity.setCurrencyAlphabeticCode(exchangeRateXmlDto.getValue().get(1).getCurrencyAlphabeticCode());
        entity.setRateAgainstEur(exchangeRateXmlDto.getValue().get(1).getRate());
        return entity;
    }
}
