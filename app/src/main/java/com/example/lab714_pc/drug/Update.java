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

public class Update extends AppCompatActivity {

    private MyDBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("msg");
        Log.w("id", "long"+ id);
        long name = extras.getLong("msg");

        ContentValues cv =new ContentValues();
        cv.put("name","");
        cv.put("amount","");



    }


//    name
//    amount
//    method
//    day
//    tvTime
//    afbf


}
