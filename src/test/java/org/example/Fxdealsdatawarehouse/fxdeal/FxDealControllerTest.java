package org.example.Fxdealsdatawarehouse.fxdeal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


@SpringBootTest
@AutoConfigureMockMvc
public class FxDealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FxDealService fxDealService;

    @MockBean
    private FxDealValidator fxDealValidator;

    @BeforeAll
    public static void setUp() {
        // Set default timezone for the tests
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Test
    public void testFindAllDeals() throws Exception {
        List<FxDeal> deals = new ArrayList<>();
        FxDeal fxDeal1 = new FxDeal();
        fxDeal1.setDealId(1);
        fxDeal1.setFromCurrencyCode("USD");
        fxDeal1.setToCurrencyCode("EUR");
        fxDeal1.setDealTimestamp(Timestamp.valueOf("2023-05-21 12:34:56"));
        fxDeal1.setDealAmount(1000.0);

        FxDeal fxDeal2 = new FxDeal();
        fxDeal2.setDealId(2);
        fxDeal2.setFromCurrencyCode("USD");
        fxDeal2.setToCurrencyCode("EUR");
        fxDeal2.setDealTimestamp(Timestamp.valueOf("2020-05-21 12:34:56"));
        fxDeal2.setDealAmount(1.0);

        deals.add(fxDeal1);
        deals.add(fxDeal2);

        Mockito.when(fxDealService.getAllFxDeals()).thenReturn(deals);

        mockMvc.perform(get("/fxdeal/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].dealId").value(1))
                .andExpect(jsonPath("$[0].fromCurrencyCode").value("USD"))
                .andExpect(jsonPath("$[0].toCurrencyCode").value("EUR"))
                .andExpect(jsonPath("$[0].dealTimestamp").value("2023-05-21T12:34:56.000+00:00"))
                .andExpect(jsonPath("$[0].dealAmount").value(1000.0));
    }

    @Test
    public void testFindAllDeals_NoContent() throws Exception {
        Mockito.when(fxDealService.getAllFxDeals()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/fxdeal/all"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAddOneFxDeal_Valid() throws Exception {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setFromCurrencyCode("USD");
        fxDeal.setToCurrencyCode("EUR");
        fxDeal.setDealTimestamp(Timestamp.valueOf("2023-05-21 12:34:56"));
        fxDeal.setDealAmount(1000.0);

        ValidationResult validationResult = new ValidationResult(true, new ArrayList<>());

        Mockito.when(fxDealValidator.validate(Mockito.any(FxDeal.class))).thenReturn(validationResult);

        String fxDealJson = "{ \"fromCurrencyCode\": \"USD\", \"toCurrencyCode\": \"EUR\", \"dealTimestamp\": \"2023-05-21T12:34:56.000+00:00\", \"dealAmount\": 1000.0 }";

        mockMvc.perform(post("/fxdeal/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fxDealJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddOneFxDeal_Invalid() throws Exception {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setFromCurrencyCode("USD");
        fxDeal.setToCurrencyCode("EUR");
        fxDeal.setDealTimestamp(Timestamp.valueOf("2023-05-21 12:34:56"));
        fxDeal.setDealAmount(1000.0);

        List<String> errors = new ArrayList<>();
        errors.add("Invalid data");

        ValidationResult validationResult = new ValidationResult(false, errors);

        Mockito.when(fxDealValidator.validate(Mockito.any(FxDeal.class))).thenReturn(validationResult);

        String fxDealJson = "{ \"fromCurrencyCode\": \"USD\", \"toCurrencyCode\": \"EUR\", \"dealTimestamp\": \"2023-05-21T12:34:56.000+00:00\", \"dealAmount\": 1000.0 }";

        mockMvc.perform(post("/fxdeal/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fxDealJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("[\"Invalid data\"]"));
    }
}

