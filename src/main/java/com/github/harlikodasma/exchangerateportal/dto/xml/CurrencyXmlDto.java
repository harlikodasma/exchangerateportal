package com.github.harlikodasma.exchangerateportal.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@XmlRootElement(name = "CcyNtry")
@Data
public class CurrencyXmlDto {

    @JacksonXmlProperty(localName = "Ccy")
    private String alphabeticCode;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "CcyNm")
    private List<CurrencyNameXmlDto> currencyName;

    @JacksonXmlProperty(localName = "CcyNbr")
    private String numericCode;

    @JacksonXmlProperty(localName = "CcyMnrUnts")
    private int minorUnit;

}
