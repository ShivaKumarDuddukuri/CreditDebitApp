package com.tomojo.dao;

public interface TransactionDao {

    boolean credit(int accountNumber, int creditAmount);

    boolean debit(int accountNumber, int debitAmount);

    boolean isAccountPresent(int accountNumber);

    int getBalance(int accountNumber);
}