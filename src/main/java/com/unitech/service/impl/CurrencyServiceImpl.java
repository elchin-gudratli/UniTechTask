package com.unitech.service.impl;


import com.unitech.dto.CurrencyDTO;
import com.unitech.entity.Currency;
import com.unitech.exception.NotFoundException;
import com.unitech.model.CurrencyRequest;
import com.unitech.model.CurrencyResponse;
import com.unitech.mapper.CurrencyMapper;
import com.unitech.repository.CurrencyRepository;
import com.unitech.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository repository;

    @Override
    public void saveCurrencies(List<CurrencyDTO> currencyDTOs) {
        log.info("ActionLog.saveCurrencies.start record count: {}", currencyDTOs.size());

        var currencies = getCurrencyEntities();
        var currencyEntities = CurrencyMapper.INSTANCE.dtoToEntity(currencies, currencyDTOs);
        repository.saveAll(currencyEntities);

        log.info("ActionLog.saveCurrencies.end record count: {}", currencyDTOs.size());
    }

    @Override
    public CurrencyResponse getCurrency(CurrencyRequest currencyRequest) {
        log.info("ActionLog.getCurrency.start");

        var currencyEntity = repository.findByCodeAndStatusTrue(currencyRequest.getCode())
                .orElseThrow(() -> new NotFoundException("Currency not found!"));

        var amount = calculateAmount(currencyRequest, currencyEntity);
        log.info("ActionLog.getCurrency.end");

        return CurrencyResponse.builder()
                .amount(amount)
                .build();
    }

    private List<Currency> getCurrencyEntities() {
        return repository.findAll();
    }

    private BigDecimal calculateAmount(CurrencyRequest currencyRequest,
                                       Currency currencyEntity) {

        boolean isCash = currencyRequest.isCash();
        boolean isBuy = currencyRequest.isBuy();

        if (isCash && isBuy)
            return currencyRequest.getAmount().multiply(currencyEntity.getBuyCashRate());
        else if (isCash)
            return currencyRequest.getAmount().multiply(currencyEntity.getCellCashRate());
        else if (isBuy)
            return currencyRequest.getAmount().multiply(currencyEntity.getBuyNoCashRate());
        else
            return currencyRequest.getAmount().multiply(currencyEntity.getCellNoCashRate());
    }
}
