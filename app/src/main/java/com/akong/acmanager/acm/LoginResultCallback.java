package com.akong.acmanager.acm;

public interface LoginResultCallback {
    void onSuccess(AccountInfo info,LoggedInUserView loggedInUserView);
    void onFailure(int statusCode,String errorMessage);

}
