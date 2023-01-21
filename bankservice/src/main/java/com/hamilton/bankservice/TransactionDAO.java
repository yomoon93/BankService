package com.hamilton.bankservice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface TransactionDAO {
    public ArrayList<Transaction> getAllTransactions();
    public Transaction getTransactionbyID(int tempID);
    public void withdrawTransaction(int acctID, double tempChange);
    public void depositTransaction(int acctID, double tempChange);
    public void transferTransaction(int fromAcctID, int toAcctID, int fromSortCode, int toSortCode, double amount);
    String type = "";
//    enum type {TRANSFER, WITHDRAW, DEPOSIT};

}
