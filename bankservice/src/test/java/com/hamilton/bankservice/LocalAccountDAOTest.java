package com.hamilton.bankservice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class LocalAccountDAOTest {
    @Mock
    AccountDAO account;




    @BeforeEach
    void setup(){
        account = mock(AccountDAO.class);
    }

    @Test
    void getByIdTest(){
        Account accountOne = new Account(1,1,"Saving",4000);
        when(account.getById(accountOne.getNumber())).thenReturn(accountOne);
        assertEquals(1, accountOne.getNumber());
    }
    @Test
    void addAccountTest(){
        Account accountOne = new Account(1,1,"Saving",4000);
        when(account.addAccount(accountOne)).thenReturn(accountOne);
        assertEquals(1,accountOne.getNumber());
    }

    @Test
    void getSortCodeTest(){
        Account account = new Account(1,1,"Saving",4000);
        when(account.getSortCode()).thenReturn(4312);
        assertEquals(4312,account.getSortCode());
    }



}
