package com.workintech.twitter.service;

import com.workintech.twitter.entity.Account;
import com.workintech.twitter.entity.Tweet;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findById(int id);
    Account save(Account account);
    Account delete(int id);

}
