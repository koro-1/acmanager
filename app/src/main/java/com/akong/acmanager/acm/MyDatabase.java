package com.akong.acmanager.acm;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {AccountInfo.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract AccountDao userDao();
    public static MyDatabase init(Context context){

    return     Room.databaseBuilder(context, MyDatabase.class, "DemoDB")
                //是否允许在主线程上操作数据库，默认false。
                .allowMainThreadQueries()
                //数据库创建和打开的事件会回调到这里，可以再次操作数据库
                .addCallback(new RoomDatabase.Callback(){
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }
                })
                .build();



    }
}
