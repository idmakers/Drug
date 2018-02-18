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

public class MainActivity extends Base  {

    private Button btadd, btitem, btOCR, btalarm,btalarmL,btring;
    private Context context = this;
    //private  TextView txt_hello = (TextView)findViewById(R.id.textView2);


    AlarmManager am;
    PendingIntent pi;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
      /* btadd = (Button) findViewById(R.id.addh);
        btadd.setOnClickListener(this);
        btitem = (Button) findViewById(R.id.item);
        btitem.setOnClickListener(this);
        btOCR = (Button) findViewById(R.id.auto);
        btOCR.setOnClickListener(this);
        btalarm = (Button) findViewById(R.id.alarm);
        btalarm.setOnClickListener(this);
        btalarmL = (Button) findViewById(R.id.QRcode);
        btalarmL.setOnClickListener(this);
        btring = (Button) findViewById(R.id.AlarmRing );
        btring.setOnClickListener(this);


        int n =30;
        for(int i=0; i<n;i++){
            cal = Calendar.getInstance();
            cal.set(2017,10,31,10,10,0);

            Intent intent = new Intent(this, AlarmTime.class);

            am =(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);

            pi = PendingIntent.getBroadcast(this, i , intent , PendingIntent.FLAG_ONE_SHOT);

            am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pi);
            */

        }




    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {	//startActivityForResult回傳值
            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");	//取得QR Code內容
                txt_hello.setText(contents);
            }
        }
    }*/

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

