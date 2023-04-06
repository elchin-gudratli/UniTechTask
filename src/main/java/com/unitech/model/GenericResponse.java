package com.unitech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * unitech
 * Elchin
 * 4/4/2023 12:31 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse <T>{

    private String error;
    private T data;
}
