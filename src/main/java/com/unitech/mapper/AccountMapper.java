package com.unitech.mapper;

import com.unitech.dto.AccountDto;
import com.unitech.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * unitech
 * Elchin
 * 4/4/2023 3:50 PM
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class AccountMapper {

    public static final AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    public abstract AccountDto mapEntityToDto(Account accounts);
}
