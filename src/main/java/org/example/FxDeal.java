package org.example;

import java.sql.Timestamp;

public class FxDeal {
    private Integer dealId;
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private Timestamp dealTimestamp;
    private double dealAmount;

    public FxDeal(String fromCurrencyCode, String toCurrencyCode, Timestamp dealTimestamp, double dealAmount) {
        this.fromCurrencyCode = fromCurrencyCode;
        this.toCurrencyCode = toCurrencyCode;
        this.dealTimestamp = dealTimestamp;
        this.dealAmount = dealAmount;
    }

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = Integer.valueOf(dealId);
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
