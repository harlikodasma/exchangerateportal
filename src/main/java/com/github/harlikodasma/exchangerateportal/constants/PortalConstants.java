package com.github.harlikodasma.exchangerateportal.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PortalConstants {

    private static final String FX_RATES_BASE_URI = "http://www.lb.lt/webservices/FxRates/FxRates.asmx/";
    private static final String CURRENCY_LIST_URI = "getCurrencyList";
    public static final String CURRENCY_LIST_FULL_URI = FX_RATES_BASE_URI + CURRENCY_LIST_URI;
}
