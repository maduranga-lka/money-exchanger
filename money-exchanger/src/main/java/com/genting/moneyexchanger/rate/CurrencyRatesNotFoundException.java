package com.genting.moneyexchanger.rate;

/**
 * <p>Exception to be thrown when currency rates are not found
 * in the database</p>
 */
public class CurrencyRatesNotFoundException extends RuntimeException{
    public CurrencyRatesNotFoundException(String message) {
        super(message);
    }
}
