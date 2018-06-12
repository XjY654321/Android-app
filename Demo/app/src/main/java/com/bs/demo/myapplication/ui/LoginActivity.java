package com.bs.demo.myapplication.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.bean.UserInfo;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.ui.admin.AdminMainActivity;
import com.bs.demo.myapplication.ui.user.activity.MainActivity;
import com.bs.demo.myapplication.ui.user.activity.RegisterActivity;
import com.bs.demo.myapplication.utils.GlobalValue;
import com.bs.demo.myapplication.utils.HttpUtil;
import com.google.gson.Gson;


public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private EditText account;
    private EditText password;
    private Button btn_register;
    private Button btn_login;
    private RadioButton rbt_admin;
    private RadioButton rbt_use;
    private RadioButton rbt_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setToolbarTitle("登陆");
    }

    private void initView() {

        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        rbt_admin = (RadioButton) findViewById(R.id.rbt_admin);

        rbt_use = (RadioButton) findViewById(R.id.rbt_use);
        rbt_use.setChecked(true);
        rbt_teacher = (RadioButton) findViewById(R.id.rbt_teacher);

        account.setText("abc");
        password.setText("123");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                go(RegisterActivity.class);
                break;
            case R.id.btn_login:
//                go(AdminMainActivity.class);
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
        String api = "Login";
        String type = "1";
        if (rbt_use.isChecked()) {
            api = "Login";
            type = "1";
        }
        if (rbt_admin.isChecked()) {
            api = "AdminLogin";
        }
        if(rbt_teacher.isChecked()){
            api="Login";
            type = "2";
        }
        HttpUtil httpUtil =new HttpUtil();
        httpUtil.addParams("account", accountString);
        httpUtil.addParams("pwd", passwordString);
        httpUtil.addParams("type", type);
        httpUtil.sendGetRequest(api, new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                UserInfo u =new Gson().fromJson(json,UserInfo.class);
                GlobalValue.setUserInfo(u);
                if (rbt_use.isChecked()) {

                    go(MainActivity.class);

                }
                if (rbt_teacher.isChecked()) {

                    go(MainActivity.class);

                }
                if (rbt_admin.isChecked()) {

                    go(AdminMainActivity.class);
                }

            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
            }
        });

    }


}

