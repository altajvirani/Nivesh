package com.phtlearning.nivesh.Founder.DatabaseHelper;

public class ProfileHelper {
    String UserName, UserProfession, UserDOB, UserGender, UserProfileImage, UserAboutMe, UserContactNumber;

    public ProfileHelper() {
    }

    public ProfileHelper(String userName, String userProfession, String userDOB, String userGender, String userProfileImage, String userContactNumber) {
        UserName = userName;
        UserProfession = userProfession;
        UserDOB = userDOB;
        UserGender = userGender;
        UserProfileImage = userProfileImage;
        UserContactNumber = userContactNumber;
    }

    public ProfileHelper(String userName, String userProfession, String userDOB, String userGender, String userProfileImage, String userAboutMe, String userContactNumber) {
        UserName = userName;
        UserProfession = userProfession;
        UserDOB = userDOB;
        UserGender = userGender;
        UserProfileImage = userProfileImage;
        UserAboutMe = userAboutMe;
        UserContactNumber = userContactNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserProfession() {
        return UserProfession;
    }

    public void setUserProfession(String userProfession) {
        UserProfession = userProfession;
    }

    public String getUserDOB() {
        return UserDOB;
    }

    public void setUserDOB(String userDOB) {
        UserDOB = userDOB;
    }

    public String getUserGender() {
        return UserGender;
    }

    public void setUserGender(String userGender) {
        UserGender = userGender;
    }

    public String getUserProfileImage() {
        return UserProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        UserProfileImage = userProfileImage;
    }

    public String getUserContactNumber() {
        return UserContactNumber;
    }

    public String getUserAboutMe() {
        return UserAboutMe;
    }

    public void setUserAboutMe(String userAboutMe) {
        UserAboutMe = userAboutMe;
    }

    public void setUserContactNumber(String userContactNumber) {
        UserContactNumber = userContactNumber;
    }
}
