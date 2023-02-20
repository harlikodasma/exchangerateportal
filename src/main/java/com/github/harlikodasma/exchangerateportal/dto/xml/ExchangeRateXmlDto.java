package com.github.harlikodasma.exchangerateportal.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "FxRate")
@Data
public class ExchangeRateXmlDto {

    @JacksonXmlProperty(localName = "Tp")
    private String exchangeRateType;

    @JacksonXmlProperty(localName = "Dt")
    private LocalDate date;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "CcyAmt")
    private List<ExchangeRateValueXmlDto> value;
}
