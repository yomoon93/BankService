package com.hamilton.bankservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class LocalTransactionDAO implements TransactionDAO{

    ArrayList<Transaction> transactionLog;

    @Autowired
    AccountDAO accountDAO;

    public LocalTransactionDAO(){
        transactionLog = new ArrayList<Transaction>();
    }

    @Override
    public ArrayList<Transaction> getAllTransactions(){
       return this.transactionLog;
    }


    @Override
    public Transaction getTransactionbyID(int tempID){
         Transaction a = transactionLog.get(tempID);
         return a;
    }


    @Override
    public void withdrawTransaction(int number, double tempChange) {
        Account a1 = accountDAO.getById(number);
        Transaction newTransaction = new Transaction(a1.getNumber(),Integer.MAX_VALUE, tempChange, "WITHDRAW");
        accountDAO.getById(number).setCurrent_balance((accountDAO.getById(number).getBalance()-tempChange));
        accountDAO.addToTransaction(number, newTransaction);}


    @Override
    public void depositTransaction(int acctID, double amount) {
        Account a2 = accountDAO.getById(acctID);
        Transaction newTransaction = new Transaction(Integer.MAX_VALUE, a2.getNumber(), amount, "DEPOSIT");
        accountDAO.getById(acctID).setCurrent_balance((accountDAO.getById(acctID).getBalance()+amount));
        accountDAO.addToTransaction(acctID,newTransaction);
    }



    @Override
    public void transferTransaction(int fromAcctID, int toAcctID, int fromSortCode, int toSortCode, double change) {
        Account a3 = accountDAO.getById(fromAcctID);
        Account a4 = accountDAO.getById(toAcctID);

        Transaction newTransfer = new Transaction(a3.getNumber(), a4.getNumber(), change, "TRANSFER");
        accountDAO.getById(fromAcctID).setCurrent_balance((accountDAO.getById(fromAcctID).getBalance() - change));
        accountDAO.getById(toAcctID).setCurrent_balance((accountDAO.getById(toAcctID).getBalance() + change));
        accountDAO.addToTransaction(fromAcctID,newTransfer);
        accountDAO.addToTransaction(toAcctID,newTransfer);


    }
}