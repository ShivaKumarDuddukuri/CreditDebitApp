package com.shiva.models.core;

public class CreditInfo {
    private int creditAmount;
    private int accountNumber;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public CreditInfo() {
    }

    public CreditInfo(int accountNumber, int creditAmount) {
        super();
        this.creditAmount = creditAmount;
        this.accountNumber = accountNumber;
    }

}
