package com.example.application1.pojo;

import com.google.gson.annotations.SerializedName;

public class GithubUserModel {

    @SerializedName("login")
    private String userName;
    @SerializedName("url")
    private String userUrl;

    public GithubUserModel(String userName, String userUrl) {
        this.userName = userName;
        this.userUrl = userUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
