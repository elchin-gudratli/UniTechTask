package com.unitech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "DECIMAL(38,4)")
    private BigDecimal amount;

    @Column(name = "currency_code")
    private String currencyCode;

    private String iban;
    private String swift;

    @Column(name= "is_active")
    private Boolean isActive;
}
