package com.bs.demo.myapplication.ui.user.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.bean.KCInfo;
import com.bs.demo.myapplication.bean.UserInfo;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.common.DetailListAdapter;
import com.bs.demo.myapplication.utils.GlobalValue;
import com.bs.demo.myapplication.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class KCListActivity extends BaseActivity {


    private ListView lv;
    private DetailListAdapter detailListAdapter;
    private List<KCInfo> kcInfos;
    private List<String> strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kclist);
        initView();
        setToolbarTitle("库存");
        strings=new ArrayList<>();
        kcInfos=new ArrayList<>();
        detailListAdapter=new DetailListAdapter(this,strings);
        lv.setAdapter(detailListAdapter);
        httpReq();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(KCListActivity.this).setItems(new String[]{"出库","删除"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                LinearLayout linearLayout = new LinearLayout(KCListActivity.this);
                                linearLayout.setOrientation(LinearLayout.VERTICAL);
                                final EditText editText=new EditText(KCListActivity.this);
                                editText.setHint("请输入出库数量");
                                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                                linearLayout.addView(editText);
                                final EditText eTName=new EditText(KCListActivity.this);
                                eTName.setHint("请输入买家姓名");
                                linearLayout.addView(eTName);
                                final EditText eTMobile=new EditText(KCListActivity.this);
                                eTMobile.setHint("请输入买家电话");
                                linearLayout.addView(eTMobile);
                                new AlertDialog.Builder(KCListActivity.this).setTitle("请输入出库信息")
                                        .setView(linearLayout)
                                        .setNegativeButton("取消",null)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                final int s=Integer.parseInt(editText.getText().toString());
                                                if (s>Integer.parseInt(kcInfos.get(position).getSl())){
                                                    showToast("库存不够");
                                                    return;
                                                }
                                                final String name = eTName.getText().toString();
                                                if (TextUtils.isEmpty(name)){
                                                    showToast("输入买家姓名");
                                                    return;
                                                }
                                                final String mobile = eTMobile.getText().toString();
                                                if (TextUtils.isEmpty(mobile)){
                                                    showToast("输入买家电话");
                                                    return;
                                                }
                                                final String sr=(s*(Integer.parseInt(kcInfos.get(position).getJg())- Integer.parseInt(kcInfos.get(position).getRkjg()))  )+"";
                                                HttpUtil httpUtil = new HttpUtil();
                                                httpUtil.addParams("sl",s+"");
                                                httpUtil.addParams("kcid",kcInfos.get(position).getId());
                                                httpUtil.addParams("sr",sr);
                                                httpUtil.addParams("name",name);
                                                httpUtil.addParams("mobile",mobile);
                                                httpUtil.sendGetRequest("AdminCK", new HttpUtil.Callback() {
                                                    @Override
                                                    public void onSuccess(String json) {
                                                       showToast("出库成功,将收入+"+sr+"元");
                                                       httpReq();
                                                    }

                                                    @Override
                                                    public void onFailure(String msg) {
                                                        showToast(msg);
                                                    }
                                                });
                                            }
                                        }).show();
                                break;
                            case 1:
                                HttpUtil httpUtil = new HttpUtil();
                                httpUtil.addParams("id",kcInfos.get(position).getId());
                                httpUtil.sendGetRequest("DelKC", new HttpUtil.Callback() {
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
    }
    private void httpReq() {
        kcInfos.clear();
        strings.clear();
        HttpUtil httpUtil = new HttpUtil();

        httpUtil.sendGetRequest("GetKCList", new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                List<KCInfo> infos = new Gson().fromJson(json, new TypeToken<List<KCInfo>>() {
                }.getType());
                kcInfos.addAll(infos);
                for (int i = 0; i < infos.size(); i++) {
                    String st="";
                    int sl=Integer.parseInt(infos.get(i).getSl());
                    if (sl<10){
                        st="库存紧缺";
                    }else if (sl>=10&&sl<1000){
                        st="库存正常";
                    }else {
                        st="库存过载";
                    }
                    strings.add("商品名称 : " + infos.get(i).getHh()
                            + "\n商品规格 : " + infos.get(i).getName()
                            + "\n入库单价 : " + infos.get(i).getRkjg()+"元/件"
                            + "\n出库单价 : " + infos.get(i).getJg()+"元/件"
                            + "\n商品库存 : " + infos.get(i).getSl()
                            + "\n入库时间 : " + infos.get(i).getTime()
                            + "\n库存状态 : " + st
                            + "\n是否新品 : " + infos.get(i).getNew_goods()
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
