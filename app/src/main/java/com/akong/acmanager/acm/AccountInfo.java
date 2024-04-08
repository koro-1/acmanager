package com.akong.acmanager.acm;

public class AccountInfo {
    private String name;
    private String accountId;
    private String password;
    private String imgUrl;

    public AccountInfo() {
    }

    public AccountInfo(String name, String accountId, String password, String imgUrl) {
        this.name = name;
        this.accountId = accountId;
        this.password = password;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
