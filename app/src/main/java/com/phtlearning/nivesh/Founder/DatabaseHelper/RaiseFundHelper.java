package com.phtlearning.nivesh.Founder.DatabaseHelper;

public class RaiseFundHelper {
    String CompanyName, FounderName, CompanyLogo, CompanyDescription, CompanyCategory, StartDate, EndDate, MinAmount,
            TotalTargetAmount, TotalInvestors, ProblemStatement, SolutionStatement, PitchLink, TotalRevenue,
            TotalEmp, WebSiteLink, CompanyForm,Equity, Status, RaisedAmount;

    public RaiseFundHelper() {
    }

    public RaiseFundHelper(String companyName) {
        CompanyName = companyName;
    }

    public RaiseFundHelper(String companyName, String founderName, String companyLogo, String companyDescription, String companyCategory, String startDate, String endDate, String minAmount, String totalTargetAmount, String totalInvestors, String problemStatement, String solutionStatement, String pitchLink, String totalRevenue, String totalEmp, String webSiteLink, String companyForm, String equity, String status, String raisedAmount) {
        CompanyName = companyName;
        FounderName = founderName;
        CompanyLogo = companyLogo;
        CompanyDescription = companyDescription;
        CompanyCategory = companyCategory;
        StartDate = startDate;
        EndDate = endDate;
        MinAmount = minAmount;
        TotalTargetAmount = totalTargetAmount;
        TotalInvestors = totalInvestors;
        ProblemStatement = problemStatement;
        SolutionStatement = solutionStatement;
        PitchLink = pitchLink;
        TotalRevenue = totalRevenue;
        TotalEmp = totalEmp;
        WebSiteLink = webSiteLink;
        CompanyForm = companyForm;
        Equity = equity;
        Status = status;
        RaisedAmount = raisedAmount;
    }

    public String getRaisedAmount() {
        return RaisedAmount;
    }

    public void setRaisedAmount(String raisedAmount) {
        RaisedAmount = raisedAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getEquity() {
        return Equity;
    }

    public void setEquity(String equity) {
        Equity = equity;
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

    public String getCompanyDescription() {
        return CompanyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        CompanyDescription = companyDescription;
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

    public String getMinAmount() {
        return MinAmount;
    }

    public void setMinAmount(String minAmount) {
        MinAmount = minAmount;
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
