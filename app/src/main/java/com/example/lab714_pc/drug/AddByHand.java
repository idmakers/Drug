package com.example.lab714_pc.drug;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;



public class AddByHand extends MainActivity  {

    private EditText amount;
    private MyDBHelper helper;
    private EditText name;
    private  EditText method;
    private  EditText day;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbyhand);
        findViews();

        helper = new MyDBHelper(this,"expense.db",null,1);
        Button btOK = (Button) findViewById(R.id.add);
        btOK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                add();
            }

            private void add() {
                Intent intent = new Intent();
                String mname = name.getText().toString();
                String mmethod = method.getText().toString();
                int mday = Integer.parseInt(day.getText().toString());
                int mamount = Integer.parseInt(amount.getText().toString());
                ContentValues values = new ContentValues();
                values.put("name",mname);
                values.put("method",mmethod);
                values.put("amount",mamount);
                values.put("day",mday);
                long id = helper.getWritableDatabase().insert("exp", null, values);
                Log.d("ADD", id+"");
                intent.setClass(AddByHand.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
    private void findViews(){
        name = (EditText)findViewById(R.id.name);
        amount = (EditText)findViewById(R.id.amount);
        method = (EditText)findViewById(R.id.method);
        day =(EditText)findViewById(R.id.day);
    }



}
