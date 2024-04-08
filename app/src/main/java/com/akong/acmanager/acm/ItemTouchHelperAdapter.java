package com.akong.acmanager.acm;

public interface ItemTouchHelperAdapter {


    //数据删除
    void onItemDissmiss(int position);
    void onItemMove(int fromPosition,int toPosition);
}