package com.unitech.service;

import com.unitech.dto.AccountDto;
import com.unitech.dto.TransferDto;
import com.unitech.dto.TransferResponse;


import java.util.List;

public interface AccountService {

    List<AccountDto> getUserAccounts(String authHeader);

    TransferResponse transfer(TransferDto transferDto);

}
