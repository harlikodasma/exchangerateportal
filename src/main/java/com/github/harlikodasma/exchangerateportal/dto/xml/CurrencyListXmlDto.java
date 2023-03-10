package com.github.harlikodasma.exchangerateportal.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

import static com.github.harlikodasma.exchangerateportal.constants.PortalConstants.FX_RATES_NAMESPACE;
import static jakarta.xml.bind.annotation.XmlAccessType.FIELD;

@XmlRootElement(name = "CcyTbl", namespace = FX_RATES_NAMESPACE)
@XmlAccessorType(FIELD)
@Data
public class CurrencyListXmlDto {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "CcyNtry")
    private List<CurrencyXmlDto> currencyList;
}
