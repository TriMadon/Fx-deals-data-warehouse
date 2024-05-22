package org.example.Fxdealsdatawarehouse.fxdeal;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class FxDealValidator {

    public ValidationResult validate(FxDeal fxDeal) {
        List<String> errors = new ArrayList<>();

        validateCurrencyCode(fxDeal.getFromCurrencyCode(), "From Currency Code", errors);
        validateCurrencyCode(fxDeal.getToCurrencyCode(), "To Currency Code", errors);
        validateDealTimestamp(fxDeal.getDealTimestamp(), errors);
        validateDealAmount(fxDeal.getDealAmount(), errors);

        return new ValidationResult(errors.isEmpty(), errors);
    }



    private void validateCurrencyCode(String currencyCode, String fieldName, List<String> errors) {
        String currencyCodeRegex = "^[A-Z]{3}$";
        if (currencyCode == null || currencyCode.isEmpty()) {
            errors.add(fieldName + " is required.");
        } else if (!currencyCode.matches(currencyCodeRegex)) {
            errors.add(fieldName + " must be in ISO 4217 format.");
        }
    }

    private void validateDealTimestamp(Timestamp dealTimestamp, List<String> errors) {
        if (dealTimestamp == null) {
            errors.add("Deal Timestamp is required.");
        }
    }

    private void validateDealAmount(Double dealAmount, List<String> errors) {
        if (dealAmount == null) {
            errors.add("Deal amount is required.");
        } else if (dealAmount <= 0) {
            errors.add("Deal Amount must be a positive number.");
        }
    }
}
