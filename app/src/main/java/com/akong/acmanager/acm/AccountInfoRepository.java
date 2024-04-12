package com.akong.acmanager.acm;

import android.content.Context;

import java.util.List;

public class AccountInfoRepository  {
    Context context;
    AccountDao accountDao;

    public AccountInfoRepository( Context context) {
        this. context=context;
        this.accountDao =  MyDatabase.init(context).userDao();
    }

    public void reset(List<AccountInfo> data) {
        accountDao.deleteAll();
        accountDao.insertAccountInfo(data.toArray(new AccountInfo[0]));
    }
}
