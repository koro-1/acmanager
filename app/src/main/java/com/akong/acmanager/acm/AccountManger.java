package com.akong.acmanager.acm;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AccountManger {
       String authToken;
    public   Context context;

    public AccountManger(Context context) {
        this.context = context;
    }

    public List<AccountInfo> getAllAccounts(){
        String accounts = (String) SPUtil.get(context, "accounts", "");
        List<AccountInfo> accountInfos = JSON.parseArray(accounts, AccountInfo.class);
        return accountInfos==null?new ArrayList<>():accountInfos;
    }
    public void setAllAccounts( List<AccountInfo> list) {

        SPUtil.put(context, "accounts", JSON.toJSONString(list));
    }
    public String getAuthToken() {
        String authToken = (String) SPUtil.get(context, "authToken", "");
        return authToken;
    }

    public void setAuthToken(String authToken) {
        SPUtil.put(context, "authToken", authToken);
    }

    protected boolean addAccount(AccountInfo info){
        List<AccountInfo> accounts =getAllAccounts();
         Map<String, AccountInfo> existingByName = null;
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
             existingByName = accounts.stream()
                     .collect(Collectors.toMap(AccountInfo::getName, item -> item));
         }
         existingByName.put(info.getName(), info);
        // map转list
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
             accounts=existingByName.values().stream().collect(Collectors.toList());
         }

        SPUtil.put(context, "accounts", JSON.toJSONString(accounts));
         setLoginAccountInfo(info);
         return true;
    }

    public AccountInfo getLoginAccountInfo() {
        String loginAccount = (String) SPUtil.get(context, "loginAccountInfo", "");
        return JSON.parseObject(loginAccount, AccountInfo.class);
    }

    public void setLoginAccountInfo(AccountInfo loginAccountInfo) {
        SPUtil.put(context, "loginAccountInfo", JSON.toJSONString(loginAccountInfo));

    }
  public abstract void doInLogin(AppCompatActivity onOwner, AccountInfo info, ResultCallback callback);
    public abstract  void doInLogout(AppCompatActivity onOwner, ResultCallback callback);
    public abstract  void autoLogin(AppCompatActivity onOwner,ResultCallback callback);



}
