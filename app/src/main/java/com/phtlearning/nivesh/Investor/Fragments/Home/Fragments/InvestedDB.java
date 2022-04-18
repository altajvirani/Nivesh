package com.phtlearning.nivesh.Investor.Fragments.Home.Fragments;

public class InvestedDB {
    String cname, amount, equity, imageulr;

    public InvestedDB(String cname, String amount, String equity, String imageulr) {
        this.cname = cname;
        this.amount = amount;
        this.equity = equity;
        this.imageulr = imageulr;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }
}
