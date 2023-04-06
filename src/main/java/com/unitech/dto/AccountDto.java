package com.unitech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private BigDecimal amount;
    private String currencyCode;
    private String iban;
    private String swift;
    private Boolean isActive;
}
