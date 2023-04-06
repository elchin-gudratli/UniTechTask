package com.unitech.service;


import com.unitech.dto.CurrencyDTO;
import com.unitech.model.CurrencyRequest;
import com.unitech.model.CurrencyResponse;

import java.util.List;

public interface CurrencyService {

    void saveCurrencies(List<CurrencyDTO> currencies);

    CurrencyResponse getCurrency(CurrencyRequest currency);
}
