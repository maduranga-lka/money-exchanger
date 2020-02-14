package com.genting.moneyexchanger.rate;

import com.genting.moneyexchanger.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;

    protected List<Currency> getAllCurrencyRates() {
        return rateRepository.getAllCurrencyRates();
    }

    protected Currency getExchangeRateByCurrency(String currency) {
        Currency currencyResult = rateRepository.getExchangeRateByCurrency(currency);

        if (currencyResult != null) {
            return currencyResult;
        } else {
            return buildNilCurrencyResponse();
        }
    }

    private Currency buildNilCurrencyResponse() {
        Currency currency = new Currency();
        currency.setNil(true);
        return currency;
    }
}
