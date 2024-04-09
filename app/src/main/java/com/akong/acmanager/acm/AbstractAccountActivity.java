package com.akong.acmanager.acm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akong.acmanager.R;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;


public abstract class AbstractAccountActivity<T extends AccountManger,M extends BaseItemTouchAdapter> extends AppCompatActivity {
    protected    T instance;
    protected   M adapter;

     protected   Class Main,Login,Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XUI.initTheme(this);
        setContentView(R.layout.activity_account);
        instance=initManger();
        adapter = initAdapter();
        setTargetActivity();
        adapter.setData(instance.getAllAccounts());
        RecyclerView recyclerView =findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        AccountInfo loginAccountInfo =instance.getLoginAccountInfo();
        adapter.setOnItemClickLitener(new OnItemClickLitener<AccountInfo>() {
            @Override
            public void onItemClick(View view, AccountInfo accountInfo) {
                boolean flag= loginAccountInfo.getAccountId().equals(accountInfo.getAccountId());
                if (!flag)
                    instance.doInLogout(AbstractAccountActivity.this,new ResultCallback() {
                        @Override
                        public void onSuccess(int statusCode,String message) {
                            instance.doInLogin(AbstractAccountActivity.this,accountInfo, new ResultCallback() {
                                @Override
                                public void onSuccess(int statusCode,String message) {
                                    startActivity(new Intent(AbstractAccountActivity.this, Main));
                                }

                                @Override
                                public void onFailure(int statusCode,String message) {

                                }
                            });
                        }

                        @Override
                        public void onFailure(int statusCode,String message) {

                        }
                    });
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
                    }
                })
                .build()
                .show();

    }
}