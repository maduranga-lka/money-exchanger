package com.genting.moneyexchanger.exchange;

public class ExchangeRequest {
    private String currency;
    private double buyRate;
    private double sellRate;
    private double amount;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
