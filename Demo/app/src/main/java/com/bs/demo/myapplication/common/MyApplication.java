package com.bs.demo.myapplication.common;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;


/**
 * Author :     xjy
 * Date :       2018/3/12
 * Description:
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
