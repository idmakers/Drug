package com.example.lab714_pc.drug;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ItemListView extends Base implements View.OnClickListener {

    private Button btadd, btitem, btOCR, btalarm,btalarmL,btring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlistview);
        btadd = (Button) findViewById(R.id.addh);
        btadd.setOnClickListener(onClickListener);
        btitem = (Button) findViewById(R.id.item);
        btitem.setOnClickListener(onClickListener);
        btOCR = (Button) findViewById(R.id.auto);
        btOCR.setOnClickListener(onClickListener);
        btalarm = (Button) findViewById(R.id.alarm);
        btalarm.setOnClickListener(onClickListener);
        btalarmL = (Button) findViewById(R.id.QRcode);
        btalarmL.setOnClickListener(onClickListener);
        ListView list = (ListView) findViewById(R.id.list);
        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
        Cursor c = helper.getReadableDatabase().query("MEDINFO", null, null, null, null, null, null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.content_itemlistview,
                c,
                new String[] {"_id", "amount","day","method"},
                new int[] {R.id.item_id, R.id.item_amount, R.id.item_info, R.id.item_cdate}, 0);
        //new String[] {"name", "amount"},
        //new int[] {android.R.id.text1, android.R.id.text2},0);

        list.setAdapter(adapter);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.addh:
                Intent intent4 = new Intent();
                intent4.setClass(this, AddByHand.class);
                startActivity(intent4);
                onBackPressed();
                break;
            case R.id.item:
                Intent intenti = new Intent();
                intenti.setClass(this, ItemListView.class);
                startActivity(intenti);
                onBackPressed();
                break;
            case R.id.auto:
                Intent intentO = new Intent();
                intentO.setClass(this, OCR.class);
                startActivity(intentO);
                onBackPressed();
                break;
            case R.id.alarm:
                Intent intent1 = new Intent();
                intent1.setClass(this, AlarmTime.class);
                startActivity(intent1);
                onBackPressed();
                break;
//                  case R.id.QRcode:
//                 Intent intent = new Intent("com.google.zxing.client.android.SCAN");	//開啟條碼掃描器
//                   intent.putExtra("SCAN_MODE", "QR_CODE_MODE");	//設定QR Code參數
//                   startActivityForResult(intent, 1);	//要求回傳1
//                   break;
//
//              case R.id.AlarmRing :
//                   Intent intent11 = new Intent(Main2Activity.context, PlayReceiver.class);
//                   intent11.putExtra("msg", "play_voice");
//                   intent11.addCategory(String.valueOf(SystemClock.elapsedRealtime()));
//                   //SystemClock.elapsedRealtime()會回傳從開機到現在當下所花的時間,手機進入睡眠時間也算在內(單位milliseconds)
//                   long elapsed = SystemClock.elapsedRealtime() + 60 * 1000; //60秒
//                   // 發送一個broadcast,類似 Context.sendBroadcast()
//                   // PendingIntent.FLAG_UPDATE_CURRENT參數表示,如果已存在 PendingIntent,就更新 extra data.
//                   PendingIntent pi = PendingIntent.getBroadcast(Main2Activity.context, 1, intent11,
//                           PendingIntent.FLAG_UPDATE_CURRENT);
//                   AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
//                   am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, elapsed , pi);
//                   break;
        }
    }


}
