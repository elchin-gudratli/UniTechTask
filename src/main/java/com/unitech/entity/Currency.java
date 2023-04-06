package com.unitech.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency")
public class Currency {

    @Id
    private String code;

    private String currency;

    @Column(name = "buy_cash_rate", columnDefinition = "DECIMAL(38,4)")
    private BigDecimal buyCashRate;

    @Column(name = "cell_cash_rate", columnDefinition = "DECIMAL(38,4)")
    private BigDecimal cellCashRate;

    @Column(name = "buy_no_cash_rate", columnDefinition = "DECIMAL(38,4)")
    private BigDecimal buyNoCashRate;

    @Column(name = "cell_no_cash_rate", columnDefinition = "DECIMAL(38,4)")
    private BigDecimal cellNoCashRate;

    private boolean status;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
