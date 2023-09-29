package com.workintech.twitter.controller;

import com.workintech.twitter.entity.Account;
import com.workintech.twitter.exceptions.AccountException;
import com.workintech.twitter.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/")
    public List<Account> findAll() {
        return accountService.findAll();
    }
    @GetMapping("/{id}")
    public Account findById(@PathVariable int id) {
        return accountService.findById(id);
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Account save(@RequestBody Account account) {
        return accountService.save(account);
    }
    @PutMapping("/{id}")
    public Account save(@RequestBody Account account, @PathVariable int id) {
        Account foundAccount = accountService.findById(id);
        if(foundAccount != null) {
            account.setId(id);
            return accountService.save(account);
        }
        throw new AccountException("Account with given id is not exist: " + id, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public Account delete(@PathVariable int id) {
        return accountService.delete(id);
    }
}
