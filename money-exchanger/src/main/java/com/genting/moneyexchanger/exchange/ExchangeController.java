package com.genting.moneyexchanger.exchange;

import com.genting.moneyexchanger.rate.CurrencyRatesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ExchangeController {

    public static final String MESSAGE = "";

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping(path = "/exchanges/sell/{currencyCode}/{amount}")
    public ExchangeRate getSellingAmount(@PathVariable String currencyCode, @PathVariable double amount) {
        ExchangeRate exchangeRate = exchangeService.findSellAmountDetailsByCurrencyCode(currencyCode, amount);

        if (exchangeRate.isNil()) {
            throw new CurrencyRatesNotFoundException(MESSAGE);
        }
        return exchangeRate;
    }

    @GetMapping(path = "/exchanges/buy/{currencyCode}/{amount}")
    public ExchangeRate getBuyingAmount(@PathVariable String currencyCode, @PathVariable double amount) {
        ExchangeRate exchangeRate = exchangeService.findBuyAmountDetailsByCurrencyCode(currencyCode, amount);

        if (exchangeRate.isNil()) {
            throw new CurrencyRatesNotFoundException(MESSAGE);
        }
        return exchangeRate;
    }

    @GetMapping(path = "/exchanges/transactions")
    public List<ExchangeTransaction> getAllTransactions() {
        List<ExchangeTransaction> exchangeTransactions = exchangeService.getAllTransactions();

        if (exchangeTransactions.isEmpty()) {
            throw new TransactionsNotFoundException(MESSAGE);
        }

        return exchangeTransactions;
    }


    @PostMapping(path = "/exchanges/transactions/{currencyCode}/{amount}")
    public ResponseEntity addTransaction(@Valid @RequestBody ExchangeRequest exchangeRequest, @PathVariable String currencyCode, @PathVariable double amount) {
        ExchangeTransaction newTransaction = exchangeService.addTransaction(exchangeRequest, amount, currencyCode);

        if (newTransaction.getTransactionId() > 0) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTransaction.getTransactionId()).toUri();
            return ResponseEntity.created(uri).build();
        }

        return ResponseEntity.badRequest().build();
    }
}
