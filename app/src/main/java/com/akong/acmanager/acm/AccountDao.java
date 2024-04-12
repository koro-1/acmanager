package com.akong.acmanager.acm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface AccountDao {
    /**
     * 增加
     *
     * @param users 用户
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccountInfo(AccountInfo... users);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Query("SELECT * FROM account_info")
    List<AccountInfo> getAllAccounts();
   // 实现save or update方法
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(AccountInfo... users);
    /**
     * 按用户名查询
     * @param userName 用户名
     * @return 用户
     */
    @Query("SELECT * FROM account_info WHERE accountId LIKE :userName LIMIT 1")
    AccountInfo findByName(String userName);

    /**
     * 修改
     * @param user 根据用户进行修改
     */
    @Update
    void update(AccountInfo user);

    /**
     * 删除
     * @param user 根据用户进行删除
     */
    @Delete
    void delete(AccountInfo user);


 //删除所有
    @Query("DELETE FROM account_info where 1=1")
    void deleteAll();
}
