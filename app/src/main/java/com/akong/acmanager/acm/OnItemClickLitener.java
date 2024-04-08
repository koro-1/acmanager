package com.akong.acmanager.acm;

import android.view.View;

public interface OnItemClickLitener <T>
{
    void onItemClick(View view, T t);

}