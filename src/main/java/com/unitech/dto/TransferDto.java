package com.unitech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * unitech
 * Elchin
 * 4/4/2023 3:02 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

    private String fromIban;
    private String toIban;
    private BigDecimal amount;

}
