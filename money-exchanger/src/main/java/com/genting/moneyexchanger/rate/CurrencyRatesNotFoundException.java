package com.genting.moneyexchanger.rate;

public class CurrencyRatesNotFoundException extends RuntimeException{
    public CurrencyRatesNotFoundException(String message) {
        super(message);
    }
}
