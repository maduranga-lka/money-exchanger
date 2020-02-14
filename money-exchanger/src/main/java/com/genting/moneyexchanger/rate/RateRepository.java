package com.genting.moneyexchanger.rate;

import com.genting.moneyexchanger.util.RateHelper;
import com.genting.moneyexchanger.domain.Currency;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <p> Repository class which handles Currency rates data operations</p>
 */
@Repository
public class RateRepository {

    /**
     * Retrieves all the currency data from the database and
     * returns
     *
     * @return currencies list
     */
    protected List<Currency> getAllCurrencyRates() {
        return RateData.getRates();
    }

    /**
     * Retrieves currency data from database and then extracts the applicable
     * currency object out of the currency code
     *
     * @param currencyCode currency code
     * @return optional
     */
    public Currency getExchangeRateByCurrency(String currencyCode) {
        // assumes that the rates data is retrieved from a database
        Optional<Currency> currencyOptional = RateHelper.getCurrencyDetailsByCurrencyCode(currencyCode, RateData.getRates());
        if (currencyOptional.isPresent()) {
            return currencyOptional.get();
        }
        return null;
    }
}
