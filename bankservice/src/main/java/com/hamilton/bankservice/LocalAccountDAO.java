package com.hamilton.bankservice;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LocalAccountDAO implements AccountDAO{
    int counter = 0;
    HashMap<Integer,Account> accountMap;


    public LocalAccountDAO(){
        accountMap = new HashMap<>();

    }
//
//    @Override
//    public HashMap<Integer,Account> getAllAccounts() {
//        return accountMap;
//    }

    @Override
    public Collection<Account> getAllAccounts() {
        return accountMap.values();
    }

    @Override
    public Account getById(Integer id) {
        return accountMap.get(id);
    }

    @Override
    public Account addAccount(Account aThing) {
        int id = ++counter;
        Account account = new Account(id,aThing.getCustomer(), aThing.getName(), aThing.getOpeningBalance());
        accountMap.put(id,account);
        return account;
    }

    @Override
    public int getSortCode() {
       return 4312;
    }

    @Override
    public Account closeAccount(int accountNum) {
       return accountMap.remove(accountNum);
    }

    @Override
    public void addToTransaction(int number, Transaction t) {
        accountMap.get(number).getTransactions().add(t);
    }

   //close All accounts removes the account from the account map and returns the total balance of all accounts in Map
    @Override
    public  double closeAllaccounts(Customer customer) {
        double totalBalance = 0.0;
        if ( customer!= null) {
            for (Integer i : customer.getAccounts()) {totalBalance += accountMap.get(i).getBalance();}
            for (Integer i : customer.getAccounts()) {accountMap.remove(i);}
        }
        return totalBalance;
    }


//    @Override
//    public Account getAllAccounts(Account account){
//        for (int i = 0; i<accountMap.size(); i++){
//            System.out.println(accountMap);
//        }
//    }

//    @Override
//    public Account deleteAccount(Account account) {
//        return null;
//    }
}
