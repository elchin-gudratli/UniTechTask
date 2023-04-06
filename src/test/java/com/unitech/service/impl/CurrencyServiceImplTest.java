package com.unitech.service.impl;

import com.unitech.dto.CurrencyDTO;
import com.unitech.entity.Currency;
import com.unitech.mapper.CurrencyMapper;
import com.unitech.model.CurrencyRequest;
import com.unitech.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTest {

    @InjectMocks
    private CurrencyServiceImpl currencyService;
    @Mock
    private CurrencyRepository repository;

    @Test
    void saveCurrencies() {

        var currencyDTOS = Arrays.asList(
                new CurrencyDTO("Euro", "EUR", BigDecimal.valueOf(1.8200), BigDecimal.valueOf(1.8723), BigDecimal.valueOf(1.8260), BigDecimal.valueOf(1.8845),true),
                new CurrencyDTO("UK pound", "GBP", BigDecimal.valueOf(2.0778), BigDecimal.valueOf(2.1453), BigDecimal.valueOf(2.0714), BigDecimal.valueOf(2.1463),true),
                new CurrencyDTO("Russian ruble", "RUB", BigDecimal.valueOf(0.0205), BigDecimal.valueOf(0.0219), BigDecimal.valueOf(0.0209), BigDecimal.valueOf(0.0225),true),
                new CurrencyDTO("US dollar", "USD", BigDecimal.valueOf(1.6970), BigDecimal.valueOf(1.7020), BigDecimal.valueOf(1.6900), BigDecimal.valueOf(1.7025),true)
        );

        var currencies = new ArrayList<Currency>();

        var currencyEntities = CurrencyMapper.INSTANCE.dtoToEntity(currencies, currencyDTOS);
        Mockito.when(repository.saveAll(currencyEntities)).thenReturn(currencyEntities);

        currencyService.saveCurrencies(currencyDTOS);
    }

    @Test
    void getCurrency() {

        var currencyRequest = CurrencyRequest.builder()
                .code("EUR")
                .isCash(false)
                .isBuy(true)
                .amount(BigDecimal.valueOf(1000))
                .build();

        var currencyEntity = Currency.builder()
                .currency("Euro")
                .code("EUR")
                .buyCashRate(BigDecimal.valueOf(1.8200))
                .cellCashRate(BigDecimal.valueOf(1.8723))
                .buyNoCashRate(BigDecimal.valueOf(1.8260))
                .cellNoCashRate(BigDecimal.valueOf(1.8845))
                .build();

        var amount = BigDecimal.valueOf(1000).multiply(currencyEntity.getBuyNoCashRate());

        Mockito.when(repository.findByCodeAndStatusTrue(currencyRequest.getCode())).thenReturn(Optional.of(currencyEntity));
        var currencyResponse = currencyService.getCurrency(currencyRequest);

        assertThat(amount).isEqualTo(currencyResponse.getAmount());
    }
}