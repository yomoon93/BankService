package com.hamilton.bankservice;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public interface AccountDAO {
 Collection<Account> getAllAccounts();
 Account getById(Integer id);
 Account addAccount(Account account);

 int getSortCode();
 Account closeAccount(int accountNum);
 void addToTransaction(int number, Transaction t);

 double closeAllaccounts(Customer customer) ;
// void getAllAccounts(Account account);
}
