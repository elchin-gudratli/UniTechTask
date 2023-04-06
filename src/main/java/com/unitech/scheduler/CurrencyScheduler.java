package com.unitech.scheduler;


import com.unitech.dto.CurrencyDTO;
import com.unitech.service.CurrencyService;
import com.unitech.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyScheduler {

    private final CurrencyService currencyService;

    @Scheduled(fixedDelayString = "${scheduler.currency.fixedDelay}")
    public void startFlow() {
        // get dummy data from central bank
        List<CurrencyDTO> currencies = CurrencyUtil.getCurrencies();
        currencyService.saveCurrencies(currencies);
    }
}
