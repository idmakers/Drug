package com.example.lab714_pc.drug;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.EditText;

public class Update extends AppCompatActivity {


    private EditText amount;
    private MyDBHelper helper;
    private EditText Mname;
    private EditText method;
    private EditText day;
    private EditText tvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        helper = new MyDBHelper(this,"expense.db",null,1);
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("msg");
        Log.w("id", "long"+ id);
        long name = extras.getLong("msg");

        if(helper.ItemisEmpty()){
            Cursor cursor = helper.filList(name);

            Mname.setText(cursor.getString(1));
            amount.setText(cursor.getString(3));
            tvTime.setText(cursor.getString(5));
            day.setText(cursor.getString(4));
            method.setText(cursor.getString(2));





        }
//        ContentValues cv =new ContentValues();
//        cv.put("name","");
//        cv.put("amount","");



    }

    private void findViews() {
        Mname = (EditText) findViewById(R.id.name);
        amount = (EditText) findViewById(R.id.amount);
        method = (EditText) findViewById(R.id.method);
        day = (EditText) findViewById(R.id.day);
        tvTime = (EditText)findViewById(R.id.time_eat);
    }

}
