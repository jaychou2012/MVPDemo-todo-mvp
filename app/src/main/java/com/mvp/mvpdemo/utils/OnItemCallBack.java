package com.mvp.mvpdemo.utils;

import android.view.View;

/**
 * Created by Tandong on 2018/3/28.
 */

public interface OnItemCallBack {
    public void onItemLongClick(View v, int posiotion);

    public void onItemClick(View v, int position);
}
