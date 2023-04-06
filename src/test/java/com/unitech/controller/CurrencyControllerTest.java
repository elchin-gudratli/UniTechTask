package com.unitech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unitech.model.CurrencyRequest;
import com.unitech.model.CurrencyResponse;
import com.unitech.security.service.JwtService;
import com.unitech.service.CurrencyService;
import com.unitech.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyController.class)
@AutoConfigureMockMvc(addFilters = false)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    @MockBean
    private CurrencyService currencyService;

    @Test
    void getCurrency() throws Exception{
        var currencyRequest = CurrencyRequest.builder()
                .code("USD")
                .isCash(false)
                .isBuy(true)
                .amount(BigDecimal.valueOf(100.50))
                .build();

        var currencyResponse = CurrencyResponse.builder()
                .amount(BigDecimal.valueOf(100.50))
                .build();

        var requestJson = objectMapper.writeValueAsString(currencyRequest);
        var responseJson = objectMapper.writeValueAsString(currencyResponse);

        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/currency")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        Mockito.when(currencyService.getCurrency(currencyRequest)).thenReturn(currencyResponse);

        var response = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn().getResponse();

        JSONAssert.assertEquals(responseJson, response.getContentAsString(), false);
    }
}