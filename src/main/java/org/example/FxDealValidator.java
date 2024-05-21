package org.example;

import java.util.ArrayList;
import java.util.List;

public class FxDealValidator {
    public ValidationResult validate(FxDeal fxDeal) {
        List<String> errors = new ArrayList<>();

        if (fxDeal.getDealId() == null || fxDeal.getDealId().isEmpty()) {
            errors.add("Deal ID is required.");
        }
        if (fxDeal.getFromCurrencyCode() == null || fxDeal.getFromCurrencyCode().isEmpty()) {
            errors.add("From Currency Code is required.");
        }
        if (fxDeal.getToCurrencyCode() == null || fxDeal.getToCurrencyCode().isEmpty()) {
            errors.add("To Currency Code is required.");
        }
        if (fxDeal.getDealTimestamp() == null) {
            errors.add("Deal Timestamp is required.");
        }

        String currencyCodeRegex = "^[A-Z]{3}$";
        if (!fxDeal.getFromCurrencyCode().matches(currencyCodeRegex)) {
            errors.add("From Currency Code must be in ISO 4217 format.");
        }
        if (!fxDeal.getToCurrencyCode().matches(currencyCodeRegex)) {
            errors.add("To Currency Code must be in ISO 4217 format.");
        }

        if (fxDeal.getDealAmount() <= 0) {
            errors.add("Deal Amount must be a positive number.");
        }

        return new ValidationResult(errors.isEmpty(), errors);
    }
}
