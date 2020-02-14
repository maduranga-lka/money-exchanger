package com.genting.moneyexchanger.exchange;

import com.genting.moneyexchanger.rate.RateRepository;
import com.genting.moneyexchanger.domain.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Service class for keeping <b>Exchange</b> related functionality</p>
 */
@Service
public class ExchangeService {

    public static final String SGD = "SGD";
    public static final String SELL = "SELL";
    public static final String BUY = "BUY";

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    /**
     * Finds selling amount for the currency and code
     * and amount passed to the respective REST service
     *
     * @param currencyCode currency code
     * @param amountToExchange amount which needs to be exchanged
     * @return exchange details
     */
    public ExchangeRate findSellAmountDetailsByCurrencyCode(String currencyCode, double amountToExchange) {

        return findExchangeAmount(currencyCode, amountToExchange, SELL);  //from SGD

    }

    /**
     * Finds buying amount for the currency and code
     * and amount passed to the respective REST service
     *
     * @param currencyCode currency code
     * @param amountToExchange amount which needs to be exchanged
     * @return exchange details
     */
    public ExchangeRate findBuyAmountDetailsByCurrencyCode(String currencyCode, double amountToExchange) {

        return findExchangeAmount(currencyCode, amountToExchange, BUY);

    }

    /**
     * This method gets the currency code and the amount to be converted in
     * then retrieves the applicable exchange rate from the respective
     * repository service then based on the exchange type, calculates the exchanged
     * amount
     *
     * @param currencyCode currency code
     * @param amountToExchange amount which needs to be exchanged
     * @param exchangeType {@code BUY} or {@code SELL}
     * @return exchange details
     */
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

    /**
     * Builds the {@link ExchangeRate} object with
     * given details
     *
     * @param fromCurrency currency to be converted to
     * @param toCurrency currency after converting
     * @param amountToExchange amount to be converted to
     * @param currency currency details
     * @param amountAfterExchanged amount after converting
     * @return exchange rate details
     */
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

    /**
     * Builds {@link ExchangeRate} when details are
     * not found ~ null object pattern
     *
     * @return exchange rate details
     */
    private ExchangeRate buildNilExchangeResponse() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setNil(true);
        return exchangeRate;
    }

    /**
     * Builds the transaction object and saves in the database
     *
     * @param exchangeRequest exchange request
     * @param amountToExchange amount to be converted to
     * @param currencyCode currency code
     * @return transaction details after saving
     */
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

    /**
     * Retrieves all the transaction details using the
     * respective repository
     *
     * @return all the transactions
     */
    protected List<ExchangeTransaction> getAllTransactions() {

        return exchangeRepository.getAllTransactions();

    }

    /**
     * Builds {@link ExchangeTransaction} when details are
     * not found ~ null object pattern
     *
     * @return transaction details after saving
     */
    private ExchangeTransaction buildNilExchangeTransactionResponse() {
        ExchangeTransaction exchangeTransaction = new ExchangeTransaction();
        exchangeTransaction.setNil(true);
        return exchangeTransaction;
    }
}
