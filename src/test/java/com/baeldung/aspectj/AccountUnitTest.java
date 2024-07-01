package com.baeldung.aspectj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountUnitTest {
    private Account account;

    @BeforeEach
    void before() {
        account = new Account();
    }

    @Test
    void givenBalance20AndMinBalance10_whenWithdraw5_thenSuccess() {
        assertTrue(account.withdraw(5));
    }

    @Test
    void givenBalance20AndMinBalance10_whenWithdraw100_thenFail() {
        assertFalse(account.withdraw(100));
    }
}
