package com.unitech.repository;

import com.unitech.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Optional<Currency> findByCodeAndStatusTrue(String code);
}
