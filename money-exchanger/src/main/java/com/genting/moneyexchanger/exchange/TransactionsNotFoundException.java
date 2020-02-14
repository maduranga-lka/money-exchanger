package com.genting.moneyexchanger.exchange;

/**
 * <p>Exception to be thrown when transaction data are not found
 * in the database</p>
 */
public class TransactionsNotFoundException extends RuntimeException {
    public TransactionsNotFoundException(String message) {
        super(message);
    }
}
