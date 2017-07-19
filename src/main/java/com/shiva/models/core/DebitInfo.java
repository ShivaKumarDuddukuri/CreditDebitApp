package com.shiva.models.core;

public class DebitInfo {

    private int debitAmount;
    private int accountNumber;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(int debitAmount) {
        this.debitAmount = debitAmount;
    }

    public DebitInfo() {
    }

    public DebitInfo(int accountNumber, int debitAmount) {
        this.debitAmount = debitAmount;
        this.accountNumber = accountNumber;
    }
}
