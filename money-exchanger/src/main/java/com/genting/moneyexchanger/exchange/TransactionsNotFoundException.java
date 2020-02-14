package com.genting.moneyexchanger.exchange;

public class TransactionsNotFoundException extends RuntimeException {
    public TransactionsNotFoundException(String message) {
        super(message);
    }
}
