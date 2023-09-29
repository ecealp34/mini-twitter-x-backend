package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    private AccountRepository accountRepository;
    @Autowired
    public AccountRepositoryTest(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @BeforeEach
    void setUp() {
        Account account = new Account();
        account.setName("Mert");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        accountRepository.save(account);
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void findByName() {
        String notFoundedName = "test";
        Optional<Account> account = accountRepository.findByName(notFoundedName);
        assertEquals(Optional.empty(), account);

        String foundName = "Mert";
        Optional<Account> foundedAccount = accountRepository.findByName(foundName);
        assertNotNull(foundedAccount.get());
        assertEquals("Mert", foundedAccount.get().getName());
    }
}