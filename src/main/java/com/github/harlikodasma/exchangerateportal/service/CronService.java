package com.github.harlikodasma.exchangerateportal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CronService {

    @Value("${cron.get-fx-rates.enabled}")
    private boolean getFxRatesEnabled;

    private final GetFxRatesService getFxRatesService;

    @Scheduled(cron = "${cron.get-fx-rates.schedule}", zone = "${cron.timezone}")
    public void fetchCurrentFxRates() {
        if (getFxRatesEnabled) {
            getFxRatesService.saveCurrentFxRates();
        } else {
            log.info("getFxRates cron job disabled.");
        }
    }
}
