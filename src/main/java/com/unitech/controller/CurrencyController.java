package com.unitech.controller;

import com.unitech.model.CurrencyRequest;
import com.unitech.model.CurrencyResponse;
import com.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${root.url}/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    public ResponseEntity<CurrencyResponse> getCurrency(@RequestBody CurrencyRequest currencyRequest) {
        return ResponseEntity.ok(currencyService.getCurrency(currencyRequest));
    }
}
