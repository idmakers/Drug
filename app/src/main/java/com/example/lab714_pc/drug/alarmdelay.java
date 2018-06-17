package com.example.lab714_pc.drug;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public  class alarmdelay extends AppCompatActivity {

    public PendingIntent pi;
    public  Intent intent11;
    public String name;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("msg");
        Log.w("msg",name);
        if(name =="susepend"){
            alarmCancel(1);
        }

    }


    public   void alarmCancel(int id){
        intent11 = new Intent(getApplicationContext(), PlayReceiver.class);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        pi = PendingIntent.getBroadcast(this, id, intent11,PendingIntent.FLAG_UPDATE_CURRENT);


        // Cancel alarms
        try {
            am.setExact(AlarmManager.RTC_WAKEUP, 30000, pi);
        } catch (Exception e) {
            Log.w("MSG", "AlarmManager update was not canceled. " + e.toString());
        }
    }
}
