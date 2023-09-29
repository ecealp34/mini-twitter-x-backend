package com.workintech.twitter.service;

import com.workintech.twitter.entity.Account;
import com.workintech.twitter.repository.AccountRepository;
import org.hibernate.sql.ast.tree.expression.Over;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    void findAll() {
        accountService.findAll();
        verify(accountRepository).findAll();
    }

    @Test
    void findById() {
        Account account = new Account();
        account.setId(1);
        accountService.findById(account.getId());
        verify(accountRepository).findById(account.getId());
    }

    @Test
    void save() {
        String name = "test";
        Account account = new Account();
        account.setName(name);

        //given(accountRepository.findByName(name)).willReturn(Optional.empty());
        given(accountRepository.save(account)).willReturn(account);
        Account savedAccount = accountService.save(account);
        assertNotNull(savedAccount);
        verify(accountRepository).save(account);
    }

    @Test
    void delete() {
        Account account = new Account();
        account.setId(1);
        account.setName("Mert");

        given(accountRepository.findById(1)).willReturn(Optional.of(account));

        Account removedAccount = accountService.delete(account.getId());

        verify(accountRepository).delete(account);
        assertEquals("Mert", removedAccount.getName());
    }
}