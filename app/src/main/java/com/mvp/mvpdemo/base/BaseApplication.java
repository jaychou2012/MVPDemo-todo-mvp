package com.mvp.mvpdemo.base;

import android.app.Application;

import com.mvp.mvpdemo.utils.Utils;

/**
 * Created by Tandong on 2018/4/2.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.getInstanceSp(getApplicationContext());
    }
}
