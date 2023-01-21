package com.hamilton.bankservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
public class LocalCustomerDaoTesting {
    @Mock
    static Customer customer;

    @Mock
    static Customer customer2;

    @Mock
    static Account account;
    static LocalCustomerDAO localCustomerDAO;

    @BeforeEach
    void setup() {
        account =  mock(Account.class);
        customer = mock(Customer.class);
        customer2 = mock(Customer.class);
        localCustomerDAO = new LocalCustomerDAO();
    }

    @Test
    void addMaztoMap() {
        when(customer.getFullName()).thenReturn("Maz");
        when(customer.getid()).thenReturn(1);
        localCustomerDAO.registerCustomer(customer.getFullName());
        assertEquals("Maz", localCustomerDAO.getById(1).getFullName());
    }

    @Test
    void addAccountToCustomer(){
        when(customer.getFullName()).thenReturn("Schmoney");
        when(account.getCustomer()).thenReturn(1);
        when(account.getNumber()).thenReturn(10);
        localCustomerDAO.registerCustomer(customer.getFullName());
        localCustomerDAO.addAccountToCustomer(account);
        assertEquals(1,localCustomerDAO.customers.size());
        assertEquals(1,localCustomerDAO.customers.get(account.getCustomer()).getAccounts().size());
        assertEquals(10, localCustomerDAO.getById(1).getAccounts().get(0));
    }

    @Test
    void getbyIDtestforOneCustomer() {
        when(customer.getFullName()).thenReturn("Kevin");
        localCustomerDAO.registerCustomer(customer.getFullName());
        assertEquals("Kevin",localCustomerDAO.getById(1).getFullName());
        assertEquals(1,localCustomerDAO.customers.size());
    }

    @Test
    void getbyIDtestforTwoCustomers() {
        when(customer.getFullName()).thenReturn("Kevin");
        when(customer2.getFullName()).thenReturn("Julian");
        localCustomerDAO.registerCustomer(customer.getFullName());
        localCustomerDAO.registerCustomer(customer2.getFullName());
        assertEquals("Kevin",localCustomerDAO.getById(1).getFullName());
        assertEquals("Julian",localCustomerDAO.getById(2).getFullName());
        assertEquals(2,localCustomerDAO.customers.size());
    }


    }
