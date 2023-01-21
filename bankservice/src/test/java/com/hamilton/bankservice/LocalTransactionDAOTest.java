package com.hamilton.bankservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class LocalTransactionDAOTest {

    @Mock
    AccountDAO account;

    @Mock
    TransactionDAO transaction;

    @BeforeEach
    void setup(){
        account = mock(AccountDAO.class);
    }

    @Test
    void getAllTransactionsTest(){
        Transaction t1 = new Transaction(1,Integer.MAX_VALUE, 10, "WITHDRAWL");
        Transaction t2 = new Transaction(2, Integer.MAX_VALUE, 20, "DEPOSIT");
        Transaction t3 = new Transaction(2,1,5, "TRANSFER");
        ArrayList<Transaction> test = new ArrayList<>();
        test.add(t1);
        test.add(t2);
        test.add(t3);
        Account a1 = new Account(1,1,"check", 20.0);
        a1.addToTransactionLog(t1);
        a1.addToTransactionLog(t2);
        a1.addToTransactionLog(t3);
        assertEquals(test,a1.getTransactions());
    }

    @Test
    void getTransactionByIDTest(){
        Transaction withdraw1 = new Transaction(1,Integer.MAX_VALUE, 10, "WITHDRAWL");
        Account a1 = new Account(1,1,"check", 20.0);
        a1.addToTransactionLog(withdraw1);
        assertEquals(withdraw1, a1.getTransactions().get(0));
    }

    @Test
    void withdrawTransactionTest(){
        double testAnswerBalance=10.0;
        Account a1 = new Account(1,1,"checking",20);
        a1.setCurrent_balance((a1.getBalance()-10));
        assertEquals(testAnswerBalance,a1.getBalance());
    }

    @Test
    void depositTransactiontest(){
        double testAnswerBalance=30.0;
        Account a2 = new Account(1,1,"checking",20);
        a2.setCurrent_balance(a2.getBalance()+10);
        assertEquals(testAnswerBalance,a2.getBalance());
    }

    @Test
    void transferTransactiontest(){
        double correctFromBalance = 10.0, correctToBalance = 20.0;
        Account a1 = new Account(1,1,"checking",15);
        Account a2 = new Account(1,1,"checking",15);
        a1.setCurrent_balance((a1.getBalance()-5));
        a2.setCurrent_balance(a2.getBalance()+5);
        assertTrue((correctFromBalance==(a1.getBalance()) && (correctToBalance==a2.getBalance())));
    }
}
