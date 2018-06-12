package com.bs.demo.myapplication.ui.user.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.common.BtnListAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private BtnListAdapter btnListAdapter;
    private List<String> strings;
    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setToolbarTitle("主页");
        strings=new ArrayList<>();

        strings.add("个人信息");
        strings.add("修改密码");
        strings.add("退出登录");
        btnListAdapter=new BtnListAdapter(this,strings);
        lv.setAdapter(btnListAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        go(UserInfoSettingActivity.class);
                        break;
                    case 1:
                        go(ChangePwdActivity.class);
                        break;
                    case 2:
                        finish();
                        break;
                }
            }
        });
    }


    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
    }
}
