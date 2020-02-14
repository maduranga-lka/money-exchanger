package com.genting.moneyexchanger.exchange;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExchangeRate {

    private double sellRate;
    private double buyRate;
    private double toAmount;
    private String toCurrency;
    private double fromAmount;
    private String fromCurrency;
    @JsonIgnore
    private boolean nil;

    public double getSellRate() {
        return sellRate;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    public double getToAmount() {
        return toAmount;
    }

    public void setToAmount(double toAmount) {
        this.toAmount = toAmount;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public double getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(double fromAmount) {
        this.fromAmount = fromAmount;
    }

    public boolean isNil() {
        return nil;
    }

    public void setNil(boolean nil) {
        this.nil = nil;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
}
