package com.hamilton.bankservice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private int number;
    final int sortCode = 4312;

    private String name;
    private double openingBalance;
    private List<Transaction> transactions;
    private double balance = 0;
    private int customer;

    public void setCurrent_balance(double balance) {
        this.balance = balance;
    }


    private List<Account> listOfAccounts;


    @JsonIgnore
    private List<Integer> taken_accounts = new ArrayList<>();

    public Account(int accountNum,int customer,String name, double openingBalance){
        this.number = accountNum;
        this.customer = customer;
        this.name = name;
        this.openingBalance = openingBalance;
        this.balance = openingBalance;
        this.transactions = new ArrayList<>();
    }

public void addToTransactionLog(Transaction transaction){
        transactions.add(transaction);
}

    public int getCustomer() {
        return customer;
    }

    public int getSortCode() {
        return sortCode;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getOpeningBalance() {
        return openingBalance ;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
    public double getBalance() {
        return balance;
    }


    public List<Integer> getTaken_accounts() {
        return taken_accounts;
    }

}
