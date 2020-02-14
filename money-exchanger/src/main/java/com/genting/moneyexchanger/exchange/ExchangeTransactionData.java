package com.genting.moneyexchanger.exchange;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Helper class that stores currency transaction data</p>
 */
public class ExchangeTransactionData {

    private ExchangeTransactionData() {
    }

    private static List<ExchangeTransaction> exchangeTransactions;

    static {
        exchangeTransactions = new ArrayList();
    }

    public static List<ExchangeTransaction> getTransactions() {
        return exchangeTransactions;
    }

    public static Long addTransaction(ExchangeTransaction exchangeTransaction) {
        Long transactionId = Long.valueOf(exchangeTransactions.size() + 1);
        exchangeTransaction.setTransactionId(transactionId);
        if (exchangeTransactions.add(exchangeTransaction)) {
            return transactionId;
        } else {
            return 0l;
        }
    }
}
