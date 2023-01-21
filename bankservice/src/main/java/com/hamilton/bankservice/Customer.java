package com.hamilton.bankservice;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private int id;
    private final String fullName;
    String input;
    private int customerId;
    List<Integer> accounts;
    Customer(int customerId, String fullName) {
        this.id = customerId;
        this.fullName = fullName;
        this.accounts = new ArrayList<>();
    }
    @JsonIgnore
    List<Account> copyAccounts = new ArrayList<>();

    public int getid() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Integer> getAccounts() {
        return accounts;
    }

    public List<Account> getCopyAccounts() {
        return copyAccounts;
    }
}






