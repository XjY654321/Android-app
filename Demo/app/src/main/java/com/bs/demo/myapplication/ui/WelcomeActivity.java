package com.bs.demo.myapplication.ui;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.ui.LoginActivity;
import com.bs.demo.myapplication.ui.admin.AdminMainActivity;
import com.bs.demo.myapplication.ui.admin.XSListActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;


public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Handler handler =new Handler();
        Runnable runnable =new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();

            }
        };
        handler.postDelayed(runnable,3000);
    }


}
