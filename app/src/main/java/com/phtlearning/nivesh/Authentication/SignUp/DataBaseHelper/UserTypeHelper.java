package com.phtlearning.nivesh.Authentication.SignUp.DataBaseHelper;

public class UserTypeHelper {
    String UserType;

    public UserTypeHelper() {
    }

    public UserTypeHelper(String userType) {
        UserType = userType;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
