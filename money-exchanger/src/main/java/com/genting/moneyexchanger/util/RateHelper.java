package com.genting.moneyexchanger.util;

import com.genting.moneyexchanger.domain.Currency;

import java.util.List;
import java.util.Optional;

/**
 * <p>Helper class which is used for manipulating the currency details</p>
 */
public class RateHelper {

    /**
     * This methods gets the {@link Currency} object out of the currency details list
     *
     * @param currencyCode currency code
     * @param currencyList currencies list extracted from repository
     * @return optional
     */
    public static Optional<Currency> getCurrencyDetailsByCurrencyCode(String currencyCode, List<Currency> currencyList) {
        return currencyList.stream()
                .filter(currency1 -> currency1.getCurrency()
                        .equals(currencyCode)).findAny();
    }
}
