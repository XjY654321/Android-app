package com.bs.demo.myapplication.ui.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.bean.KCInfo;
import com.bs.demo.myapplication.bean.Lsinfo;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.common.DetailListAdapter;
import com.bs.demo.myapplication.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class LSListActivity extends BaseActivity {


    private ListView lv;
    private DetailListAdapter detailListAdapter;
    private List<Lsinfo> kcInfos;
    private List<String> strings;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kclist);
        initView();
        //数据源
        ArrayList<String> spinners = new ArrayList<>();
        spinners.add("出库");
        spinners.add("入库");

        //设置ArrayAdapter内置的item样式-这里是单行显示样式
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinners);
        //这里设置的是Spinner的样式 ， 输入 simple_之后会提示有4人，如果专属spinner的话应该是俩种，在特殊情况可自己定义样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        //设置Adapter了
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                httpReq();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        setToolbarTitle("历史记录");
        strings = new ArrayList<>();
        kcInfos = new ArrayList<>();
        detailListAdapter = new DetailListAdapter(this, strings);
        lv.setAdapter(detailListAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(LSListActivity.this).setItems(new String[]{"删除"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                HttpUtil httpUtil = new HttpUtil();
                                httpUtil.addParams("id",kcInfos.get(position).getId());

                                httpUtil.sendGetRequest("DelLS", new HttpUtil.Callback() {
                                    @Override
                                    public void onSuccess(String json) {
                                        showToast("删除成功");
                                        httpReq();
                                    }

                                    @Override
                                    public void onFailure(String msg) {
                                        showToast(msg);
                                    }
                                });
                                break;
                        }
                    }
                }).show();
            }
        });

    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        sp = (Spinner) findViewById(R.id.sp);

    }

    private void httpReq() {
        kcInfos.clear();
        strings.clear();
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.addParams("bq", (String) sp.getSelectedItem());
        httpUtil.addParams("type", "0");
        httpUtil.sendGetRequest("GetLS", new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                List<Lsinfo> infos = new Gson().fromJson(json, new TypeToken<List<Lsinfo>>() {
                }.getType());
                kcInfos.addAll(infos);
                for (int i = 0; i < infos.size(); i++) {


                    strings.add("名称 : " + infos.get(i).getKcInfo().getHh()
                            + "\n规格 : " + infos.get(i).getKcInfo().getName()
                            + "\n价格 : " + infos.get(i).getKcInfo().getJg()
                            + "\n是否新品 : " + infos.get(i).getKcInfo().getNew_goods()
                            + "\n数量 : " + infos.get(i).getSl()
                            + "\n时间 : " + infos.get(i).getTime()

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
