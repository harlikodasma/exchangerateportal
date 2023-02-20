package com.github.harlikodasma.exchangerateportal.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@JacksonXmlRootElement(localName = "CcyNm")
@Data
public class CurrencyNameXmlDto {

    @JacksonXmlProperty(isAttribute = true)
    private String lang;

    @JacksonXmlText
    private String value;
}
