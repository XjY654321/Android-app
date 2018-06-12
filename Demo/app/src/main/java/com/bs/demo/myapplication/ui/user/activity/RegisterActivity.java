package com.bs.demo.myapplication.ui.user.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.utils.HttpUtil;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private EditText account;
    private EditText password,sure_password,name,phone;
    private Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        setToolbarTitle("注册");
    }
    private RadioButton rbt_use;
    private RadioButton rbt_teacher;

    private void initView() {
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.sure_phone);
        name = (EditText) findViewById(R.id.sure_name);
        sure_password = (EditText) findViewById(R.id.sure_password);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);


        rbt_use = (RadioButton) findViewById(R.id.rbt_use);
        rbt_use.setChecked(true);
        rbt_teacher = (RadioButton) findViewById(R.id.rbt_teacher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String accountString = account.getText().toString().trim();
        if (TextUtils.isEmpty(accountString)) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String surePwd = sure_password.getText().toString().trim();
        if (TextUtils.isEmpty(surePwd)) {
            Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!passwordString.equals(surePwd)){
            Toast.makeText(this, "二次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        String nameStr = name.getText().toString().trim();
        if (TextUtils.isEmpty(nameStr)) {
            Toast.makeText(this, "名字", Toast.LENGTH_SHORT).show();
            return;
        }
        String phoneStr = phone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneStr)) {
            Toast.makeText(this, "手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        int type = 1;
        if (rbt_use.isChecked()) {//供应
            type = 1;
        }

        if(rbt_teacher.isChecked()){//客户
           type = 2;
        }
        // TODO validate success, do something
        String api ="Register";
        HttpUtil httpUtil =new HttpUtil();
        httpUtil.addParams("account", accountString);
        httpUtil.addParams("pwd", passwordString);
        httpUtil.addParams("name", nameStr);
        httpUtil.addParams("phone", phoneStr);
        httpUtil.addParams("type", ""+type);

        httpUtil.sendGetRequest(api, new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                showToast("注册成功");
                finish();
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
            }
        });
    }
}

