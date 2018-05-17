package com.example.lab714_pc.drug;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;

import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.app.Notification;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RemoteViews;

public class Base extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static Toolbar toolbar;
    public Button btadd, btitem, btOCR, btalarm,btalarmL,btnotify,close, history;
    private Context context = this;
    static NotificationManager notificationManger;
    static Notification notification;
    private static final int NOTIFICATION_ID = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        btadd = (Button) findViewById(R.id.addh);
        btadd.setOnClickListener(onClickListener);
        btitem = (Button) findViewById(R.id.item);
        btitem.setOnClickListener(onClickListener);
        btalarm = (Button) findViewById(R.id.alarm);
        btalarm.setOnClickListener(onClickListener);
        btalarmL = (Button) findViewById(R.id.QRcode);
        btalarmL.setOnClickListener(onClickListener);
        btnotify = (Button) findViewById(R.id.Notification);
        btnotify.setOnClickListener(onClickListener);
        history = (Button) findViewById(R.id.history);
        history.setOnClickListener(onClickListener);
        close = (Button) findViewById(R.id.close);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        Intent updateServiceIntent = new Intent(this, PlayReceiver.class);
        PendingIntent pendingUpdateIntent = PendingIntent.getService(context, 0, updateServiceIntent, 0);

        // Cancel alarms
        try {
            alarmManager.cancel(pendingUpdateIntent);
        } catch (Exception e) {
            Log.e("", "AlarmManager update was not canceled. " + e.toString());
        }
        notificationManger = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.activity_notification);
        contentView.setTextViewText(R.id.title, "Custom notification");
        contentView.setTextViewText(R.id.text, "This is a custom layout");
        contentView.setOnClickPendingIntent(R.id.close,getPendingSelfIntent(context,"close"));
     if(Build.VERSION.SDK_INT==Build.VERSION_CODES.N){
             notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_focused)
                .setCustomBigContentView(contentView)
                .build(); // available from API level 11 and onwards
        notification.flags = Notification.FLAG_AUTO_CANCEL;

    }
    else  if(Build.VERSION.SDK_INT==Build.VERSION_CODES.M){
             notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_focused)
                .build(); // available from API level 11 and onwards
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.bigContentView = contentView;

    }




        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;

        notification.flags |= Notification.FLAG_NO_CLEAR; //Do not clear the notification
        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
        notification.defaults |= Notification.DEFAULT_SOUND; // Sound
        notification.flags = Notification.FLAG_AUTO_CANCEL;


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
    View.OnClickListener onClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.addh:
                    Intent intent4 = new Intent();
                    intent4.setClass(context, AddByHand.class);
                    startActivity(intent4);
                    onBackPressed();
                    break;
                case R.id.item:
                    Intent intenti = new Intent();
                    intenti.setClass(context, ItemListView.class);
                    startActivity(intenti);
                    onBackPressed();
                    break;
                case R.id.Notification:
                    notificationManger.notify(0, notification);
                    break;
                case R.id.alarm:
                    Intent intent1 = new Intent();
                    intent1.setClass(context, AlarmTime.class);
                    startActivity(intent1);
                    onBackPressed();
                    break;
                case R.id.QRcode:
                    Intent intentQR = new Intent();
                    intentQR.setClass(context, Qrcode.class);
                    startActivity(intentQR);
                    onBackPressed();
                    break;
                case R.id.history:
                    Intent intentH = new Intent();
                    intentH.setClass(context, History.class);
                    startActivity(intentH);
                    onBackPressed();
                    break;
          /*     case R.id.AlarmRing :
                   Intent intent11 = new Intent(Main2Activity.context, PlayReceiver.class);
                   intent11.putExtra("msg", "play_voice");
                   intent11.addCategory(String.valueOf(SystemClock.elapsedRealtime()));
                   //SystemClock.elapsedRealtime()會回傳從開機到現在當下所花的時間,手機進入睡眠時間也算在內(單位milliseconds)
                   long elapsed = SystemClock.elapsedRealtime() + 60 * 1000; //60秒
                   // 發送一個broadcast,類似 Context.sendBroadcast()
                   // PendingIntent.FLAG_UPDATE_CURRENT參數表示,如果已存在 PendingIntent,就更新 extra data.
                   PendingIntent pi = PendingIntent.getBroadcast(Main2Activity.context, 1, intent11,
                           PendingIntent.FLAG_UPDATE_CURRENT);
                   AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                   am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, elapsed , pi);
                   break;

   */
            }


        }
    };
    private void setUpToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
    }
    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, PlayReceiver.class);
        intent.putExtra("msg",action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.home){
            drawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
