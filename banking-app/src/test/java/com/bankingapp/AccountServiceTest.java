package com.bankingapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private AccountService service;
    private String testAccountId;

    @BeforeEach
    void setUp() {
        service = new AccountService();
        testAccountId = service.createAccount().getAccountId();
    }

    @Test
    void depositPositiveAmount_IncreasesBalance() {
        assertEquals(0.0, service.getBalance(testAccountId), 0.0001);
        service.deposit(testAccountId, 100.0);
        assertEquals(100.0, service.getBalance(testAccountId), 0.0001);
    }

    @Test
    void withdrawMoreThanBalance_ThrowsInsufficientFundsException() {
        assertThrows(InsufficientFundsException.class, () -> {
            service.withdraw(testAccountId, 50.0);
        });
    }

    @Test
    void withdrawValidAmount_DecreasesBalance() {
        service.deposit(testAccountId, 200.0);
        service.withdraw(testAccountId, 75.0);
        assertEquals(125.0, service.getBalance(testAccountId), 0.0001);
    }
}
