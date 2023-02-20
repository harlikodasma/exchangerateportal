package com.github.harlikodasma.exchangerateportal.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PortalConstants {

    private static final String FX_RATES_BASE_URI = "http://www.lb.lt/webservices/FxRates/FxRates.asmx/";
    private static final String CURRENCY_LIST_URI = "getCurrencyList";
    private static final String GET_FX_RATES_EU_URI = "getCurrentFxRates?tp=EU";
    public static final String CURRENCY_LIST_FULL_URI = FX_RATES_BASE_URI + CURRENCY_LIST_URI;
    public static final String FX_RATES_FULL_URI = FX_RATES_BASE_URI + GET_FX_RATES_EU_URI;
    public static final String FX_RATES_NAMESPACE = "http://www.lb.lt/WebServices/FxRates";
}
