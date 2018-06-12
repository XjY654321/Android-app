package com.bs.demo.myapplication.common;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.demo.myapplication.R;


/**
 * Author :     xjy
 * Date :
 * Description:
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected static String TAG_LOG = null;
    public Toolbar mToolbar;
    public TextView mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        TAG_LOG = this.getClass().getSimpleName();
        //设置全局背景
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(R.color.bg_main));

    }
    protected void showToast(String msg) {
        if (null != msg && !msg.equals("")) {
            Toast.makeText(this, msg + "", Toast.LENGTH_SHORT).show();
        }
    }


    protected void go(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    protected void go(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }

        startActivity(intent);
    }
    protected void setToolbarTitle(String title) {
        try {
            mTitle = (TextView) findViewById(R.id.tv_title);
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mTitle != null) {
                mTitle.setText(title);
            }
            if (mToolbar != null) {
                mToolbar.setTitle("");
                setSupportActionBar(mToolbar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
