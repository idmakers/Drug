package com.example.lab714_pc.drug;

/**
 * Created by 714B on 2017/10/30.
 */
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.lab714_pc.drug.R.id.btnmidnight;
import static com.example.lab714_pc.drug.R.id.btnmorning;
import static com.example.lab714_pc.drug.R.id.btnnight;
import static com.example.lab714_pc.drug.R.id.btnnoon;
import static com.example.lab714_pc.drug.R.id.update;


public class AlarmTime extends Base
        implements View.OnClickListener{

    private MyDBHelper helper;
    private EditText day;
    private TextView displayedText;
    private EditText morning ,noon,night,midnight;
    private Button btUP,btnMORNING,btnNOON,btnNIGHT,btnMIDNIGHT;
    private int mHour, mMinute , mSecoond;

    static long id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmtime);
        findViews();
        helper = new MyDBHelper(this,"expense.db",null,1);
        morning = (EditText)findViewById(R.id.morining);
        noon = (EditText)findViewById(R.id.noon);
        night = (EditText)findViewById(R.id.night);
        midnight = (EditText)findViewById(R.id.midnight);
        btUP = (Button) findViewById(R.id.update);
        btUP.setOnClickListener(this);
        btnMORNING =(Button)findViewById(R.id.btnmorning);
        btnMORNING.setOnClickListener(this);
        btnNOON =(Button)findViewById(R.id.btnnoon);
        btnNOON.setOnClickListener(this);
        btnNIGHT =(Button)findViewById(R.id.btnnight);
        btnNIGHT.setOnClickListener(this);
        btnMIDNIGHT = (Button)findViewById(R.id.btnmidnight);
        btnMIDNIGHT.setOnClickListener(this);
        btadd = (Button) findViewById(R.id.addh);
        btadd.setOnClickListener(onClickListener);
        btitem = (Button) findViewById(R.id.item);
        btitem.setOnClickListener(onClickListener);

        btalarm = (Button) findViewById(R.id.alarm);
        btalarm.setOnClickListener(onClickListener);
        btalarmL = (Button) findViewById(R.id.QRcode);
        btalarmL.setOnClickListener(onClickListener);
        btnotify = (Button) findViewById(R.id.Notification);
        btnotify.setOnClickListener(onClickListener);;
        history = (Button) findViewById(R.id.history);
        history.setOnClickListener(onClickListener);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        if(helper.isEmpty()){
        Cursor morn = helper.filList(1);
        Cursor noo = helper.filList(2);
        Cursor ni = helper.filList(3);
        Cursor mid = helper.filList(4);



        morning.setText(morn.getString(1));
        noon.setText(noo.getString(1));
        night.setText(ni.getString(1));
        midnight.setText(mid.getString(1));

    }







    }


    @Override
    public void onClick (View v) {
        switch (v.getId()) {
//                  case R.id.QRcode:
//                 Intent intent = new Intent("com.google.zxing.client.android.SCAN");	//開啟條碼掃描器
//                   intent.putExtra("SCAN_MODE", "QR_CODE_MODE");	//設定QR Code參數
//                   startActivityForResult(intent, 1);	//要求回傳1
//                   break;
            case btnmorning:
                // 設定初始時間
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                mSecoond = c.get(Calendar.SECOND);

                // 跳出時間選擇器
                TimePickerDialog tpd = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute ) {
                                // 完成選擇，顯示時間
                                morning.setText(hourOfDay + ":" + minute );
                            }
                        }, mHour, mMinute, true);
                tpd.show();
                break;
            case btnnoon:
                // 設定初始時間
                final Calendar d = Calendar.getInstance();
                mHour = d.get(Calendar.HOUR_OF_DAY);
                mMinute = d.get(Calendar.MINUTE);

                // 跳出時間選擇器
                TimePickerDialog tpdnoon = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // 完成選擇，顯示時間
                                noon.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                tpdnoon.show();
                break;
            case btnnight:
                // 設定初始時間
                final Calendar e = Calendar.getInstance();
                mHour = e.get(Calendar.HOUR_OF_DAY);
                mMinute = e.get(Calendar.MINUTE);
                // 跳出時間選擇器
                TimePickerDialog tpdnight = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // 完成選擇，顯示時間
                                night.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                tpdnight.show();
                break;
            case btnmidnight:
                // 設定初始時間
                final Calendar f = Calendar.getInstance();
                mHour = f.get(Calendar.HOUR_OF_DAY);
                mMinute = f.get(Calendar.MINUTE);
                // 跳出時間選擇器
                TimePickerDialog tpdmidnight = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // 完成選擇，顯示時間
                                midnight.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                tpdmidnight.show();
                break;

            case update:
                add();
                break;

        }
    }

    private void add() {
        Intent intent = new Intent();
        String mmoring = morning.getText().toString();
        String mnoon = noon.getText().toString();
        String mnight = night.getText().toString();
        String mmidnight = midnight.getText().toString();
        ContentValues values = new ContentValues();
        //java.util.Date mnoon= new java.util.Date();
       // java.util.Date mmoring= new java.util.Date();
        //java.util.Date mmidnight= new java.util.Date();
       // java.util.Date mnight= new java.util.Date();
        // java.util.Date mnoon= new java.util.Date();

    if(!helper.isEmpty()){
        values.put("Aname","morning");
        values.put("Atime", mmoring);
        long id = helper.getWritableDatabase().insert("ALARM", null, values);
        values.clear();
        Log.d("ADD", id + "");
        values.put("Aname","noon");
        values.put("Atime", mnoon);
        id = helper.getWritableDatabase().insert("ALARM", null, values);
        Log.d("ADD", id + "");
        values.clear();
        values.put("Aname","night");
        values.put("Atime", mnight);
        id = helper.getWritableDatabase().insert("ALARM", null, values);
        Log.d("ADD", id + "");
        values.clear();
        values.put("Aname","midnight");
        values.put("Atime", mmidnight);
        id = helper.getWritableDatabase().insert("ALARM", null, values);
        Log.d("ADD", id + "");
        values.clear();
    }
    else
    {
        id = helper.update(1,mmoring);
        id = helper.update(2,mnoon);
        id = helper.update(3,mnight);
        id = helper.update(4,mmidnight);

    }

        intent.setClass(AlarmTime.this, Base.class);
        startActivity(intent);
    }


    private void findViews() {
        morning = (EditText) findViewById(R.id.morining);
        noon = (EditText) findViewById(R.id.noon);
        night = (EditText) findViewById(R.id.night);
        midnight =(EditText)findViewById(R.id.midnight);

    }


}