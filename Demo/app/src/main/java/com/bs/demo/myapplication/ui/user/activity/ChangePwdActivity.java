package com.bs.demo.myapplication.ui.user.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.utils.GlobalValue;
import com.bs.demo.myapplication.utils.HttpUtil;


public class ChangePwdActivity extends BaseActivity implements View.OnClickListener {

    private EditText new_password,sure_password;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        initView();
        setToolbarTitle("修改密码");
    }

    private void initView() {
        new_password = (EditText) findViewById(R.id.new_password);
        sure_password = (EditText) findViewById(R.id.sure_password);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);
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
        String password = new_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "新密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String surepassword = sure_password.getText().toString().trim();
        if (TextUtils.isEmpty(surepassword)) {
            Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(surepassword)){
            Toast.makeText(this, "二次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpUtil httpUtil =new HttpUtil();
        httpUtil.addParams("id", GlobalValue.getUserInfo().getId());
        httpUtil.addParams("pwd", password);
        httpUtil.sendGetRequest("ChangePwd", new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                showToast("修改成功!");
                finish();
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
            }
        });

    }
}
