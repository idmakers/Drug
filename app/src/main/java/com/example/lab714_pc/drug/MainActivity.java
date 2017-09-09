package com.example.lab714_pc.drug;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button btadd ,btitem,btOCR;
    private Context context  = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btadd = (Button) findViewById(R.id.addh);
        btadd.setOnClickListener(this);
        btitem =(Button)findViewById(R.id.item);
        btitem.setOnClickListener(this);
        btOCR =(Button)findViewById(R.id.auto);
        btOCR.setOnClickListener(this);

    }

        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.addh:
                    Intent intent = new Intent();
                    intent.setClass(this, AddByHand.class);
                    startActivity(intent);
                    break;
                case R.id.item:
                    Intent intenti = new Intent();
                    intenti.setClass(this, ItemListView.class);
                    startActivity(intenti);
                    break;
                case R.id.auto:
                    Intent intentO = new Intent();
                    intentO.setClass(this, OCR.class);
                    startActivity(intentO);
                    break;


            }



        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
     //鬧鐘










}
