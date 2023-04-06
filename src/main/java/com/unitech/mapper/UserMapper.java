package com.unitech.mapper;

import com.unitech.dto.UserResponse;
import com.unitech.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract UserResponse mapEntityToDto(User user);
}
