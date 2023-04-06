package com.unitech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String pin;
    private String serial;
    private String firstName;
    private String lastName;
    private String phone;
}
