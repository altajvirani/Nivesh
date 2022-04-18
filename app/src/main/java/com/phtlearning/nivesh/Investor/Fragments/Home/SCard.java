package com.phtlearning.nivesh.Investor.Fragments.Home;

public class SCard {
    private String sCoverImage, sDesc, sEndDate, sName, sStatus;
    private int sMinAmount, sNoOfInvestors, sRaisedAmount, sTarget;
    public SCard(String sCoverImage, String sDesc, String sEndDate, int sMinAmount, String sName, int sNoOfInvestors, int sRaisedAmount, int sTarget, String sStatus){
        this.sCoverImage = sCoverImage;
        this.sDesc = sDesc;
        this.sEndDate = sEndDate;
        this.sMinAmount = sMinAmount;
        this.sName = sName;
        this.sNoOfInvestors = sNoOfInvestors;
        this.sRaisedAmount = sRaisedAmount;
        this.sTarget = sTarget;
        this.sStatus = sStatus;
    }
    public String getsCoverImage() {
        return sCoverImage;
    }
    public String getsDesc() {
        return sDesc;
    }
    public String getsEndDate() {
        return sEndDate;
    }
    public String getsName() {
        return sName;
    }
    public int getsNoOfInvestors() {
        return sNoOfInvestors;
    }
    public int getsMinAmount() {
        return sMinAmount;
    }
    public int getsRaisedAmount() {
        return sRaisedAmount;
    }
    public int getsTarget() {
        return sTarget;
    }
    public String getsStatus() {
        return sStatus;
    }
    public SCard(){}
}

