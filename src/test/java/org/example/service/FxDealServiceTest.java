package org.example.service;

import org.example.entity.FxDeal;
import org.example.repository.FxDealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

import static org.mockito.Mockito.*;

class FxDealServiceTest {
    @Mock
    private FxDealRepository fxDealRepository;

    @InjectMocks
    private FxDealService fxDealService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDeal_SuccessfulInsertion() {
        FxDeal fxDeal = new FxDeal("USD", "EUR", new Timestamp(System.currentTimeMillis()), 1000.0);

        when(fxDealRepository.isDealExists(
                fxDeal.getFromCurrencyCode(), fxDeal.getToCurrencyCode(), fxDeal.getDealTimestamp(), fxDeal.getDealAmount())).thenReturn(false);
        when(fxDealRepository.save(fxDeal)).thenReturn(fxDeal);

        fxDealService.saveDeal(fxDeal);

        verify(fxDealRepository, times(1)).save(fxDeal);
    }

    @Test
    void saveDeal_DuplicateEntry() {
        FxDeal fxDeal = new FxDeal("USD", "EUR", new Timestamp(System.currentTimeMillis()), 1000.0);

        when(fxDealRepository.isDealExists(
                fxDeal.getFromCurrencyCode(), fxDeal.getToCurrencyCode(), fxDeal.getDealTimestamp(), fxDeal.getDealAmount())).thenReturn(true);

        fxDealService.saveDeal(fxDeal);

        // Verify that the save method was never called
        verify(fxDealRepository, never()).save(fxDeal);
    }
}