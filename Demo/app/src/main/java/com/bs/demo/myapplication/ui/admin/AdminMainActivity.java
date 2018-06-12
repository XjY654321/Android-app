package com.bs.demo.myapplication.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.common.BtnListAdapter;
import com.bs.demo.myapplication.ui.user.activity.KCListActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;


public class AdminMainActivity extends BaseActivity {

    int REQUEST_CODE=1001;
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
        strings.add("扫码入库");
        strings.add("查看库存");
        strings.add("联系供应商");
        strings.add("联系客户");
        strings.add("统计报表");
        strings.add("历史记录");
        strings.add("货物售出");
        strings.add("退出登录");
        btnListAdapter=new BtnListAdapter(this,strings);
        lv.setAdapter(btnListAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(AdminMainActivity.this, CaptureActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                       // Bundle bundle1=new Bundle();
                        //bundle1.putString("hh","测试");
                        //go(AddKCActivity.class,bundle1);
                        break;
                    case 1:
                        go(KCListActivity.class);
                        break;
                    case 2:
                        Intent intent2 = new Intent(AdminMainActivity.this,XSListActivity.class);
                        intent2.putExtra("type",1);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(AdminMainActivity.this,XSListActivity.class);
                        intent3.putExtra("type",2);
                        startActivity(intent3);
                        break;
                    case 4:
                        go(FromListActivity.class);
                        break;
                    case 5:
                        go(LSListActivity.class);
                        break;
                    case 7:
                        finish();
                        break;
                    case 6:
                        go(RenActivity.class);
                        break;
                }
            }
        });
    }


    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Bundle bundle1=new Bundle();
                    bundle1.putString("hh",result);
                    go(AddKCActivity.class,bundle1);
                    //  Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    showToast("解析二维码失败");
                    // Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
//虚拟机没有拨打电话.扫码的我模拟了完成扫描入库扫二维码 接着扫完会拿到货号调到这里填入货单