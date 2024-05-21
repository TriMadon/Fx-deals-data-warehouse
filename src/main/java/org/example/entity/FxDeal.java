package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "fx_deals")
public class FxDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deal_id", nullable = false)
    private Integer dealId;

    @Column(name = "from_currency_code", nullable = false)
    private String fromCurrencyCode;

    @Column(name = "to_currency_code", nullable = false)
    private String toCurrencyCode;

    @Column(name = "deal_timestamp", nullable = false)
    private Timestamp dealTimestamp;

    @Column(name = "deal_amount", nullable = false)
    private Double dealAmount;

    public FxDeal() {
    }

    public FxDeal(String fromCurrencyCode, String toCurrencyCode, Timestamp dealTimestamp, double dealAmount) {
        this.fromCurrencyCode = fromCurrencyCode;
        this.toCurrencyCode = toCurrencyCode;
        this.dealTimestamp = dealTimestamp;
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
