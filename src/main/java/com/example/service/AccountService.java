package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account register(Account account) {

        if (account == null) {
            return null;
        }

        String username = account.getUsername();
        String password = account.getPassword();

        if (username == null || username.trim().length() == 0) {
            return null;
        }

        if (password == null || password.length() < 4) {
            return null;
        }

        Optional<Account> existingAccount =
                accountRepository.findByUsername(username);

        if (existingAccount.isPresent()) {
            return null;
        }

        return accountRepository.save(account);
    }

    public Account login(Account account) {

        if (account == null) {
            return null;
        }

        String username = account.getUsername();
        String password = account.getPassword();

        if (username == null || password == null) {
            return null;
        }

        Optional<Account> foundAccount =
                accountRepository.findByUsernameAndPassword(username, password);

        if (foundAccount.isPresent()) {
            return foundAccount.get();
        }

        return null;
    }

    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id);
    }

    public boolean existsById(Integer id) {
        return accountRepository.existsById(id);
    }
}