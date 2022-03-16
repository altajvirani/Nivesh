package com.phtlearning.nivesh.Authentication.SignUp;

public class UserSignUpDetails {
    String Email;
    public UserSignUpDetails(String FounderEmail)
    {
        Email = FounderEmail;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String FounderEmail) {
        Email = FounderEmail;
    }
}
