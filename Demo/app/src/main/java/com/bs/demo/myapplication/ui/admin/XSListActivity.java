package com.bs.demo.myapplication.ui.admin;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.bean.UserInfo;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.common.InfoListAdapter;

import com.bs.demo.myapplication.utils.GlobalValue;
import com.bs.demo.myapplication.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class XSListActivity extends BaseActivity{
    private List<UserInfo> infoList;
    private List<String> stringList;
    private InfoListAdapter infoListAdapter;
    private ListView lv;
    private FloatingActionButton fab;
    int type = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xslist);
        type = getIntent().getIntExtra("type",1);
        initView();
        if(type == 1){
            setToolbarTitle("供应商列表");
        }else {
            setToolbarTitle("客户列表");
        }

        infoList = new ArrayList<>();
        stringList = new ArrayList<>();
        lv = findViewById(R.id.lv);
        infoListAdapter = new InfoListAdapter(this, stringList);
        lv.setAdapter(infoListAdapter);
        httpReq();
        fab.setVisibility(View.GONE);

    }
    private void httpReq() {
        infoList.clear();
        stringList.clear();
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.addParams("type", ""+type);
        httpUtil.sendGetRequest("GetUserList", new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                List<UserInfo> infos = new Gson().fromJson(json, new TypeToken<List<UserInfo>>() {
                }.getType());
                infoList.addAll(infos);
                for (int i = 0; i < infos.size(); i++) {
                    stringList.add("姓名 : " + infos.get(i).getName()
                            + "\n电话 : " + infos.get(i).getTel()
                            + "\n地址 : " + infos.get(i).getAddr()

                    );
                }
                infoListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new RxPermissions(XSListActivity.this).request(Manifest.permission.CALL_PHONE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+infoList.get(position).getTel()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else {
                            showToast("请授权...");
                        }
                    }
                });



            }
        });
    }

    private void initView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);

    }


}
