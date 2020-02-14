package com.genting.moneyexchanger;

import com.genting.moneyexchanger.domain.Currency;
import com.genting.moneyexchanger.exchange.*;
import com.genting.moneyexchanger.rate.RateRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExchangeService.class)
public class ExchangeServiceTest {

    @MockBean
    private RateRepository rateRepositoryMock;

    @MockBean
    private ExchangeRepository exchangeRepositoryMock;

    @Autowired
    private ExchangeService exchangeService;

    @Rule
    public TestName name = new TestName();

    private static final String CURRENCY_CODE_USD = "USD";
    private static final double BUY_RATE = 1.3392;
    private static final double SELL_RATE = 1.3574;

    @Before
    public void setUp() {
        if (name.getMethodName().equalsIgnoreCase("findSellAmountDetailsByCurrencyCode") ||
                name.getMethodName().equalsIgnoreCase("findBuyAmountDetailsByCurrencyCode")) {
            when(rateRepositoryMock.getExchangeRateByCurrency(any())).thenReturn(new Currency(CURRENCY_CODE_USD, BUY_RATE, SELL_RATE));
        }
    }

    @Test
    public void findSellAmountDetailsByCurrencyCode() {

        double amountToExchange = 1.3574;
        ExchangeRate exchangeRate = exchangeService.findSellAmountDetailsByCurrencyCode(CURRENCY_CODE_USD, amountToExchange);
        Assert.assertEquals(1.0, exchangeRate.getToAmount(), 0);
        Assert.assertEquals(CURRENCY_CODE_USD, exchangeRate.getToCurrency());

    }

    @Test
    public void findBuyAmountDetailsByCurrencyCode() {

        double amountToExchange = 1;
        ExchangeRate exchangeRate = exchangeService.findBuyAmountDetailsByCurrencyCode(CURRENCY_CODE_USD, amountToExchange);
        Assert.assertEquals(1.3392, exchangeRate.getToAmount(), 0);
        Assert.assertEquals("SGD", exchangeRate.getToCurrency());
    }

    @Test
    public void testAddTransaction() {
        ExchangeRequest exchangeRequest = new ExchangeRequest();
        exchangeRequest.setAmount(100);
        exchangeRequest.setBuyRate(1.11);
        exchangeRequest.setCurrency(CURRENCY_CODE_USD);
        exchangeRequest.setSellRate(2.22);
        when(exchangeRepositoryMock.addTransaction(any())).thenReturn(new Long(1));
        ExchangeTransaction exchangeTransaction = exchangeService.addTransaction(exchangeRequest, 100, CURRENCY_CODE_USD);
        Assert.assertEquals(1.0,exchangeTransaction.getTransactionId(),0);

    }
}
