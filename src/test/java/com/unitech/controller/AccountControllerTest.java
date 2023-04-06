package com.unitech.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unitech.dto.AccountDto;
import com.unitech.dto.TransferDto;
import com.unitech.dto.TransferResponse;
import com.unitech.security.service.JwtService;
import com.unitech.service.AccountService;
import com.unitech.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters = false)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    @MockBean
    private AccountService accountService;

    @Test
    void getUserAccounts() throws Exception {

        var request = new MockHttpServletRequest();
        request.setParameter("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");

        var accountDtos = Arrays.asList(new AccountDto(1L, BigDecimal.valueOf(100), "AZN", "AZERTY5667476747747", "ASFG567", true),
                new AccountDto(2L, BigDecimal.valueOf(100), "USD", "AZERHH89009890998", "DSFG567", true));

        var responseJson = objectMapper.writeValueAsString(accountDtos);

        var requestBuilder = MockMvcRequestBuilders.get("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        Mockito.when(accountService.getUserAccounts(request.getHeader("Authorization"))).thenReturn(accountDtos);

        var response = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn().getResponse();

        JSONAssert.assertEquals(responseJson, response.getContentAsString(), false);
    }

    @Test
    void transfer() throws Exception {
        var transferRequest = TransferDto.builder()
                .fromIban("AZBN8900567")
                .toIban("AZBN8900588")
                .amount(BigDecimal.valueOf(100))
                .build();

        var transferResponse = TransferResponse.builder()
                .message("The process ended successfully!")
                .build();

        var requestJson = objectMapper.writeValueAsString(transferRequest);
        var responseJson = objectMapper.writeValueAsString(transferResponse);

        var requestBuilder = MockMvcRequestBuilders.post("/api/v1/account")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        Mockito.when(accountService.transfer(transferRequest)).thenReturn(transferResponse);

        var response = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn().getResponse();

        JSONAssert.assertEquals(responseJson, response.getContentAsString(), false);
    }
}