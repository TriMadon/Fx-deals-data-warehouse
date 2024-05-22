package org.example.Fxdealsdatawarehouse.fxdeal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FxDealService {

    private static final Logger logger = LoggerFactory.getLogger(FxDealService.class);

    @Autowired
    FxDealRepository fxDealRepository;

    public void saveDeal(FxDeal fxDeal) {
        try {
            if (fxDealRepository.existsByFromCurrencyCodeAndToCurrencyCodeAndDealTimestampAndDealAmount(
                    fxDeal.getFromCurrencyCode(), fxDeal.getToCurrencyCode(), fxDeal.getDealTimestamp(), fxDeal.getDealAmount())) {
                logger.warn("Deal already exists, skipping insertion: {}", fxDeal);
                return;
            }
            fxDealRepository.save(fxDeal);
            logger.info("Successfully saved deal: {}", fxDeal);
        } catch (Exception e) {
            logger.error("Error saving deal: {}", fxDeal, e);
            throw new RuntimeException("Error saving deal", e);
        }
    }

    public List<FxDeal> getAllFxDeals() {
        try {
            List<FxDeal> fxDeals = fxDealRepository.findAll();
            if (fxDeals.isEmpty()) {
                logger.warn("There are no recorded deals");
            } else {
                logger.info("Retrieved all deals successfully");
            }
            return fxDeals;
        } catch (Exception e) {
            logger.error("Error retrieving all deals", e);
            return null;
        }
    }
}
