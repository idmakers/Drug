package com.example.lab714_pc.drug;

/**
 * Created by 714B on 2017/10/30.
 */
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.media.MediaPlayer;
import android.media.SoundPool;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.lab714_pc.drug.R.id.btnbreakfestaf;
import static com.example.lab714_pc.drug.R.id.btnbreakfestbf;

import static com.example.lab714_pc.drug.R.id.btnnoonbf;
import static com.example.lab714_pc.drug.R.id.update;


public class AlarmTime extends Base
        implements View.OnClickListener{

    private SoundPool sp;
    private boolean spLoader = false;
    MediaPlayer mPlayer = new MediaPlayer();
    public PendingIntent pi;
    public  Intent intent11;
    private MyDBHelper helper;
    private EditText day;
    private TextView displayedText;
    private EditText morning ,noon,night,midnight;
    private Button btUP,btnMORNING,btnNOON,btnNIGHT,btnMIDNIGHT;
    private int mHour, mMinute , mSecoond;
    private EditText breakfestbf , breakfestaf , noonbf, noonaf, nightbf , nightaf ;
    static long id;
    private  Button btbreakbf ,btbreakaf, btnoonbf , btnoonaf , btnightbf, btnightaf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmtime);

        helper = new MyDBHelper(this,"expense.db",null,1);
          breakfestbf = (EditText)findViewById(R.id.breakfestbf);
          breakfestaf = (EditText)findViewById(R.id.breakfestaf);
        noonbf = (EditText)findViewById(R.id.noonbf);
        noonaf = (EditText)findViewById(R.id.noonaf);
        nightbf = (EditText)findViewById(R.id.nightbf);
        nightaf = (EditText)findViewById(R.id.nightaf);
//        morning = (EditText)findViewById(R.id.morining);
//        noon = (EditText)findViewById(R.id.noon);
//        night = (EditText)findViewById(R.id.night);
//        midnight = (EditText)findViewById(R.id.midnight);
//        btUP = (Button) findViewById(R.id.update);
//        btUP.setOnClickListener(this);
//        btnMORNING =(Button)findViewById(R.id.btnmorning);
//        btnMORNING.setOnClickListener(this);
//        btnNOON =(Button)findViewById(R.id.btnnoon);
//        btnNOON.setOnClickListener(this);
//        btnNIGHT =(Button)findViewById(R.id.btnnight);
//        btnNIGHT.setOnClickListener(this);
//        btnMIDNIGHT = (Button)findViewById(R.id.btnmidnight);
//        btnMIDNIGHT.setOnClickListener(this);
        btbreakbf = (Button)findViewById(R.id.btnbreakfestbf);
        btbreakbf.setOnClickListener(this);
        btbreakaf = (Button)findViewById(R.id.btnbreakfestaf);
        btbreakaf.setOnClickListener(this);
        btnoonbf = (Button)findViewById(R.id.btnnoonbf);
        btnoonbf.setOnClickListener(this);
        btnoonaf = (Button)findViewById(R.id.btnnoonaf);
        btnoonaf.setOnClickListener(this);
        btnightbf = (Button)findViewById(R.id.btnnightbf);
        btnightbf.setOnClickListener(this);
        btnightaf = (Button)findViewById(R.id.btnnightaf);
        btnightaf.setOnClickListener(this);

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
            case btnbreakfestbf:
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
            case btnbreakfestaf:
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
            case R.id.btnnoonbf:
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
            case R.id.btnnoonaf:
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

            case R.id.btnmidnight:
                // 設定初始時間
                final Calendar f1 = Calendar.getInstance();
                mHour = f1.get(Calendar.HOUR_OF_DAY);
                mMinute = f1.get(Calendar.MINUTE);
                // 跳出時間選擇器
                TimePickerDialog tpdmidnight1 = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // 完成選擇，顯示時間
                                midnight.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                tpdmidnight1.show();
                break;

            case R.id.btnnightbf:
                // 設定初始時間
                final Calendar f2 = Calendar.getInstance();
                mHour = f2.get(Calendar.HOUR_OF_DAY);
                mMinute = f2.get(Calendar.MINUTE);
                // 跳出時間選擇器
                TimePickerDialog tpdmidnight2 = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // 完成選擇，顯示時間
                                midnight.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                tpdmidnight2.show();
                break;

            case R.id.btnnightaf:
                // 設定初始時間
                final Calendar f3 = Calendar.getInstance();
                mHour = f3.get(Calendar.HOUR_OF_DAY);
                mMinute = f3.get(Calendar.MINUTE);
                // 跳出時間選擇器
                TimePickerDialog tpdmidnight3 = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                // 完成選擇，顯示時間
                                midnight.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                tpdmidnight3.show();
                break;

            case update:
                Cursor breakbfc = helper.filList(1);
                Cursor breakafc = helper.filList(2);
                Cursor noonbfc = helper.filList(3);
                Cursor noonafc = helper.filList(4);
                Cursor nightbfc = helper.filList(5);
                Cursor nightafc = helper.filList(6);
                Cursor mid = helper.filList(7);
                add();
                alarm(breakbfc.getString(1),1);
                alarm(breakafc.getString(1),1);
                alarm(noonbfc.getString(1),1);
                alarm(noonafc.getString(1),1);
                alarm(nightbfc.getString(1),1);
                alarm(nightafc.getString(1),1);
                alarm(mid.getString(1),4);
                break;

        }
    }

    private void add() {
        Intent intent = new Intent();
        String breakfestbf0 = breakfestbf.getText().toString();
        String breakfestaf0 = breakfestaf.getText().toString();
        String noonbf0 = noonbf.getText().toString();
        String noonaf0 = noonaf.getText().toString();
        String nightbf0 = nightbf.getText().toString();
        String nightaf0 = nightaf.getText().toString();
//        String mnoon = noon.getText().toString();
 //       String mnight = night.getText().toString();
        String mmidnight = midnight.getText().toString();
        ContentValues values = new ContentValues();
        //java.util.Date mnoon= new java.util.Date();
       // java.util.Date mmoring= new java.util.Date();
        //java.util.Date mmidnight= new java.util.Date();
       // java.util.Date mnight= new java.util.Date();
        // java.util.Date mnoon= new java.util.Date();

    if(!helper.isEmpty()){
        values.put("Aname","breakfestbf");
        values.put("Atime", breakfestbf0);
        helper.getWritableDatabase().insert("ALARM", null, values);
        values.clear();
        values.put("Aname","breakfestaf");
        values.put("Atime", breakfestaf0);
        helper.getWritableDatabase().insert("ALARM", null, values);
        values.clear();
        values.put("Aname","noonbf");
        values.put("Atime", noonbf0);
        helper.getWritableDatabase().insert("ALARM", null, values);
        values.clear();
        values.put("Aname","noonaf");
        values.put("Atime", noonaf0);
        helper.getWritableDatabase().insert("ALARM", null, values);
        values.clear();
        values.put("Aname","nightbf");
        values.put("Atime", nightbf0);
        helper.getWritableDatabase().insert("ALARM", null, values);
        values.clear();
        values.put("Aname","nightaf");
        values.put("Atime", nightaf0);
        helper.getWritableDatabase().insert("ALARM", null, values);
        values.clear();
        values.put("Aname","midnight");
        values.put("Atime", mmidnight);
        helper.getWritableDatabase().insert("ALARM", null, values);
        values.clear();



    }
    else
    {
        id = helper.update(1,breakfestbf0);
        id = helper.update(2,breakfestaf0);
        id = helper.update(3,noonbf0);
        id = helper.update(4,noonaf0);
        id = helper.update(5,nightbf0);
        id = helper.update(6,nightaf0);
        id = helper.update(7,mmidnight);

    }

        intent.setClass(AlarmTime.this, Base.class);
        startActivity(intent);
    }



    public void alarm (String alarmtimein , int id) {


        SimpleDateFormat time = new SimpleDateFormat("yyyy MM dd HH:mm");
        SimpleDateFormat date = new SimpleDateFormat("yyyy MM dd");
        try {
            intent11 = new Intent(getApplicationContext(), PlayReceiver.class);
            Date today  = new Date();
            String s = date.format(today);
            String times = s + " " + alarmtimein;
            Date alarmtimeout = time.parse(times);
            long milliseconds = alarmtimeout.getTime();
            Log.w("msg", "Date s  " + s);
            Log.w("msg", "Date d  " + today);
            Log.w("msg", "time s  " + times);
            Log.w("msg", "alarm time in  " + milliseconds);
            Log.w("msg", "alarm time out  " + alarmtimeout);
            Log.w("msg", "milliseconds  "   + milliseconds);
            Log.w("msg", "id  "   + id);



            intent11.putExtra("msg", "play_voice"+id);

            long elapsed =  milliseconds;
            pi = PendingIntent.getBroadcast(this, id, intent11,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, elapsed, AlarmManager.INTERVAL_DAY, pi);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public   void alarmCancel(int id){
        intent11 = new Intent(getApplicationContext(), PlayReceiver.class);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        pi = PendingIntent.getBroadcast(this, id, intent11,PendingIntent.FLAG_UPDATE_CURRENT);


        // Cancel alarms
        try {
            am.cancel( pi);
        } catch (Exception e) {
            Log.w("MSG", "AlarmManager update was not canceled. " + e.toString());
        }
    }
    private void displayAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setCancelable(
                false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    }



