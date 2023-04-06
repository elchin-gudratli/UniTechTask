package com.unitech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CurrencyDTO {

    private String currency;
    private String code;
    private BigDecimal buyCashRate;
    private BigDecimal cellCashRate;
    private BigDecimal buyNoCashRate;
    private BigDecimal cellNoCashRate;
    private boolean status;
}
