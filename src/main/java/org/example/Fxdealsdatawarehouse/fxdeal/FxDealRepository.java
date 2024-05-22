package org.example.Fxdealsdatawarehouse.fxdeal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FxDealRepository extends JpaRepository<FxDeal, Integer> {
    boolean existsByFromCurrencyCodeAndToCurrencyCodeAndDealTimestampAndDealAmount(String fromCurrencyCode, String toCurrencyCode, Timestamp dealTimestamp, double dealAmount);
}
