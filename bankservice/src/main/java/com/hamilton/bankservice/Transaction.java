package com.hamilton.bankservice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class Transaction {

    LocalDateTime time;
    static String type;
    int fromAccount;
    final int fromAccountSortCode = 4213;
    int toAccount;
    int toAccountSortCode;
    double amount;
    @JsonIgnore
    int id=0;

//    Standard response:
//    {
//        "time": 02-11-2022-17:06:01PM,
//            "type": "TRANSFER",
//            "fromAccount": 123456789,
//            "fromAccountSortCode": 1234,
//            "toAccount": 987654321,
//            "toAccountSortCode": 4444,
//            "amount": 150.00
//    }



//    public int gettoAccountSortCode() {return toAccountSortCode;}
//
//    public void settoAccountSortCode(int toAccountSortCode) {this.toAccountSortCode = toAccountSortCode;}
//
//    public int getFromAccountid() {
//        return fromAccountid;
//    }

    public LocalDateTime gettime() {return time;}

    public void setTime(LocalDateTime time) {this.time = time;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public int getfromAccount() {return fromAccount;}

    public void setfromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getfromSortCode() {return fromAccountSortCode;}

    public int gettoAccount() {return toAccount;}

    public void settoAccount(int ToAccount) {
        this.toAccount = ToAccount;
    }

    public int gettoAccountSortCode() {return toAccountSortCode;}

    public void settoAccountSortCode(int toAccountSortCode) {this.toAccountSortCode = toAccountSortCode;}

    public double getamount() {
        return amount;
    }

    public void setamount(double amount) {
        this.amount = amount;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}


    Transaction(int fromAccount, int toAccount, double amount, String type){
              this.setfromAccount(fromAccount);
              this.settoAccount(toAccount);
              this.setTime(LocalDateTime.now());
              this.setamount(amount);
              this.setType(type);
              this.id++;
    }


}





