package org.example.repository;

import org.example.entity.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface FxDealRepository extends CrudRepository<FxDeal, Integer> {
    boolean isDealExists(String fromCurrencyCode, String toCurrencyCode, Timestamp dealTimestamp, double dealAmount);
}
