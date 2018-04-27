package com.example.lab714_pc.drug;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.Toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.lab714_pc.drug.R.id.add;
import static com.example.lab714_pc.drug.R.id.befaf;
import static com.example.lab714_pc.drug.R.id.eat;
import static com.example.lab714_pc.drug.R.id.item;
import static com.example.lab714_pc.drug.R.id.time;
import static com.example.lab714_pc.drug.R.id.time_eat;


public class AddByHand extends Base
        implements View.OnClickListener{

    private static Context context;
    private EditText amount;
    private MyDBHelper helper;
    private EditText name;
    private EditText method;
    private EditText day;
    private TextView displayedText;
    Button btn, btn1;

    private EditText tvTime ,afbf;
    private Button btTime, btAdd,btAf;
    private int mHour, mMinute;
    Intent intent = new Intent();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbyhand);

        findViews();

        helper = new MyDBHelper(this,"expense.db",null,1);
        tvTime = (EditText)findViewById(R.id.time_eat);
        afbf = (EditText)findViewById(R.id.eat);
        btTime = (Button) findViewById(R.id.time);
        btTime.setOnClickListener(this);
        btAf = (Button) findViewById(R.id.befaf);
        btAf.setOnClickListener(this);
        btAdd = (Button) findViewById(R.id.add);
        btAdd.setOnClickListener(this);
        btadd = (Button) findViewById(R.id.addh);
        btadd.setOnClickListener(onClickListener);
        btitem = (Button) findViewById(R.id.item);
        btitem.setOnClickListener(onClickListener);
        btOCR = (Button) findViewById(R.id.auto);
        btOCR.setOnClickListener(onClickListener);
        btalarm = (Button) findViewById(R.id.alarm);
        btalarm.setOnClickListener(onClickListener);
        btalarmL = (Button) findViewById(R.id.QRcode);
        btalarmL.setOnClickListener(onClickListener);
        btnotify = (Button) findViewById(R.id.Notification);
        btnotify.setOnClickListener(onClickListener);
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








    }



    @Override
            public void onClick (View v) {
              switch (v.getId()) {
                  case add:
                      add();
                      break;
                  case time:
                      new AlertDialog.Builder(AddByHand.this)

                              .setTitle("時間")
                              .setItems(R.array.item, new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {
                                      String[] Item = getResources().getStringArray(
                                              R.array.item
                                      );

                                      tvTime.setText(Item[which]);
                                  }
                              }).create().show();
                      break;
                  case befaf:
                      new AlertDialog.Builder(AddByHand.this)


                              .setTitle("飯前飯後")
                              .setItems(R.array.dialog_rise, new DialogInterface.OnClickListener() {


                                  @Override

                                  public void onClick(DialogInterface dialog, int which) {
                                      String[] AF = getResources().getStringArray(
                                              R.array.dialog_rise
                                      );
                                      afbf.setText(AF[which]);


                                  }
                              }).create().show();


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
    public void add() {
        String mname = name.getText().toString();
        String mmethod = method.getText().toString();
        String mtime = tvTime.getText().toString();
        int mday = Integer.parseInt(day.getText().toString());
        int mamount = Integer.parseInt(amount.getText().toString());
        ContentValues values = new ContentValues();
        values.put("name", mname);
        values.put("method", mmethod);
        values.put("amount", mamount);
        values.put("day", mday);
        values.put("tvTime", mtime);
        long id = helper.getWritableDatabase().insert("MEDINFO", null, values);
        Log.d("ADD", id + "" + mtime  +"");
        if(helper.isEmpty()){
            Cursor morn = helper.filList(1);
            Cursor noo = helper.filList(2);
            Cursor ni = helper.filList(3);
            Cursor mid = helper.filList(4);
            if(tvTime.getText().toString().equals("早上")){
                alarm(morn.getString(1));
            }
            else if(tvTime.getText().toString().equals("中午")){
                alarm(noo.getString(1));
            }
            else if(tvTime.getText().toString().equals("晚上")){
                alarm(ni.getString(1));
            }

            else if(tvTime.getText().toString().equals("睡前")){
                alarm(mid.getString(1));
            }

            else if(tvTime.getText().toString().equals("早上/晚上")){
                alarm(morn.getString(1));
                alarm(ni.getString(1));

            }

            else if(tvTime.getText().toString().equals("早上/中午/晚上")){
                alarm(morn.getString(1));
                alarm(noo.getString(1));
                alarm(ni.getString(1));
            }

            else if(tvTime.getText().toString().equals("早上/中午/晚上/睡前")){
                alarm(morn.getString(1));
                alarm(noo.getString(1));
                alarm(ni.getString(1));
                alarm(mid.getString(1));
            }
        }
        intent.setClass(AddByHand.this, Base.class);
        startActivity(intent);

    }


    private void findViews() {
        name = (EditText) findViewById(R.id.name);
        amount = (EditText) findViewById(R.id.amount);
        method = (EditText) findViewById(R.id.method);
        day = (EditText) findViewById(R.id.day);
        tvTime = (EditText)findViewById(R.id.time_eat);
        afbf = (EditText)findViewById(R.id.eat);
        name.setText("123");
        amount.setText("123");
        method.setText("123");
        day.setText("123");
        tvTime.setText("123");
        afbf.setText("123");
    }
    public void alarm (String alarmtimein) {


        SimpleDateFormat time = new SimpleDateFormat("yyyy MM dd HH:mm");
        SimpleDateFormat date = new SimpleDateFormat("yyyy MM dd");
        try {
            Intent intent11 = new Intent(getApplicationContext(), PlayReceiver.class);
            Date today  = new Date();
            String s = date.format(today);
            String times = s + " " + alarmtimein;
            Date alarmtimeout = time.parse(times);
            long milliseconds = alarmtimeout.getTime();




        intent11.putExtra("msg", "play_voice");
        final int _id = (int)System.currentTimeMillis();
        long elapsed =  milliseconds;
        PendingIntent pi = PendingIntent.getBroadcast(this, _id, intent11,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, elapsed, AlarmManager.INTERVAL_DAY, pi);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }





}
