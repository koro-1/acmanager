package com.akong.acmanager.acm;

public interface ResultCallback {
    void onSuccess(int statusCode,String message);
    void onFailure(int statusCode,String errorMessage);

}
