package com.bs.demo.myapplication.ui.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.common.BtnListAdapter;
import com.bs.demo.myapplication.ui.user.activity.KCListActivity;

import java.util.ArrayList;
import java.util.List;


public class FromListActivity extends BaseActivity {

    int REQUEST_CODE = 1001;
    private BtnListAdapter btnListAdapter;
    private List<String> strings;
    private ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setToolbarTitle("统计报表");
        strings = new ArrayList<>();
        strings.add("收入报表");
        //strings.add("支出报表");
        strings.add("新旧产品库存数量对比图");
        strings.add("新旧产品库存总价对比图");
        strings.add("主要产品出库数量报表");
        strings.add("主要产品售出总价报表");
        btnListAdapter = new BtnListAdapter(this, strings);
        lv.setAdapter(btnListAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        go(ChartActivity.class);
                        break;
                  //  case 1:
                    //    go(Chart1Activity.class);
                      //  break;
                    case 1:
                        go(PieActivity.class);
                        break;
                    case 2:
                        go(Pie1Activity.class);
                        break;
                    case 3:
                        go(ColActivity.class);
                        break;
                    case 4:
                        go(Col1Activity.class);
                        break;


                }
            }
        });
    }


    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
    }
}