package com.bs.demo.myapplication.widget.dialog;

import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Author :     zjw
 * Date :       2017/1/12
 * Description: 时间选择器
 */

public class CTimePickerDialog {
    private Context context;
    private TimePickerDialog timePickerDialog;
    private OnTimePicked call;
    public interface OnTimePicked {
        void callback(TimePicker view, String hour, String minute);
    }
    public CTimePickerDialog(Context context, OnTimePicked onTimePicked) {
        this.context = context;
        call = onTimePicked;
        init();
    }
    private void init(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                DecimalFormat df = new DecimalFormat("00");
                call.callback(timePicker,df.format(hourOfDay) ,df.format(minute));
            }
        },hour , minute, DateFormat.is24HourFormat(context));
    }
    public void show(){
        timePickerDialog.show();
    }
}
