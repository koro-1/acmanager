package com.akong.acmanager.acm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akong.acmanager.R;
import com.loading.dialog.AndroidLoadingDialog;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;


public abstract class AbstractAccountActivity<T extends AccountManger,M extends BaseItemTouchAdapter> extends AppCompatActivity {
    protected    T instance;
    protected   M adapter;

     protected   Class Main,Login,Register;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XUI.init(getApplication());
        XUI.initTheme(this);
        setContentView(R.layout.activity_account);
        instance=initManger();
        adapter = initAdapter();
        setTargetActivity();
        adapter.setData(instance.accountInfoRepository.accountDao.getAllAccounts());
        RecyclerView recyclerView =findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        AccountInfo loginAccountInfo =instance.loggedinAccount;
        AndroidLoadingDialog iosLoadingDialog = new AndroidLoadingDialog().setHintMsg("正在切换账号中...").setOnTouchOutside(true);
        instance.getLoginResult().observe(this,loginResult -> {
            iosLoadingDialog.dismiss();
            Intent intent = new Intent(AbstractAccountActivity.this, Main);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
        adapter.setOnItemClickLitener(new OnItemClickLitener<AccountInfo>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onItemClick(View view, AccountInfo accountInfo) {
                boolean flag= loginAccountInfo.getAccountId().equals(accountInfo.getAccountId());
                if (!flag)
                {
                    iosLoadingDialog.show(getFragmentManager(), "AndroidLoadingDialog");
                    instance.swich(accountInfo.getAccountId(),accountInfo.getPassword());

                }
            }
        });

        //先实例化Callback
        ItemTouchHelper.Callback callback =initTouchHelperCallback();
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(recyclerView);
    }

    protected abstract T initManger() ;
    protected abstract M initAdapter() ;
    protected abstract void setTargetActivity() ;
    protected abstract ItemTouchHelper.Callback initTouchHelperCallback() ;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void click(View view) {

        new BottomSheet.BottomListSheetBuilder(this)
                .addItem("登录其他账号")
                .addItem("注册账号")
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                           if (position == 0){
                               startActivity(new Intent(AbstractAccountActivity.this, Login));
                           }else {
                               startActivity(new Intent(AbstractAccountActivity.this, Register));
                           }
                           dialog.dismiss();
                    }
                })
                .build()
                .show();

    }
}