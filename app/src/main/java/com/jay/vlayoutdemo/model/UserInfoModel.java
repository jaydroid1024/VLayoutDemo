package com.jay.vlayoutdemo.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/21.
 */

public class UserInfoModel implements Serializable {
    private String sessionKey;
    private String id;
    private String email;
    private String firstName;
    private String lastName;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserInfoModel() {
    }
}
