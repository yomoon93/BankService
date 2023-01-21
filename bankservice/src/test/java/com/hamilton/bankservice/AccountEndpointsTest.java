package com.hamilton.bankservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Import(AccountController.class)
@WebMvcTest(AccountController.class)
@ContextConfiguration(locations = "classpath:test-bean.xml")
public class AccountEndpointsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountDAO account;
    @MockBean
    private CustomerDAO customer;



    @Test
    void makeNewAccount(){
        Customer customerOne = new Customer(1,"Kevin");
        Account accountTwo = new Account(1, 1, "Savings", 500.00);
        when(customer.registerCustomer(any())).thenReturn(customerOne);
        when(account.addAccount(any())).thenReturn(accountTwo);
        when(customer.getById(1)).thenReturn(customerOne);
        customer.addAccountToCustomer(accountTwo);
        try {
            mockMvc.perform(post("/account")
                            .content("""
                                    {"number": 1,
                                    "customerId": 1,
                                    "accountName": "Savings",
                                    "openingBalance": 500.00}
                                    """)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(MockMvcResultMatchers.jsonPath("number").value(1));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteAccountFromCustomer() {

        Account accountOne = new Account(24, 1, "Checking", 1500.00);
        Account accountTwo = new Account(1, 1, "Savings", 5000.00);
        when(account.addAccount(accountTwo)).thenReturn(accountTwo);
        when(account.addAccount(accountOne)).thenReturn(accountOne);
        account.addAccount(accountTwo);
        account.addAccount(accountOne);
//        customerDAO.registerCustomer(accountTwo);
//        customerDAO.registerCustomer(accountOne);
        when(account.closeAccount(24)).thenReturn(accountOne);
        try {
            mockMvc.perform(delete("/account/24").content(
                                    """   
                                            {1500   .00}     """
                            ).contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("/account/2")
                            .doesNotExist());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getByAccountNumberTest() {
        Customer customerOne = new Customer(1, "Munar");
        Account accountTwo = new Account(1, 1, "Savings", 500.00);
        when(customer.registerCustomer(any())).thenReturn(customerOne);
        customer.registerCustomer(customerOne.getFullName());
        when(account.addAccount(accountTwo)).thenReturn(accountTwo);
        customer.addAccount(customerOne,accountTwo);
        when(account.getById(1)).thenReturn(accountTwo);
        account.getById(1);
        try {
            mockMvc.perform(get("/account/1"))
                    .andExpect(MockMvcResultMatchers.jsonPath("name")
                            .value("Savings"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}






