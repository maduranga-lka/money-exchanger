package com.genting.moneyexchanger.rate;

import com.genting.moneyexchanger.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RatesController {

    public static final String ALL_RATES_URI = "/exchanges/rates";
    public static final String MESSAGE = "";
    public static final String RATES_BY_CURRENCY_URI = "/exchanges/rates/{currency}";

    @Autowired
    private RateService currencyRateService;

    @GetMapping(path = ALL_RATES_URI)
    public List<Currency> getAllCurrencyRates() {
        List<Currency> currencyRates = currencyRateService.getAllCurrencyRates();
        if (!currencyRates.isEmpty()) {
            return currencyRates;
        } else {
            throw new CurrencyRatesNotFoundException(MESSAGE);
        }
    }

    @GetMapping(path = RATES_BY_CURRENCY_URI)
    public Currency getExchangeRate(@PathVariable String currency) {
        Currency rate = currencyRateService.getExchangeRateByCurrency(currency);
        if (rate.isNil()) {
            throw new CurrencyRatesNotFoundException("");
        }
        return rate;
    }
}
