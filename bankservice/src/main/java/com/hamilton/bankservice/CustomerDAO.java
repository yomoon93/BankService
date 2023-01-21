package com.hamilton.bankservice;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public interface CustomerDAO {

    Map<Integer, Customer> getAllCustomer();

    Customer getById(Integer id);

    Customer registerCustomer(String customer);

    void addAccountToCustomer(Account account);

    void addAccount(Customer customer, Account account);


    double deleteCustomer(Integer id);


    void removeAccount(Account account);


}
