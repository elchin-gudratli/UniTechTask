package com.unitech.service.impl;

import com.unitech.dto.TransferDto;
import com.unitech.dto.TransferResponse;
import com.unitech.entity.Account;
import com.unitech.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AccountsServiceImplTest {

    @InjectMocks
    private AccountsServiceImpl accountsService;
    @Mock
    private AccountRepository accountRepository;

    @Test
    void transfer() {
        var successMessage = "The process ended successfully!";

        var transferDto = TransferDto.builder()
                .fromIban("AZBBRT568983003")
                .toIban("AZBBRT568983056")
                .amount(BigDecimal.valueOf(50))
                .build();

        var fromAccount = Account.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(1000))
                .currencyCode("AZN")
                .iban("AZBBRT568983003")
                .swift("SWIFT223")
                .isActive(true)
                .build();

        var toAccount = Account.builder()
                .id(2L)
                .amount(BigDecimal.valueOf(50))
                .currencyCode("AZN")
                .iban("AZBBRT568983056")
                .swift("SWIFT2YU")
                .isActive(true)
                .build();

        Mockito.when(accountRepository.findByIban(transferDto.getFromIban())).thenReturn(Optional.ofNullable(fromAccount));
        Mockito.when(accountRepository.save(fromAccount)).thenReturn(fromAccount);

        Mockito.when(accountRepository.findByIban(transferDto.getToIban())).thenReturn(Optional.ofNullable(toAccount));
        Mockito.when(accountRepository.save(toAccount)).thenReturn(toAccount);

        TransferResponse transferResponse = accountsService.transfer(transferDto);

        assertThat(successMessage).isEqualTo(transferResponse.getMessage());
    }
}