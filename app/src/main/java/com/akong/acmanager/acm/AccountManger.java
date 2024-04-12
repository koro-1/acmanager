package com.akong.acmanager.acm;


import android.app.Activity;
import android.content.Context;


import androidx.annotation.NonNull;

import androidx.lifecycle.LiveData;

import java.util.Stack;


public abstract class AccountManger {


    AccountInfoRepository accountInfoRepository;
    AccountInfo loggedinAccount;
    public  Context context;

    public AccountManger(@NonNull Context context) {

       this.context = context;
       accountInfoRepository = new AccountInfoRepository(context);

    }

    public AccountInfo getLoggedinAccount() {
        return loggedinAccount;
    }

    public void setLoggedinAccount(AccountInfo loggedinAccount) {
        this.loggedinAccount = loggedinAccount;
    }
    // 自定义非粘性livedata
    private MyMutableLiveData<LoginResult> loginResult = new MyMutableLiveData<>();


   public LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }



    public void login(String username, String password){
        AccountInfo loggedinAccount1 = getLoggedinAccount();
        if (loggedinAccount1 != null ){
            logout();
        }
        doInLogin(username,password,new LoginResultCallback() {
            @Override
            public void onSuccess(AccountInfo info,LoggedInUserView userView) {

                setLoggedinAccount(info);
                accountInfoRepository.accountDao.insertOrUpdate(info);
                loginResult.postValue(new LoginResult(userView));
            }

            @Override
            public void onFailure(int statusCode, String errorMessage) {
                loginResult.postValue(new LoginResult(statusCode));
            }
        });

    }
    public void swich(String username, String password){
        doInLogout(new LogoutResultCallback(){
            @Override
            public void onSuccess() {
                setLoggedinAccount(null);
                login(username,password);
            }

            @Override
            public void onFailure(int statusCode, String errorMessage) {

            }
        });

    }
    public void logout(){

        doInLogout(new LogoutResultCallback(){
            @Override
            public void onSuccess() {
                   setLoggedinAccount(null);

            }

            @Override
            public void onFailure(int statusCode, String errorMessage) {

            }
        });
    }
   protected abstract void doInLogin(String username, String password, LoginResultCallback callback);
    protected abstract  void doInLogout( LogoutResultCallback callback);
    protected abstract  void autoLogin(LoginResultCallback callback);



}
