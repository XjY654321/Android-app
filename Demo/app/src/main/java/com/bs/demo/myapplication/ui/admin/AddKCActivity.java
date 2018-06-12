package com.bs.demo.myapplication.ui.admin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.utils.HttpUtil;

public class AddKCActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_hh;
    private EditText edt_sl;
    private EditText edt_jg;
    private Button btn_submit;
    private EditText edt_name;
    private EditText edt_rkjg,edt_gys_mobile,edt_gys_name,edt_new_goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kc);
        initView();
        setToolbarTitle("入库单");
        tv_hh.setText("货物名称 : " + getIntent().getStringExtra("hh"));
    }

    private void initView() {
        tv_hh = (TextView) findViewById(R.id.tv_hh);
        edt_sl = (EditText) findViewById(R.id.edt_sl);
        edt_jg = (EditText) findViewById(R.id.edt_jg);

        edt_new_goods = (EditText) findViewById(R.id.edt_new_goods);
        edt_gys_name = (EditText) findViewById(R.id.edt_gys_name);
        edt_gys_mobile = (EditText) findViewById(R.id.edt_gys_mobile);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_name.setOnClickListener(this);
        edt_rkjg = (EditText) findViewById(R.id.edt_rkjg);
        edt_rkjg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String sl = edt_sl.getText().toString().trim();
        if (TextUtils.isEmpty(sl)) {
            Toast.makeText(this, "入库数量不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String jg = edt_jg.getText().toString().trim();
        if (TextUtils.isEmpty(jg)) {
            Toast.makeText(this, "售价/件不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String name = edt_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "名称不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String rkjg = edt_rkjg.getText().toString().trim();
        if (TextUtils.isEmpty(rkjg)) {
            Toast.makeText(this, "成本价/件", Toast.LENGTH_SHORT).show();
            return;
        }
        String gys_name = edt_gys_name.getText().toString().trim();
        if (TextUtils.isEmpty(rkjg)) {
            Toast.makeText(this, "供应商名", Toast.LENGTH_SHORT).show();
            return;
        }
        String gys_mobile = edt_gys_mobile.getText().toString().trim();
        if (TextUtils.isEmpty(rkjg)) {
            Toast.makeText(this, "供应商联系电话", Toast.LENGTH_SHORT).show();
            return;
        }
        String new_goods = edt_new_goods.getText().toString().trim();
        if (TextUtils.isEmpty(rkjg)) {
            Toast.makeText(this, "是否是新产品", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.addParams("hh", getIntent().getStringExtra("hh"));
        httpUtil.addParams("sl", sl);
        httpUtil.addParams("jg", jg);
        httpUtil.addParams("rkjg",rkjg);
        httpUtil.addParams("name", name);
        httpUtil.addParams("gys_name", gys_name);
        httpUtil.addParams("gys_mobile", gys_mobile);
        httpUtil.addParams("new_goods", new_goods);
        httpUtil.sendGetRequest("AddKC", new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                showToast("入库成功");
                finish();
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
            }
        });

    }



}
