package com.hamilton.bankservice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CustomerTest {

    @Test
    void getMazName() {
        Customer c1 = new Customer(1, "Mazid Uddin");
        assertEquals("Mazid Uddin", c1.getFullName());

    }
}