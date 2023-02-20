package com.github.harlikodasma.exchangerateportal.converter;

import com.github.harlikodasma.exchangerateportal.dto.ExchangeRateDto;
import com.github.harlikodasma.exchangerateportal.model.ExchangeRateHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring")
@MapperConfig(unmappedTargetPolicy = IGNORE)
public interface DtoMapper {

    List<ExchangeRateDto> exchangeRateEntityListToDtoList(List<ExchangeRateHistory> exchangeRateHistoryList);
}
