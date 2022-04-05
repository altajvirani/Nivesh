package com.phtlearning.nivesh.Investor.Fragments.Home;

import java.text.ParseException;

public class SCard {
    private String sCoverImage, sDesc, sEndDate, sName;
    private int sMinAmount, sNoOfInvestors, sRaisedAmount, sTarget;
    public SCard(String sCoverImage, String sDesc, String sEndDate, int sMinAmount, String sName, int sNoOfInvestors, int sRaisedAmount, int sTarget){
        this.sCoverImage = sCoverImage;
        this.sDesc = sDesc;
        this.sEndDate = sEndDate;
        this.sMinAmount = sMinAmount;
        this.sName = sName;
        this.sNoOfInvestors = sNoOfInvestors;
        this.sRaisedAmount = sRaisedAmount;
        this.sTarget = sTarget;
    }

    public String getsCoverImage() {
        return sCoverImage;
    }
    public void setsCoverImage(String sCoverImage) {
        this.sCoverImage = sCoverImage;
    }
    public String getsDesc() {
        return sDesc;
    }
    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }
    public String getsEndDate() {
        return sEndDate;
    }
    public void setsEndDate(String sDesc) throws ParseException {
        this.sEndDate = sEndDate;
    }
    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }
    public int getsNoOfInvestors() {
        return sNoOfInvestors;
    }
    public void setsNoOfInvestors(int sNoOfInvestors) {
        this.sNoOfInvestors = sNoOfInvestors;
    }
    public int getsMinAmount() {
        return sMinAmount;
    }
    public void setsMinAmount(int sMinAmount) {
        this.sMinAmount = sMinAmount;
    }
    public int getsRaisedAmount() {
        return sRaisedAmount;
    }
    public void setsRaisedAmount(int sRaisedAmount) {
        this.sRaisedAmount = sRaisedAmount;
    }
    public int getsTarget() {
        return sTarget;
    }
    public void setsTarget(int sTarget) {
        this.sTarget = sTarget;
    }
    public SCard(){}
}

