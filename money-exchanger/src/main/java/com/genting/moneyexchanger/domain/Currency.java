package com.genting.moneyexchanger.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Currency {

    public Currency() {
    }

    public Currency(String currency, double buyRate, double sellRate) {
        this.currency = currency;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }

    private String currency;
    private double buyRate;
    private double sellRate;
    @JsonIgnore
    private boolean nil;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
