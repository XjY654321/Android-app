package com.bs.demo.myapplication.ui.user.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.bean.UserInfo;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.common.EdtListAdapter;
import com.bs.demo.myapplication.utils.GlobalValue;
import com.bs.demo.myapplication.utils.HttpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UserInfoSettingActivity extends BaseActivity implements View.OnClickListener {
    private EdtListAdapter edtListAdapter;
    private ListView lv;
    private FloatingActionButton fab;

    private List<String> title =new ArrayList<>();
    private List<String> content=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_setting);
        initView();
        setToolbarTitle("个人中心");

    }


    private void initView() {
        lv = findViewById(R.id.lv);
        fab =findViewById(R.id.fab);

        fab.setOnClickListener(this);
        getInfo();
    }

    private void getInfo() {
        HttpUtil httpUtil =new HttpUtil();
        httpUtil.addParams("id", GlobalValue.getUserInfo().getId());
        httpUtil.sendGetRequest("GetUserInfo", new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                UserInfo u =new Gson().fromJson(json,UserInfo.class);
                GlobalValue.setUserInfo(u);
                title.add("姓名 :");
                title.add("手机 :");
                title.add("身份证 :");
                title.add("地址 :");
                content.add(GlobalValue.getUserInfo().getName());
                content.add(GlobalValue.getUserInfo().getTel());
                content.add(GlobalValue.getUserInfo().getIdcard());
                content.add(GlobalValue.getUserInfo().getAddr());
                edtListAdapter=new EdtListAdapter(UserInfoSettingActivity.this,title,content);
                lv.setAdapter(edtListAdapter);
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                new AlertDialog.Builder(UserInfoSettingActivity.this).setTitle("提交这次修改?")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                HttpUtil httpUtil =new HttpUtil();
                                httpUtil.addParams("id", GlobalValue.getUserInfo().getId());
                                httpUtil.addParams("tel", edtListAdapter.getContents().get(1));
                                httpUtil.addParams("name", edtListAdapter.getContents().get(0));
                                httpUtil.addParams("addr", edtListAdapter.getContents().get(3));
                                httpUtil.addParams("idcard",edtListAdapter.getContents().get(2));
                                httpUtil.sendGetRequest("UserInfoSetting", new HttpUtil.Callback() {
                                    @Override
                                    public void onSuccess(String json) {
                                        showToast("修改成功");
                                    }

                                    @Override
                                    public void onFailure(String msg) {
                                        showToast(msg);
                                    }
                                });
                            }
                        }).show();
                break;
        }
    }
}
