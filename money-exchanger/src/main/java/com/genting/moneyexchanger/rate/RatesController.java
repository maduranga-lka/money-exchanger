package com.genting.moneyexchanger.rate;

import com.genting.moneyexchanger.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RatesController {

    @Autowired
    private RateService currencyRateService;

    @GetMapping(path = "/exchanges/rates")
    public List<Currency> getAllCurrencyRates() {
        List<Currency> currencyRates = currencyRateService.getAllCurrencyRates();
        if (!currencyRates.isEmpty()) {
            return currencyRates;
        } else {
            throw new CurrencyRatesNotFoundException("currency not found");
        }
    }

    @GetMapping(path = "/exchanges/rates/{currency}")
    public Currency getExchangeRate(@PathVariable String currency) {
        Currency rate = currencyRateService.getExchangeRateByCurrency(currency);
        if (rate.isNil()) {
            throw new CurrencyRatesNotFoundException("currency " + currency + " not found");
        }
        return rate;
    }
}
