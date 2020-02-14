package com.genting.moneyexchanger.exchange;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Repository class for <b>Exchange</b> related database operations</p>
 */
@Repository
public class ExchangeRepository {

    /**
     * Adds transaction data to the database
     *
     * @param exchangeTransaction exchange transaction request
     * @return transaction id
     */
    public Long addTransaction(ExchangeTransaction exchangeTransaction) {
        return ExchangeTransactionData.addTransaction(exchangeTransaction);
    }

    /**
     * Retrieves all the transaction details
     *
     * @return all transaction details
     */
    protected List<ExchangeTransaction> getAllTransactions() {
        return ExchangeTransactionData.getTransactions();
    }
}
