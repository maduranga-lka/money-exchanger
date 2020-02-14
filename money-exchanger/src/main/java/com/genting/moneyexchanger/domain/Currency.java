package com.genting.moneyexchanger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p>This class is for storing basic currency details</p>
 */
public class Currency {

    public Currency() {
    }

    public Currency(String currencyCode, double buyRate, double sellRate) {
        this.currencyCode = currencyCode;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }

    private String currencyCode;
    private double buyRate;
    private double sellRate;
    @JsonIgnore
    private boolean nil;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    public double getSellRate() {
        return sellRate;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }

    public boolean isNil() {
        return nil;
    }

    public void setNil(boolean nil) {
        this.nil = nil;
    }
}
