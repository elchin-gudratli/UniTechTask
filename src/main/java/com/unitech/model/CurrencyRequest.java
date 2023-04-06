package com.unitech.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRequest {

    private String code;

    @JsonProperty("isCash")
    private boolean isCash;

    @JsonProperty("isBuy")
    private boolean isBuy;
    private BigDecimal amount;
}
