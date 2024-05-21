package org.example.component;

import org.example.Fxdealsdatawarehouse.FxDealsDataWarehouseApplication;
import org.example.entity.FxDeal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Timestamp;
import java.time.Instant;


class FxDealValidatorTest {

    private final FxDealValidator validator = new FxDealValidator();

    @Test
    public void testValidFxDeal() {
        FxDeal validFxDeal = new FxDeal("USD", "EUR", Timestamp.from(Instant.now()), 1000.0);
        ValidationResult result = validator.validate(validFxDeal);
        assertTrue(result.isValid());
        assertTrue(result.getErrors().isEmpty());
    }

    @Test
    public void testMissingFields() {
        FxDeal invalidFxDeal = new FxDeal( "", "EUR", null, 1000.0);
        ValidationResult result = validator.validate(invalidFxDeal);
        System.out.println(result.getErrors());
        assertFalse(result.isValid());
        assertFalse(result.getErrors().isEmpty());
    }

    @Test
    public void testFxDealAmount() {
        FxDeal invalidDealAmount = new FxDeal("USD", "EUR", Timestamp.from(Instant.now()), 0);
        ValidationResult result = validator.validate(invalidDealAmount);
        assertFalse(result.isValid());
        assertFalse(result.getErrors().isEmpty());
    }

}