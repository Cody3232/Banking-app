package com.bankingapp;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountService {
    // In-memory store: accountId → Account
    private final Map<String, Account> accounts = new HashMap<>();

    /**
     * Creates a new Account with a random UUID as its ID.
     * Stores it and returns the Account object.
     */
    public Account createAccount() {
        String newId = UUID.randomUUID().toString();
        Account newAccount = new Account(newId);
        accounts.put(newId, newAccount);
        return newAccount;
    }

    /**
     * Deposits a positive amount into the account identified by accountId.
     * Throws IllegalArgumentException if account not found or amount <= 0.
     */
    public void deposit(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        account.deposit(amount);
    }

    /**
     * Withdraws a positive amount from the account if funds are sufficient.
     * Throws IllegalArgumentException if account not found or amount <= 0.
     * Throws InsufficientFundsException if balance is less than amount.
     */
    public void withdraw(String accountId, double amount) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        account.withdraw(amount);
    }

    /**
     * Returns the current balance of the account.
     * Throws IllegalArgumentException if account not found.
     */
    public double getBalance(String accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }
        return account.getBalance();
    }
}
