package org.example.Fxdealsdatawarehouse.fxdeal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fxdeal")
public class FxDealController {

    private static final Logger logger = LoggerFactory.getLogger(FxDealController.class);

    @Autowired
    FxDealService fxDealService;
    @Autowired
    FxDealValidator fxDealValidator;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllDeals() {
        try {
            List<FxDeal> fxDeals = fxDealService.getAllFxDeals();
            logger.info("Retrieved {} FX deals", fxDeals.size());
            if (fxDeals.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No deals found");
            }
            return ResponseEntity.ok(fxDeals);
        } catch (Exception e) {
            logger.error("Failed to retrieve FX deals", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while retrieving deals.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOneFxDeal(@RequestBody FxDeal fxDeal) {
        ValidationResult validationResult = fxDealValidator.validate(fxDeal);

        if (!validationResult.isValid()) {
            logger.warn("Invalid FX deal: {}", validationResult.getErrors());
            return ResponseEntity.badRequest().body(validationResult.getErrors());
        }

        try {
            fxDealService.saveDeal(fxDeal);
            logger.info("FX deal added successfully: {}", fxDeal);
            return ResponseEntity.status(HttpStatus.CREATED).body(fxDeal);
        } catch (Exception e) {
            logger.error("Failed to add FX deal", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }
}