package org.example.service;

import org.example.entity.FxDeal;
import org.example.repository.FxDealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FxDealService {

    private static final Logger logger = LoggerFactory.getLogger(FxDealService.class);

    private final FxDealRepository fxDealRepository;

    @Autowired
    public FxDealService(FxDealRepository fxDealRepository) {
        this.fxDealRepository = fxDealRepository;
    }

    public void saveDeal(FxDeal fxDeal) {
        if (fxDealRepository.isDealExists(
                fxDeal.getFromCurrencyCode(), fxDeal.getToCurrencyCode(), fxDeal.getDealTimestamp(), fxDeal.getDealAmount())) {
            logger.warn("Deal already exists, skipping insertion: {}", fxDeal);
            return;
        }
        fxDealRepository.save(fxDeal);
    }
}
