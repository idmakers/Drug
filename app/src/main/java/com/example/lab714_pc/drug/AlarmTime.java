package com.example.lab714_pc.drug;

/**
 * Created by 714B on 2017/10/30.
 */
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import static com.example.lab714_pc.drug.R.id.add;
import static com.example.lab714_pc.drug.R.id.befaf;
import static com.example.lab714_pc.drug.R.id.time;
import static com.example.lab714_pc.drug.R.id.update;


public class AlarmTime extends MainActivity {

    private AlarmDBHelper helper;
    private EditText day;
    private TextView displayedText;
    private EditText moring ,noon,night;
    private Button btUP;
    private int mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmtime);
        findViews();
        helper = new AlarmDBHelper(this,"expense.db",null,1);
        moring = (EditText)findViewById(R.id.morining);
        noon = (EditText)findViewById(R.id.noon);
        night = (EditText)findViewById(R.id.night);
        btUP = (Button) findViewById(R.id.update);
        btUP.setOnClickListener(this);

    }
    /*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                add();
                break;
            case R.id.time:
                showTimePickerDialog();
                break;
        }

    }
*/
    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case update:
                add();
                break;

        }
    }
    //輸入時間
/*
    public void showTimePickerDialog() {
        // 設定初始時間
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // 跳出時間選擇器
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // 完成選擇，顯示時間
                        tvTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        tpd.show();
    }
*/
    private void add() {
        Intent intent = new Intent();
        String mmoring = moring.getText().toString();
        String mnoon = noon.getText().toString();
        String mnight = night.getText().toString();
        ContentValues values = new ContentValues();
        values.put("moring", mmoring);
        values.put("mnoon", mnoon);
        values.put("mnight", mnight);

        long id = helper.getWritableDatabase().insert("exp", null, values);
        Log.d("ADD", id + "");
        intent.setClass(AlarmTime.this, MainActivity.class);
        startActivity(intent);
    }


    private void findViews() {
        moring = (EditText) findViewById(R.id.morining);
        noon = (EditText) findViewById(R.id.noon);
        night = (EditText) findViewById(R.id.night);

    }


}

