package com.unitech.controller;

import com.unitech.dto.AccountDto;
import com.unitech.dto.TransferDto;
import com.unitech.dto.TransferResponse;
import com.unitech.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * unitech
 * Elchin
 * 4/4/2023 2:36 PM
 */
@RestController
@RequestMapping("${root.url}/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getUserAccounts(@NonNull HttpServletRequest request){

        return ResponseEntity.ok(accountService.getUserAccounts(request.getHeader("Authorization")));
    }

    @PostMapping
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferDto transferDto){
        return ResponseEntity.ok(accountService.transfer(transferDto));
    }

}
