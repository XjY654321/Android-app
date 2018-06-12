package com.bs.demo.myapplication.widget.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Author :     zjw
 * Date :       2017/1/12
 * Description: 日期选择器
 */

public class CDatePickerDialog {
    private Context context;
    private DatePickerDialog datePickerDialog;
    private OnDatePicked call;
    public interface OnDatePicked {
        void callback(DatePicker view, String year, String month, String day);
    }
    public CDatePickerDialog(Context context, OnDatePicked  onDatePicked) {
        this.context = context;
        call = onDatePicked;
        init();
    }
    private void init(){
        final Calendar c = Calendar.getInstance();

       datePickerDialog =new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
               c.set(datePicker.getYear(), datePicker.getMonth(),datePicker.getDayOfMonth());
               DecimalFormat df = new DecimalFormat("00");
               call.callback(datePicker,df.format(i),df.format(i1+1),df.format(i2));
           }
       },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
    }
    public void show(){
        datePickerDialog.show();
    }
}
