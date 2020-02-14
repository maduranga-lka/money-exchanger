package com.genting.moneyexchanger;


import com.genting.moneyexchanger.domain.Currency;
import com.genting.moneyexchanger.util.RateHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RateHelperTest {

    public static final String USD = "USD";
    public static final String GBP = "GBP";
    public static final String AUD = "AUD";
    private List<Currency> currencyList;
    @Before
    public void setup(){
        currencyList = new ArrayList<>();
        currencyList.add(new Currency(USD,1,2));
        currencyList.add(new Currency(AUD,11,22));
    }

    @Test
    public void testGetCurrencyDetailsByCurrencyCode_When_Exist(){
        Optional<Currency> currency = RateHelper.getCurrencyDetailsByCurrencyCode(USD, currencyList);
        Assert.assertTrue(currency.isPresent());
    }

    @Test
    public void testGetCurrencyDetailsByCurrencyCode_When_Not_Exist(){
        Optional<Currency> currency = RateHelper.getCurrencyDetailsByCurrencyCode(GBP, currencyList);
        Assert.assertTrue(!currency.isPresent());
    }
}
