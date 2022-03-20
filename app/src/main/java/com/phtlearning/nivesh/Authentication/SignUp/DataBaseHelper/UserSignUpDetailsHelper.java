package com.phtlearning.nivesh.Authentication.SignUp.DataBaseHelper;

public class UserSignUpDetailsHelper {
    String Email;

    public UserSignUpDetailsHelper() {
    }

    public UserSignUpDetailsHelper(String email) {
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
