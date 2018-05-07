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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Update extends AddByHand
        implements View.OnClickListener{


    private EditText amount;
    private MyDBHelper helper;
    private EditText Mname;
    private EditText method;
    private EditText day;
    private EditText tvTime;
    private Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        helper = new MyDBHelper(this,"expense.db",null,1);
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("msg");
        Log.w("id", "long"+ id);
        long name = extras.getLong("msg");
        findViews();
        if(helper.ItemisEmpty()){
            Cursor cursor = helper.ItemfilList(name);

//            Log.w("text", "setTect " + cursor.getString(1));
//            Log.w("text", "setTect " + cursor.getString(2));
//            Log.w("text", "setTect " + cursor.getString(3));
//            Log.w("text", "setTect " + cursor.getString(4));
//           // Log.w("text", "setTect " + cursor.getString());
            Mname.setText(cursor.getString(4));
            amount.setText(cursor.getString(5));
            tvTime.setText(cursor.getString(1));
            day.setText(cursor.getString(3));
            method.setText(cursor.getString(2));





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

        }
    }

    private void findViews() {
        Mname = (EditText) findViewById(R.id.name);
        amount = (EditText) findViewById(R.id.amount);
        method = (EditText) findViewById(R.id.method);
        day = (EditText) findViewById(R.id.day);
        tvTime = (EditText)findViewById(R.id.time_eat);
        update = (Button)findViewById(R.id.updateL);
    }



}
