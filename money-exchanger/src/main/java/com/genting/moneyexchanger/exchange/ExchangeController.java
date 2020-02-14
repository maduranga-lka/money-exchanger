package com.genting.moneyexchanger.exchange;

import com.genting.moneyexchanger.rate.CurrencyRatesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * <p>Controller class which consists with <b>Exchange</b> related REST operations</p>
 */
@RestController
public class ExchangeController {

    public static final String MESSAGE = "";
    public static final String SELLING_AMOUNT_URI = "/exchanges/sell/{currencyCode}/{amount}";
    public static final String BUYING_AMOUNT_URI = "/exchanges/buy/{currencyCode}/{amount}";
    public static final String RET_ALL_TRANSACTIONS_URI = "/exchanges/transactions";
    public static final String NEW_TRANSACTION_URI = "/exchanges/transactions/{currencyCode}/{amount}";
    public static final String ID = "/{id}";

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping(path = SELLING_AMOUNT_URI)
    public ExchangeRate getSellingAmount(@PathVariable String currencyCode, @PathVariable double amount) {
        ExchangeRate exchangeRate = exchangeService.findSellAmountDetailsByCurrencyCode(currencyCode, amount);

        if (exchangeRate.isNil()) {
            throw new CurrencyRatesNotFoundException(MESSAGE);
        }
        return exchangeRate;
    }

    @GetMapping(path = BUYING_AMOUNT_URI)
    public ExchangeRate getBuyingAmount(@PathVariable String currencyCode, @PathVariable double amount) {
        ExchangeRate exchangeRate = exchangeService.findBuyAmountDetailsByCurrencyCode(currencyCode, amount);

        if (exchangeRate.isNil()) {
            throw new CurrencyRatesNotFoundException(MESSAGE);
        }
        return exchangeRate;
    }

    @GetMapping(path = RET_ALL_TRANSACTIONS_URI)
    public List<ExchangeTransaction> getAllTransactions() {
        List<ExchangeTransaction> exchangeTransactions = exchangeService.getAllTransactions();

        if (exchangeTransactions.isEmpty()) {
            throw new TransactionsNotFoundException(MESSAGE);
        }

        return exchangeTransactions;
    }


    @PostMapping(path = NEW_TRANSACTION_URI)
    public ResponseEntity addTransaction(@Valid @RequestBody ExchangeRequest exchangeRequest, @PathVariable String currencyCode, @PathVariable double amount) {
        ExchangeTransaction newTransaction = exchangeService.addTransaction(exchangeRequest, amount, currencyCode);

        if (newTransaction.getTransactionId() > 0) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(newTransaction.getTransactionId()).toUri();
            return ResponseEntity.created(uri).build();
        }

        return ResponseEntity.badRequest().build();
    }
}
