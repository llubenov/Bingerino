package com.cognizant.bingo.account.service;

import com.cognizant.bingo.account.domain.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAccountService {
    ResponseEntity<Object> createAccount(String account);

    Account retrieveAccount(Long accountId);

    ResponseEntity<Object> updateAccount(Long accountId, Account account);

    ResponseEntity<Object> deleteAccount(Long accountId);

    List<Account> getAllAccounts();
}
