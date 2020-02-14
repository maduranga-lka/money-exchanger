package com.genting.moneyexchanger.rate;

import com.genting.moneyexchanger.domain.Currency;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Helper class that stores currency data</p>
 */
public class RateData {
    private static List<Currency> currencyList;

    static {
        currencyList = new ArrayList();
        currencyList.add(new Currency("USD", 1.3392, 1.3574));
        currencyList.add(new Currency("HKD", 0.1738, 0.1698));
    }

    public static List<Currency> getRates(){
        return currencyList;
    }
}
