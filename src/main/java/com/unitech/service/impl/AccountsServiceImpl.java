package com.unitech.service.impl;

import com.unitech.dto.AccountDto;
import com.unitech.dto.TransferDto;
import com.unitech.dto.TransferResponse;
import com.unitech.exception.InvalidRequestException;
import com.unitech.model.GenericResponse;
import com.unitech.entity.User;
import com.unitech.exception.NotFoundException;
import com.unitech.mapper.AccountMapper;
import com.unitech.repository.AccountRepository;
import com.unitech.repository.UserAccountRepository;
import com.unitech.security.service.JwtService;
import com.unitech.service.AccountService;
import com.unitech.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserAccountRepository userAccountRepository;
    private final UserService userService;
    private final JwtService jwtService;

    private static final AccountMapper accountMapper = AccountMapper.INSTANCE;

    @Override
    public List<AccountDto> getUserAccounts(String authHeader) {
        log.info("ActionLog.getUserAccount.start");

        var user = getUser(authHeader);
        var accounts = userAccountRepository.findByUser(user);
        var accountDtos = new ArrayList<AccountDto>();

        for (var userAccounts : accounts){
            var account = accountRepository.findById(userAccounts.getAccount().getId())
                            .orElseThrow(()-> new NotFoundException("Account not Found"));
            accountDtos.add(accountMapper.mapEntityToDto(account));
        }

        log.info("ActionLog.getUserAccount.end");
        return accountDtos;
    }

    @Override
    public TransferResponse transfer(TransferDto transferDto) {
        log.info("ActionLog.sendToMoney.start transferDto: {}", transferDto);

        var fromAccount = accountRepository.findByIban(transferDto.getFromIban())
                .orElseThrow(()->new NotFoundException("Account Info not Found"));

        if (transferDto.getFromIban().compareTo(transferDto.getToIban()) == 0)
            throw new InvalidRequestException("The transfer is not possible between the same accounts!");

        if (transferDto.getAmount().compareTo(fromAccount.getAmount()) < 0)
            fromAccount.setAmount(fromAccount.getAmount().subtract(transferDto.getAmount()));

        else
            throw new InvalidRequestException("The balance is not enough for this transfer!");

        accountRepository.save(fromAccount);

        var toAccount = accountRepository.findByIban(transferDto.getToIban())
                .orElseThrow(()->new NotFoundException("IBAN not found!"));

        if (!toAccount.getIsActive())
            throw new InvalidRequestException("Account is not active!");

        toAccount.setAmount(toAccount.getAmount().add(transferDto.getAmount()));
        accountRepository.save(toAccount);

        log.info("ActionLog.sendToMoney.end transferDto: {}", transferDto);
        return TransferResponse.builder()
                .message("The process ended successfully!").build();
    }

    private User getUser(String authHeader) {
        String userPin = null;

        if (authHeader != null) {
            var jwt = authHeader.substring(7);
            userPin = jwtService.extractUsername(jwt);
        }

        return userService.getUserByPin(userPin).orElseThrow(() -> new NotFoundException("User not found!"));
    }

}
