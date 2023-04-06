package com.unitech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * unitech
 * Elchin
 * 4/4/2023 12:26 PM
 */
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
