package com.solance.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAccount {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("balance")
    private Double balance;

    public OpenAccount(String userId, Double balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
