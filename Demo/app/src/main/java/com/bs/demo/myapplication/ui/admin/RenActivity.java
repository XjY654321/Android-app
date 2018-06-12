package com.bs.demo.myapplication.ui.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.bean.Lsinfo;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.common.DetailListAdapter;
import com.bs.demo.myapplication.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class RenActivity extends BaseActivity {


    private ListView lv;
    private DetailListAdapter detailListAdapter;
    private List<Lsinfo> kcInfos;
    private List<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ren);
        initView();

        setToolbarTitle("货物售出");
        strings = new ArrayList<>();
        kcInfos = new ArrayList<>();
        detailListAdapter = new DetailListAdapter(this, strings);
        lv.setAdapter(detailListAdapter);

    }

    private EditText hhEt,ggEt;
    private AppCompatButton cxBt;
    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        hhEt = (EditText) findViewById(R.id.hh);
        ggEt = (EditText) findViewById(R.id.gg);
        cxBt = (AppCompatButton) findViewById(R.id.btn_cx);
        cxBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String hh = hhEt.getText().toString();
               String gg = ggEt.getText().toString();
                httpReq(hh,gg);
            }
        });
    }

    private void httpReq(String hh,String gg) {
        kcInfos.clear();
        strings.clear();
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.addParams("bq", "出库");
        httpUtil.addParams("hh", hh);
        httpUtil.addParams("gg", gg);
        httpUtil.addParams("type", "1");
        httpUtil.sendGetRequest("GetLS", new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                List<Lsinfo> infos = new Gson().fromJson(json, new TypeToken<List<Lsinfo>>() {
                }.getType());
                kcInfos.addAll(infos);
                for (int i = 0; i < infos.size(); i++) {

                    strings.add("姓名 : " + infos.get(i).getName()
                            + "\n电话 : " + infos.get(i).getMobile()
                            + "\n购买数量 : " + infos.get(i).getSl()


                    );
                }
                detailListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
            }
        });

    }
}
