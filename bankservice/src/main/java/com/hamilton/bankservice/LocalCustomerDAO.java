package com.hamilton.bankservice;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class LocalCustomerDAO implements CustomerDAO {
    Map<Integer, Customer> customers;
    private int id;
    private int customerId = 0;

    // not sure if i need this i belive this is for LocalAccountDAO but i am leaving it as localcustomerDao for now.
    public LocalCustomerDAO() {
        customers = new HashMap<>();
    }

    @Override
    public Customer getById(Integer id) {
        return customers.get(id);

    }

    @Override
    public Map<Integer, Customer> getAllCustomer() {
        return customers;
    }

    @Override
    public Customer registerCustomer(String customer) {
        Customer customer1 = new Customer(++customerId, customer);
        this.customers.put(customerId, customer1);
        return customer1;
    }

    @Override
    public void removeAccount(Account account) {
        customers.get(account.getCustomer()).getAccounts().remove(Integer.valueOf(account.getNumber()));
    } ///!!! .remove(Integer.valueOf(account.getNumber())) not .remove(account.getNumber)
        // because type of our List is Integer and we need remove exactly accountNumber, not it position in List


    @Override
    public void addAccountToCustomer(Account account) {
        customers.get(account.getCustomer()).getAccounts().add(account.getNumber());
    }

    @Override
    public void addAccount(Customer customer, Account account) {
        if (customer.getid() == account.getCustomer()) {
            customer.accounts.add(account.getCustomer());
        }
    }


    @Override
    public double deleteCustomer(Integer id) {
//        Map<Integer,Customer> tempList = new HashMap<>();
        double sum = 0;
        if (customers.containsKey(id)) {
            for (Account a : customers.get(id).getCopyAccounts()) {
                sum += a.getBalance();
//                tempList.put(id, customers.get(id));
            }
            customers.remove(id);
            return sum;
        }
        return 0;
    }

}

