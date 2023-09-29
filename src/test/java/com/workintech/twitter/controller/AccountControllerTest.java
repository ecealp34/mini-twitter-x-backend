package com.workintech.twitter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.twitter.entity.Account;
import com.workintech.twitter.service.AccountService;
import com.workintech.twitter.service.AuthenticationService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;
    private AuthenticationService authenticationService;
    @Test
    void findAll() throws Exception {
        Account account = new Account();
        account.setName("Mert");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        when(accountService.findAll()).thenReturn(accounts);

        mockMvc.perform(get("/account/"))
                .andExpect(status().isOk());
        verify(accountService.findAll());
    }

    @Test
    void findById() throws Exception {
        Account account = new Account();
        account.setName("Mert");
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        when(accountService.findById(account.getId())).thenReturn(account);

        mockMvc.perform(get("/account/id"))
                .andExpect(status().isOk());
        verify(accountService.findById(account.getId()));
    }

    @Test
    void save() throws Exception {
        Account account = new Account();
        account.setName("Cem");

        when(accountService.save(account)).thenReturn(account);

        mockMvc.perform(post("/account/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(account))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Cem"));

        verify(accountService).save(account);

    }

    public static String asJsonString(Object object) {
        try{
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}