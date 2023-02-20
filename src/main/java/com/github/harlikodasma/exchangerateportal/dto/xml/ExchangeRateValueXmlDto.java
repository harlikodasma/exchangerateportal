package com.github.harlikodasma.exchangerateportal.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.math.BigDecimal;

@XmlRootElement(name = "CcyAmt")
@Data
public class ExchangeRateValueXmlDto {

    @JacksonXmlProperty(localName = "Ccy")
    private String currencyAlphabeticCode;

    @JacksonXmlProperty(localName = "Amt")
    private BigDecimal rate;
}
