package com.phtlearning.nivesh.Founder.DatabaseHelper;

public class RaiseFundHelper {
    String CompanyName, FounderName, CompanyLogo, CompanyDiscription, CompanyCategory, StartDate, EndDate, MinInvestment,
            TotalTargetAmount, TotalInvestors, ProblemStatement, SolutionStatement, PitchLink, TotalRevenue,
            TotalEmp, WebSiteLink, CompanyForm;

    public RaiseFundHelper() {
    }

    public RaiseFundHelper(String companyName) {
        CompanyName = companyName;
    }


    public RaiseFundHelper(String companyName, String founderName, String companyLogo, String companyDiscription, String companyCategory, String startDate, String endDate, String minInvestment, String totalTargetAmount, String totalInvestors, String problemStatement, String solutionStatement, String pitchLink, String totalRevenue, String totalEmp, String webSiteLink) {
        CompanyName = companyName;
        FounderName = founderName;
        CompanyLogo = companyLogo;
        CompanyDiscription = companyDiscription;
        CompanyCategory = companyCategory;
        StartDate = startDate;
        EndDate = endDate;
        MinInvestment = minInvestment;
        TotalTargetAmount = totalTargetAmount;
        TotalInvestors = totalInvestors;
        ProblemStatement = problemStatement;
        SolutionStatement = solutionStatement;
        PitchLink = pitchLink;
        TotalRevenue = totalRevenue;
        TotalEmp = totalEmp;
        WebSiteLink = webSiteLink;
    }

    public RaiseFundHelper(String companyName, String founderName, String companyLogo, String companyDiscription, String companyCategory, String startDate, String endDate, String minInvestment, String totalTargetAmount, String totalInvestors, String problemStatement, String solutionStatement, String pitchLink, String totalRevenue, String totalEmp, String webSiteLink, String companyForm) {
        CompanyName = companyName;
        FounderName = founderName;
        CompanyLogo = companyLogo;
        CompanyDiscription = companyDiscription;
        CompanyCategory = companyCategory;
        StartDate = startDate;
        EndDate = endDate;
        MinInvestment = minInvestment;
        TotalTargetAmount = totalTargetAmount;
        TotalInvestors = totalInvestors;
        ProblemStatement = problemStatement;
        SolutionStatement = solutionStatement;
        PitchLink = pitchLink;
        TotalRevenue = totalRevenue;
        TotalEmp = totalEmp;
        WebSiteLink = webSiteLink;
        CompanyForm = companyForm;
    }

    public String getCompanyForm() {
        return CompanyForm;
    }

    public void setCompanyForm(String companyForm) {
        CompanyForm = companyForm;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getFounderName() {
        return FounderName;
    }

    public void setFounderName(String founderName) {
        FounderName = founderName;
    }

    public String getCompanyLogo() {
        return CompanyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        CompanyLogo = companyLogo;
    }

    public String getCompanyDiscription() {
        return CompanyDiscription;
    }

    public void setCompanyDiscription(String companyDiscription) {
        CompanyDiscription = companyDiscription;
    }

    public String getCompanyCategory() {
        return CompanyCategory;
    }

    public void setCompanyCategory(String companyCategory) {
        CompanyCategory = companyCategory;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getMinInvestment() {
        return MinInvestment;
    }

    public void setMinInvestment(String minInvestment) {
        MinInvestment = minInvestment;
    }

    public String getTotalTargetAmount() {
        return TotalTargetAmount;
    }

    public void setTotalTargetAmount(String totalTargetAmount) {
        TotalTargetAmount = totalTargetAmount;
    }

    public String getTotalInvestors() {
        return TotalInvestors;
    }

    public void setTotalInvestors(String totalInvestors) {
        TotalInvestors = totalInvestors;
    }

    public String getProblemStatement() {
        return ProblemStatement;
    }

    public void setProblemStatement(String problemStatement) {
        ProblemStatement = problemStatement;
    }

    public String getSolutionStatement() {
        return SolutionStatement;
    }

    public void setSolutionStatement(String solutionStatement) {
        SolutionStatement = solutionStatement;
    }

    public String getPitchLink() {
        return PitchLink;
    }

    public void setPitchLink(String pitchLink) {
        PitchLink = pitchLink;
    }

    public String getTotalRevenue() {
        return TotalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        TotalRevenue = totalRevenue;
    }

    public String getTotalEmp() {
        return TotalEmp;
    }

    public void setTotalEmp(String totalEmp) {
        TotalEmp = totalEmp;
    }

    public String getWebSiteLink() {
        return WebSiteLink;
    }

    public void setWebSiteLink(String webSiteLink) {
        WebSiteLink = webSiteLink;
    }
}
