package com.itgroup.bean;

public class Users {
    private String userId;      //USER_ID	VARCHAR2(50 BYTE)
    private String userName;    //USER_NAME	VARCHAR2(50 BYTE)

    public Users() {
    }

    public Users(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
