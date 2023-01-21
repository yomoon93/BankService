package com.hamilton.bankservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Import(CustomerController.class)
@WebMvcTest(CustomerController.class)
@ContextConfiguration(locations = "classpath:test-bean.xml")

public class CustomerEndpointsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerDAO customers;

    @MockBean
    AccountDAO accounts;


    @BeforeEach
    void setup() {

    }


    @Test
    void getCustomerById() {
        when(customers.getById(1)).thenReturn(new Customer(1, "Mazid Uddin"));

        try {
            mockMvc.perform(get("/customer/1"))
                    .andExpect(MockMvcResultMatchers.jsonPath("id")
                            .value("1"))
                    .andExpect(status().isOk());
//                    .andExpect(MockMvcResultMatchers.jsonPath("name")
//                            .value("Mazid Uddin"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void makeNewCustomer() {
        Customer person = new Customer(2,"Kevin");
        when(customers.registerCustomer(any())).thenReturn(person);
        customers.registerCustomer("Kevin");
        try {mockMvc.perform(post("/customer")
                        .content(""" 
                                 "id": 2,
                                    "fullName": "Kevin",
                                    "accounts": []""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(2));
        } catch (Exception e) {
            throw new RuntimeException(e);}
    }
//    void makeNewCustomer () {
//        Customer cOutput = new Customer (1, "Kevin");
//        when (customers.registerCustomer (any (String.class))).thenReturn (cOutput);
//        try {
//            mockMvc.perform (post ("/customer")
//                            .content ("""
//                                    {"name" : "Kevin"}
//                                  """)
//                            .contentType (MediaType.APPLICATION_JSON)
//                            .accept (MediaType.APPLICATION_JSON))
//                    .andExpect (status ().isCreated ())
//                    .andExpect (MockMvcResultMatchers.jsonPath ("id").value (1))
//                    .andExpect (MockMvcResultMatchers.jsonPath ("name").value ("Kevin"));
//        } catch (Exception e) {
//            throw new RuntimeException (e);
//        }


    @Test
    void deleteCustomertest () {
        Customer cOutput = new Customer (45, "Kevin");
        when (customers.registerCustomer(any (String.class))).thenReturn (cOutput);
        customers.registerCustomer(cOutput.getFullName());

        try {
            mockMvc.perform (delete ("/customer/99")
                            .contentType (MediaType.APPLICATION_JSON)
                            .accept (MediaType.APPLICATION_JSON))
                    .andExpect (status ().isOk());
        } catch (Exception e) {
            throw new RuntimeException (e);
        }

        try {
            mockMvc.perform (get ("/customer/99")
                            .contentType (MediaType.APPLICATION_JSON)
                            .accept (MediaType.APPLICATION_JSON))
                    .andExpect (status ().isOk())
                    .andExpect (content().string(""));
        } catch (Exception e) {
            throw new RuntimeException (e);
        }

    }

    @Test
    void deleteCustomertest2() {
        Customer cOutput = new Customer (99, "Kevin");
        when(customers.registerCustomer(any(String.class))).thenReturn(cOutput );
        when(customers.getById(99)).thenReturn(cOutput);
        when(accounts.closeAllaccounts(cOutput)).thenReturn(1000.0);

        try {
            mockMvc.perform(delete("/customer/99"))
                    .andExpect(MockMvcResultMatchers.jsonPath("name").doesNotExist())
                    .andExpect(MockMvcResultMatchers.jsonPath("id").doesNotExist())
                    .andExpect(MockMvcResultMatchers.jsonPath("accounts").doesNotExist())
                    .andExpect(content().string("0.0"))
                    .andExpect(status().isOk());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
