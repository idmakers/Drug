package com.example.lab714_pc.drug;

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
import static com.example.lab714_pc.drug.R.id.item;
import static com.example.lab714_pc.drug.R.id.time;
import static com.example.lab714_pc.drug.R.id.time_eat;


public class AddByHand extends MainActivity {

    private EditText amount;
    private MyDBHelper helper;
    private EditText name;
    private EditText method;
    private EditText day;
    private TextView displayedText;
    Button btn, btn1;
    private EditText tvTime;
    private Button btTime, btAdd;
    private int mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbyhand);
        findViews();
        helper = new MyDBHelper(this,"expense.db",null,1);
        tvTime = (EditText)findViewById(R.id.time_eat);
        btTime = (Button) findViewById(R.id.time);
        btTime.setOnClickListener(this);
        btAdd = (Button) findViewById(R.id.add);
        btAdd.setOnClickListener(this);





    }
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

       /*     @Override
            public void onClick (View v) {
                switch (v.getId()) {
                    case add:add();
                             break;
                    case time:
                         new AlertDialog.Builder(AddByHand.this)

                                .setTitle("時間")
                                .setItems(R.array.item, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String[] Item=getResources().getStringArray(
                                                R.array.item
                                        );
                                        Toast.makeText(AddByHand.this,""+which+","+
                                                Item[which],Toast.LENGTH_LONG).show();
                                    }
                                }).create().show();
                                break;
                    case befaf:
                        setContentView(R.layout.activity_addbyhand);
                        new AlertDialog.Builder(AddByHand.this)


                                .setTitle("飯前飯後")
                                .setItems(R.array.dialog_rise, new DialogInterface.OnClickListener() {


                                    @Override

                                    public void onClick(DialogInterface dialog, int which) {
                                        String[] Item=getResources().getStringArray(
                                                R.array.dialog_rise

                                        );

                                    }
                                }).create().show();




                }
            }*/

    //輸入時間

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

    private void add() {
        Intent intent = new Intent();
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
        long id = helper.getWritableDatabase().insert("exp", null, values);
        Log.d("ADD", id + "" + mtime  +"");
        intent.setClass(AddByHand.this, MainActivity.class);
        startActivity(intent);
    }


    private void findViews() {
        name = (EditText) findViewById(R.id.name);
        amount = (EditText) findViewById(R.id.amount);
        method = (EditText) findViewById(R.id.method);
        day = (EditText) findViewById(R.id.day);
        tvTime = (EditText)findViewById(R.id.time_eat);
    }


}

