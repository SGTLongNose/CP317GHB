package com.example.ghb_draft;

public class AccountClass {
    String accountType, accountAmount;
    int picture;

    public AccountClass(String accountType, String accountAmount, int picture) {
        this.accountType = accountType;
        this.accountAmount = accountAmount;
        this.picture = picture;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(String accountAmount) {
        this.accountAmount = accountAmount;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
