package com.genting.moneyexchanger.exchange;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExchangeRepository {

    public Long addTransaction(ExchangeTransaction exchangeTransaction) {
        return ExchangeTransactionData.addTransaction(exchangeTransaction);
    }

    protected List<ExchangeTransaction> getAllTransactions() {
        return ExchangeTransactionData.getTransactions();
    }
}
