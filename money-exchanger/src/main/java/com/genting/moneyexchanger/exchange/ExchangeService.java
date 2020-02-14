package com.genting.moneyexchanger.exchange;

import com.genting.moneyexchanger.rate.RateRepository;
import com.genting.moneyexchanger.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeService {

    public static final String SGD = "SGD";
    public static final String SELL = "SELL";
    public static final String BUY = "BUY";

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    public ExchangeRate findSellAmountDetailsByCurrencyCode(String currencyCode, double amountToExchange) {

        return findExchangeAmount(currencyCode, amountToExchange, SELL);  //from SGD

    }

    public ExchangeRate findBuyAmountDetailsByCurrencyCode(String currencyCode, double amountToExchange) {

        return findExchangeAmount(currencyCode, amountToExchange, BUY);

    }

    private ExchangeRate findExchangeAmount(String currencyCode, double amountToExchange, String exchangeType) {
        Currency currency = rateRepository.getExchangeRateByCurrency(currencyCode);
        if (currency != null) {
            double exchangeAmount = 0;
            String fromCurrency;
            String toCurrency;
            if (exchangeType.equals(SELL)) {
                exchangeAmount = amountToExchange / currency.getSellRate();
                toCurrency = currencyCode;
                fromCurrency = SGD;
            } else if (exchangeType.equals(BUY)) {
                exchangeAmount = currency.getBuyRate() * amountToExchange;
                toCurrency = SGD;
                fromCurrency = currencyCode;
            } else {
                return buildNilExchangeResponse();
            }
            return buildExchangeResponse(fromCurrency, toCurrency, amountToExchange, currency, exchangeAmount);
        }

        return buildNilExchangeResponse();
    }

    private ExchangeRate buildExchangeResponse(String fromCurrency, String toCurrency, double amountToExchange, Currency currency, double amountAfterExchanged) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setSellRate(currency.getSellRate());
        exchangeRate.setBuyRate(currency.getBuyRate());
        exchangeRate.setFromCurrency(fromCurrency);
        exchangeRate.setFromAmount(amountToExchange);
        exchangeRate.setToCurrency(toCurrency);
        exchangeRate.setToAmount(amountAfterExchanged);
        exchangeRate.setNil(false);
        return exchangeRate;
    }

    private ExchangeRate buildNilExchangeResponse() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setNil(true);
        return exchangeRate;
    }

    public ExchangeTransaction addTransaction(ExchangeRequest exchangeRequest, double amountToExchange, String currencyCode) {
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction();
        exchangeTransaction.setSellRate(exchangeRequest.getSellRate());
        exchangeTransaction.setBuyRate(exchangeRequest.getBuyRate());
        exchangeTransaction.setFromAmount(amountToExchange);
        exchangeTransaction.setToAmount(exchangeRequest.getAmount());
        exchangeTransaction.setFromCurrencyCode(SGD);
        exchangeTransaction.setToCurrencyCode(currencyCode);
        Long transactionId = exchangeRepository.addTransaction(exchangeTransaction);

        if (transactionId > 0) {
            exchangeTransaction.setTransactionId(transactionId);
            return exchangeTransaction;
        } else { // transaction has not happened
            return buildNilExchangeTransactionResponse();
        }
    }

    protected List<ExchangeTransaction> getAllTransactions() {

        return exchangeRepository.getAllTransactions();

    }

    private ExchangeTransaction buildNilExchangeTransactionResponse() {
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction();
        exchangeTransaction.setNil(true);
        return exchangeTransaction;
    }
}
