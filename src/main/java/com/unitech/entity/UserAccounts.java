package com.unitech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

/**
 * unitech
 * Elchin
 * 4/4/2023 1:03 PM
 */
@Entity
@Table(name = "user_accounts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class UserAccounts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;

    @Column(name= "is_active")
    private Boolean isActive;

}
