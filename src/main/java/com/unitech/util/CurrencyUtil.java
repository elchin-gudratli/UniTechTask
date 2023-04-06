package com.unitech.util;

import com.unitech.dto.CurrencyDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class CurrencyUtil {

    public static List<CurrencyDTO> getCurrencies() {
        return Arrays.asList(
                new CurrencyDTO("Euro", "EUR", BigDecimal.valueOf(1.8200), BigDecimal.valueOf(1.8723), BigDecimal.valueOf(1.8260), BigDecimal.valueOf(1.8845), true),
                new CurrencyDTO("UK pound", "GBP", BigDecimal.valueOf(2.0778), BigDecimal.valueOf(2.1453), BigDecimal.valueOf(2.0714), BigDecimal.valueOf(2.1463), true),
                new CurrencyDTO("Russian ruble", "RUB", BigDecimal.valueOf(0.0205), BigDecimal.valueOf(0.0219), BigDecimal.valueOf(0.0209), BigDecimal.valueOf(0.0225), true),
                new CurrencyDTO("US dollar", "USD", BigDecimal.valueOf(1.6970), BigDecimal.valueOf(1.7020), BigDecimal.valueOf(1.6900), BigDecimal.valueOf(1.7025), true)
        );
    }
}
