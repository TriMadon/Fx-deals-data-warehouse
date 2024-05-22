package org.example.Fxdealsdatawarehouse.fxdeal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
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

        when(fxDealRepository.existsByFromCurrencyCodeAndToCurrencyCodeAndDealTimestampAndDealAmount(
                fxDeal.getFromCurrencyCode(), fxDeal.getToCurrencyCode(), fxDeal.getDealTimestamp(), fxDeal.getDealAmount())).thenReturn(false);
        when(fxDealRepository.save(fxDeal)).thenReturn(fxDeal);

        fxDealService.saveDeal(fxDeal);

        verify(fxDealRepository, times(1)).save(fxDeal);
    }

    @Test
    void saveDeal_DuplicateEntry() {
        FxDeal fxDeal = new FxDeal("USD", "EUR", new Timestamp(System.currentTimeMillis()), 1000.0);

        when(fxDealRepository.existsByFromCurrencyCodeAndToCurrencyCodeAndDealTimestampAndDealAmount(
                fxDeal.getFromCurrencyCode(), fxDeal.getToCurrencyCode(), fxDeal.getDealTimestamp(), fxDeal.getDealAmount())).thenReturn(true);

        fxDealService.saveDeal(fxDeal);

        // Verify that the save method was never called
        verify(fxDealRepository, never()).save(fxDeal);
    }

    @Test
    public void testGetAllFxDeals_NoDeals() {
        when(fxDealRepository.findAll()).thenReturn(Collections.emptyList());

        List<FxDeal> fxDeals = fxDealService.getAllFxDeals();

        assertTrue(fxDeals.isEmpty());
    }

    @Test
    public void testGetAllFxDeals_SingleDeal() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setFromCurrencyCode("USD");
        fxDeal.setToCurrencyCode("EUR");
        fxDeal.setDealTimestamp(Timestamp.valueOf("2023-05-21 12:34:56"));
        fxDeal.setDealAmount(1000.0);

        when(fxDealRepository.findAll()).thenReturn(Collections.singletonList(fxDeal));

        List<FxDeal> fxDeals = fxDealService.getAllFxDeals();

        assertEquals(1, fxDeals.size());
        assertEquals("USD", fxDeals.get(0).getFromCurrencyCode());
        assertEquals("EUR", fxDeals.get(0).getToCurrencyCode());
        assertEquals(Timestamp.valueOf("2023-05-21 12:34:56"), fxDeals.get(0).getDealTimestamp());
        assertEquals(1000.0, fxDeals.get(0).getDealAmount());
    }

    @Test
    public void testGetAllFxDeals_MultipleDeals() {
        FxDeal fxDeal1 = new FxDeal();
        fxDeal1.setFromCurrencyCode("USD");
        fxDeal1.setToCurrencyCode("EUR");
        fxDeal1.setDealTimestamp(Timestamp.valueOf("2023-05-21 12:34:56"));
        fxDeal1.setDealAmount(1000.0);

        FxDeal fxDeal2 = new FxDeal();
        fxDeal2.setFromCurrencyCode("GBP");
        fxDeal2.setToCurrencyCode("JPY");
        fxDeal2.setDealTimestamp(Timestamp.valueOf("2023-06-22 14:34:56"));
        fxDeal2.setDealAmount(2000.0);

        when(fxDealRepository.findAll()).thenReturn(Arrays.asList(fxDeal1, fxDeal2));

        List<FxDeal> fxDeals = fxDealService.getAllFxDeals();

        assertEquals(2, fxDeals.size());
        assertEquals("USD", fxDeals.get(0).getFromCurrencyCode());
        assertEquals("EUR", fxDeals.get(0).getToCurrencyCode());
        assertEquals(Timestamp.valueOf("2023-05-21 12:34:56"), fxDeals.get(0).getDealTimestamp());
        assertEquals(1000.0, fxDeals.get(0).getDealAmount());

        assertEquals("GBP", fxDeals.get(1).getFromCurrencyCode());
        assertEquals("JPY", fxDeals.get(1).getToCurrencyCode());
        assertEquals(Timestamp.valueOf("2023-06-22 14:34:56"), fxDeals.get(1).getDealTimestamp());
        assertEquals(2000.0, fxDeals.get(1).getDealAmount());
    }

    @Test
    public void testGetAllFxDeals_RepositoryReturnsNull() {
        when(fxDealRepository.findAll()).thenReturn(null);

        List<FxDeal> fxDeals = fxDealService.getAllFxDeals();

        assertNull(fxDeals);
    }
}
