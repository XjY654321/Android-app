package com.bs.demo.myapplication.ui.admin;

import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.bs.demo.myapplication.R;
import com.bs.demo.myapplication.bean.KCInfo;
import com.bs.demo.myapplication.bean.SrInfo;
import com.bs.demo.myapplication.common.BaseActivity;
import com.bs.demo.myapplication.utils.GlobalValue;
import com.bs.demo.myapplication.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class PieActivity extends BaseActivity {

    private TextView tv_sr;

    //饼形图控件
    private PieChartView pie_chart;
    //数据
    private PieChartData pieChardata;
    List<SliceValue> values = new ArrayList<SliceValue>();
    //定义数据，实际情况肯定不是这样写固定值的
    private int[] data = {21, 20};
    private int[] colorData = {Color.parseColor("#ec063d"),
            Color.parseColor("#333333")};
    private String[] stateChar = {"新产品", "旧产品"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        initView();
        setToolbarTitle("新旧产品库存数量对比图");

        pie_chart.setOnValueTouchListener(selectListener);//设置点击事件监听
         load();
       // setPieChartData();
//        initPieChart();

    }

    /**
     * 获取数据
     */
    private void setPieChartData() {

        for (int i = 0; i < data.length; ++i) {
            SliceValue sliceValue = new SliceValue((float) data[i], colorData[i]);
            values.add(sliceValue);
        }
    }

    /**
     * 初始化
     */
    private void initPieChart() {
        pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);//是否是环形显示
        pieChardata.setValues(values);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        pie_chart.setPieChartData(pieChardata);
        pie_chart.setValueSelectionEnabled(true);//选择饼图某一块变大
        pie_chart.setAlpha(0.9f);//设置透明度
        pie_chart.setCircleFillRatio(1f);//设置饼图大小

    }

    private void load() {
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.addParams("id", GlobalValue.getUserInfo().getId());

        httpUtil.sendGetRequest("GetKCList", new HttpUtil.Callback() {
            @Override
            public void onSuccess(String json) {
                List<KCInfo> b = new Gson().fromJson(json, new TypeToken<List<KCInfo>>() {
                }.getType());
               int isNew = 0;
               int isNotNew = 0;


                for (int i = 0; i < b.size(); i++) {
                    if("是".equals(b.get(i).getNew_goods())){
                        isNew = isNew + Integer.parseInt(b.get(i).getSl());
                    }else {
                        isNotNew = isNotNew + Integer.parseInt(b.get(i).getSl());
                    }
                }

                data[0] = isNew;
                data[1] = isNotNew;
                setPieChartData();
                initPieChart();
            }

            @Override
            public void onFailure(String msg) {
                showToast(msg);
            }
        });
    }

    private void initView() {
        pie_chart = (PieChartView) findViewById(R.id.lcv);
        tv_sr = (TextView) findViewById(R.id.tv_sr);

    }


    /**
     * 监听事件
     */
    private PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            pieChardata.setCenterText1(stateChar[arg0]);
            pieChardata.setCenterText1Color(colorData[arg0]);
            pieChardata.setCenterText1FontSize(10);
            pieChardata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
            pieChardata.setCenterText2Color(colorData[arg0]);
            pieChardata.setCenterText2FontSize(12);
            Toast.makeText(PieActivity.this, stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();
        }
    };
    private String calPercent(int i) {
        String result = "";
        int sum = 0;
        for (int i1 = 0; i1 < data.length; i1++) {
            sum += data[i1];
        }
        result = String.format("%.2f", (float) data[i] * 100 / sum) + "%";
        return result;
    }
}
