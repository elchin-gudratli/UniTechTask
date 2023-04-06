package com.unitech.mapper;

import com.unitech.dto.CurrencyDTO;
import com.unitech.entity.Currency;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class CurrencyMapper {

    public static final CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    public abstract List<Currency> dtoToEntity(@MappingTarget List<Currency> currencyEntities,
                                               List<CurrencyDTO> currencies);
}
