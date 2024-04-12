package com.akong.acmanager.acm;

public interface LogoutResultCallback {
    void onSuccess();
    void onFailure(int statusCode,String errorMessage);

}
