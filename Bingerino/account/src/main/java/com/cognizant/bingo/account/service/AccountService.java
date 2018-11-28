package com.cognizant.bingo.account.service;

import com.cognizant.bingo.account.domain.Account;
import com.cognizant.bingo.account.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public ResponseEntity<Object> createAccount(String account) {
        return null;
    }

    public Account retrieveAccount(Long accountId) {
        Optional<Account> accountDB = accountRepository.findById(accountId);
        return accountDB.get();
    }

    public  ResponseEntity<Object> updateAccount(Long id, Account account) {
        Optional<Account> accountDB = accountRepository.findById(id);
        //classic iuri
        String res;
        if(accountDB.isPresent()){
            account.setId(id);
//            updateAccountAttributes(account,id);
            accountRepository.save(account);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    public ResponseEntity<Object> deleteAccount(Long id) {
        if(exists(id)){
            accountRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public boolean exists(Long id) {
        Optional<Account> accountDB = accountRepository.findById(id);
        return accountDB.isPresent();

    }
}
