package com.unitech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String pin;
    private String password;
    private String serial;
    private String firstName;
    private String lastName;
    private String phone;
}
