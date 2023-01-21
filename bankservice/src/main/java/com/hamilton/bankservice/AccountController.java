package com.hamilton.bankservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
//@RequestMapping
@RestController
@CrossOrigin
public class AccountController {
    private static class AccountForm{
       private String accountName;
        private int customerId;
        private double openingBalance;
//
        public AccountForm(String name, int customer, double openingBalance){
            this.accountName = name;
            this.customerId = customer;
            this.openingBalance = openingBalance;
        }

        public String getAccountName() {
            return accountName;
        }

        public int getCustomerId() {
            return customerId;
        }

        public double getOpeningBalance() {
            return openingBalance;
        }

    }
    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private CustomerDAO customerDAO;


    @GetMapping(value ="/sortCode")
    public int getSortCode(){
        return accountDAO.getSortCode();
    }

    @GetMapping(value ="/account/{id}")
    public Account getAccount(@PathVariable Integer id){

        return accountDAO.getById(id);
    }



    @PostMapping(value="/account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account newAccount(@RequestBody AccountForm accountForm){
            Customer customer = customerDAO.getById(accountForm.getCustomerId());
            if (customer != null ){
                Account aThing = new Account(0, accountForm.getCustomerId(),accountForm.getAccountName(),accountForm.getOpeningBalance());
                aThing = accountDAO.addAccount(aThing);
                customer.copyAccounts.add(aThing);
                customer.getAccounts().add(aThing.getNumber());
                return aThing;
            }
            return null;
    }
    @DeleteMapping(value = "/account/{id}")
    public double deleteAccount(@PathVariable Integer id){
        Account accounts = accountDAO.closeAccount(id);
          customerDAO.removeAccount(accounts);
        return accounts.getBalance();
    }
}
