package com.example.lab714_pc.drug;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Dialog;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static android.media.RingtoneManager.TYPE_ALARM;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button btadd, btitem, btOCR, btalarm;
    private Context context = this;
    private static TextView textView2;

    public static TextView getTextView2() {
        return textView2;
    }

    AlarmManager am;
    PendingIntent pi;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btadd = (Button) findViewById(R.id.addh);
        btadd.setOnClickListener(this);
        btitem = (Button) findViewById(R.id.item);
        btitem.setOnClickListener(this);
        btOCR = (Button) findViewById(R.id.auto);
        btOCR.setOnClickListener(this);
        btalarm = (Button) findViewById(R.id.alarm);
        btalarm.setOnClickListener(this);

/*
        int n =30;
        for(int i=0; i<n;i++){
            cal = Calendar.getInstance();
            cal.set(2017,10,31,10,10,0);

            Intent intent = new Intent(this, AlarmTime.class);

            am =(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

            pi = PendingIntent.getBroadcast(this, i , intent , PendingIntent.FLAG_ONE_SHOT);

            am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pi);

        }

*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addh:
                Intent intent4 = new Intent();
                intent4.setClass(this, AddByHand.class);
                startActivity(intent4);
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
            case R.id.alarm:
                Intent intent1 = new Intent();
                intent1.setClass(this, AlarmTime.class);
                startActivity(intent1);
                break;

          /*
                Intent intent11 = new Intent(MainActivity.this, PlayReceiver.class);
                intent11.putExtra("msg", "play_voice");
                intent11.addCategory(String.valueOf(SystemClock.elapsedRealtime()));
                //SystemClock.elapsedRealtime()會回傳從開機到現在當下所花的時間,手機進入睡眠時間也算在內(單位milliseconds)
                long elapsed = SystemClock.elapsedRealtime() + 60 * 1000; //60秒
                // 發送一個broadcast,類似 Context.sendBroadcast()
                // PendingIntent.FLAG_UPDATE_CURRENT參數表示,如果已存在 PendingIntent,就更新 extra data.
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 1, intent11,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, elapsed , pi);

*/

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

