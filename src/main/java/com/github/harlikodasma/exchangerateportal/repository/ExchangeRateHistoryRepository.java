package com.github.harlikodasma.exchangerateportal.repository;

import com.github.harlikodasma.exchangerateportal.model.ExchangeRateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRateHistoryRepository extends JpaRepository<ExchangeRateHistory, Long> {

    List<ExchangeRateHistory> findAllByCurrencyAlphabeticCode(String currencyAlphabeticCode);
}
