package com.matsuri.TradePrice;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matsuri.controller.PriceController;
import com.matsuri.model.Price;
import com.matsuri.service.PriceService;

public class PriceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
                mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

   
    @Test
    public void testGetPriceByIsin() throws Exception {
        List<Price> prices = Arrays.asList(new Price("BLOOMBERG", "US-000402625-0", 100.0, LocalDateTime.now()));
        when(priceService.getPriceByIsin("123")).thenReturn(prices);

        mockMvc.perform(get("/isin/123"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPriceByIsin_InternalServerError() throws Exception {
        when(priceService.getPriceByIsin("123")).thenThrow(new RuntimeException());

        mockMvc.perform(get("/isin/123"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetPriceByVendor() throws Exception {
        List<Price> prices = Arrays.asList(new Price("BLOOMBERG", "US-000402625-0", 100.0, LocalDateTime.now()));
        when(priceService.getPriceByVendor("456")).thenReturn(prices);

        mockMvc.perform(get("/vendor/456"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPriceByVendor_InternalServerError() throws Exception {
        when(priceService.getPriceByVendor("456")).thenThrow(new RuntimeException());

        mockMvc.perform(get("/vendor/456"))
                .andExpect(status().isInternalServerError());
    }
}

