package com.solance.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferInstruction {
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("solanceFrom")
    private String solanceFrom;

    @JsonProperty("solanceTo")
    private String solanceTo;

    @JsonProperty("timePlaced")
    private String timePlaced;

    @JsonProperty("beneficiaryIban")
    private String beneficiaryIban;

    @JsonProperty("originatingCountry")
    private String originatingCountry;

    @JsonProperty("paymentRef")
    private String paymentRef;

    @JsonProperty("purposeRef")
    private String purposeRef;

    @JsonProperty("amountSell")
    private Double amountSell;

    @JsonProperty("rate")
    private Double rate;

    public TransferInstruction(String userId, String solanceFrom, String solanceTo, String timePlaced, String beneficiaryIban, String originatingCountry, String paymentRef, String purposeRef, Double amountSell, Double amountBuy, Double rate) {
        this.userId = userId;
        this.solanceFrom = solanceFrom;
        this.solanceTo = solanceTo;
        this.timePlaced = timePlaced;
        this.beneficiaryIban = beneficiaryIban;
        this.originatingCountry = originatingCountry;
        this.paymentRef = paymentRef;
        this.purposeRef = purposeRef;
        this.amountSell = amountSell;
        this.rate = rate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSolanceFrom() {
        return solanceFrom;
    }

    public void setSolanceFrom(String solanceFrom) {
        this.solanceFrom = solanceFrom;
    }

    public String getSolanceTo() {
        return solanceTo;
    }

    public void setSolanceTo(String solanceTo) {
        this.solanceTo = solanceTo;
    }

    public String getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(String timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getBeneficiaryIban() {
        return beneficiaryIban;
    }

    public void setBeneficiaryIban(String beneficiaryIban) {
        this.beneficiaryIban = beneficiaryIban;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public String getPurposeRef() {
        return purposeRef;
    }

    public void setPurposeRef(String purposeRef) {
        this.purposeRef = purposeRef;
    }

    public Double getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(Double amountSell) {
        this.amountSell = amountSell;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
