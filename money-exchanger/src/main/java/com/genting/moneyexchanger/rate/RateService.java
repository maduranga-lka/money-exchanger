package com.genting.moneyexchanger.rate;

import com.genting.moneyexchanger.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Service class for keeping <b>Rate</b> related functionality</p>
 */
@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;

    /**
     * Retrieves all the currency details from the database
     *
     * @return currency details list
     */
    protected List<Currency> getAllCurrencyRates() {
        return rateRepository.getAllCurrencyRates();
    }

    /**
     * Retrieves currency details by currency code
     *
     * @param currencyCode currency Code
     * @return Currency details
     */
    protected Currency getExchangeRateByCurrency(String currencyCode) {
        Currency currencyResult = rateRepository.getExchangeRateByCurrency(currencyCode);

        if (currencyResult != null) {
            return currencyResult;
        } else {
            return buildNilCurrencyResponse();
        }
    }

    /**
     * Builds {@link Currency} when details are
     * not found ~ null object pattern
     *
     * @return Currency details
     */
    private Currency buildNilCurrencyResponse() {
        Currency currency = new Currency();
        currency.setNil(true);
        return currency;
    }
}
