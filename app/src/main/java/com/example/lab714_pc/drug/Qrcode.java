package com.example.lab714_pc.drug;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Qrcode extends Base{

    private Button btn_scan;
    private TextView txt_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);


        this.btn_scan = (Button)findViewById(R.id.scan);
        this.txt_url = (TextView) findViewById(R.id.txt_url);
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
        btnotify = (Button) findViewById(R.id.Notification);
        btnotify.setOnClickListener(onClickListener);



        //點擊Button用IntentIntegration的initiateScan方法開始掃描
        this.btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(Qrcode.this).initiateScan();
            }
        });
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


    //掃描完的結果回傳到Activity，要用onActivityResult這個方法
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(result!=null){
            String scanContent = result.getContents();
            String scanFormat = result.getFormatName();
            txt_url.setText(scanFormat+" \n"+scanContent); //將資料顯示到textView

        }else{
            Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_LONG).show();
        }

    }
    void covert(TextView textView){
        String txt = textView.toString();
        

    }
}