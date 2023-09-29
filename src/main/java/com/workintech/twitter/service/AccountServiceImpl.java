package com.workintech.twitter.service;

import com.workintech.twitter.entity.Account;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(int id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()) {
            return account.get();
        }
        return null;
    }

    @Override
    public Account save(Account account) {
        Account foundAccount = findById(account.getId());
        if(foundAccount == null) {
            return accountRepository.save(account);
        }
        return null;
    }

    @Override
    public Account delete(int id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()) {
            accountRepository.delete(account.get());
        }
        return null;
    }
}
