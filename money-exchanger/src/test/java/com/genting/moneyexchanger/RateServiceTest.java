package com.genting.moneyexchanger;

import com.genting.moneyexchanger.domain.Currency;
import com.genting.moneyexchanger.rate.RateRepository;
import com.genting.moneyexchanger.rate.RateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RateService.class)
public class RateServiceTest {

    @MockBean
    private RateRepository rateRepository;

    @Autowired
    private RateService rateService;

    @Test
    public void testGetExchangeRateByCurrency(){
        String currencyCode = "USD";
        Currency currency = new Currency(currencyCode,1.1,2.2);
        when(rateRepository.getExchangeRateByCurrency(anyString())).thenReturn(currency);
        Currency currencyResult = rateRepository.getExchangeRateByCurrency(currencyCode);
        Assert.assertEquals(currencyCode,currencyResult.getCurrencyCode());
        Assert.assertEquals(1.1,currencyResult.getBuyRate(),0);
        Assert.assertEquals(2.2,currencyResult.getSellRate(),0);
    }
}
