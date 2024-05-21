package org.example;

import java.security.Timestamp;

public class FxDeal {
    private String dealId;
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private Timestamp dealTimestamp;
    private double dealAmount;

    public FxDeal(String dealId, String fromCurrencyCode, String toCurrencyCode, Timestamp dealTimestamp, double dealAmount) {
        this.dealId = dealId;
        this.fromCurrencyCode = fromCurrencyCode;
        this.toCurrencyCode = toCurrencyCode;
        this.dealTimestamp = dealTimestamp;
        this.dealAmount = dealAmount;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    public void setFromCurrencyCode(String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    public Timestamp getDealTimestamp() {
        return dealTimestamp;
    }

    public void setDealTimestamp(Timestamp dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }

    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    public void setToCurrencyCode(String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    public double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(double dealAmount) {
        this.dealAmount = dealAmount;
    }

    @Override
    public String toString() {
        return "FxDeal{" +
                "dealId=" + dealId +
                ", fromCurrencyCode='" + fromCurrencyCode + '\'' +
                ", toCurrencyCode='" + toCurrencyCode + '\'' +
                ", dealTimestamp=" + dealTimestamp +
                ", dealAmount=" + dealAmount +
                '}';
    }
}
