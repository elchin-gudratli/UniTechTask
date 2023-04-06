package com.unitech.entity;

import com.unitech.enums.Role;
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
 * 4/4/2023 3:50 PM
 */

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pin;
    private String password;
    private String serial;
    private String firstName;
    private String lastName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

}
