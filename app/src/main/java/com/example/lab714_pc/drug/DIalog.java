package com.example.lab714_pc.drug;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by idmakers on 2018/6/3.
 */

public class DIalog  extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Intent intent00 = getIntent();
        LinearLayout linearLayoutMain = new LinearLayout(this);//自定义一个布局文件
        linearLayoutMain.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ListView listView = new ListView(this);//this为获取当前的上下文
        listView.setFadingEdgeLength(0);
        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
        Cursor c = helper.getReadableDatabase().rawQuery("SELECT * FROM  MEDINFO WHERE day = '' +0+ ''  ORDER BY name  " , null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.content_listname,
                c,
                new String[] {"name"},
                new int[] {R.id.name}, 0);
        //new String[] {"name", "amount"},
        //new int[] {android.R.id.text1, android.R.id.text2},0);

        listView.setAdapter(adapter);
        linearLayoutMain.addView(listView);//往这个布局中加入listview
        String msg = intent00.getStringExtra("message");
        if (msg.equals("Dialog1")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕1");
            dialog.setView(linearLayoutMain);
            Log.w("msg", "123 " );
            dialog.setMessage("基本訊息對話功能介紹");
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "我還尚未了解", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "我了解了", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "取消", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.show();
        }
        else if (msg.equals("Dialog2")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕2");
            dialog.setMessage("基本訊息對話功能介紹");
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "我還尚未了解", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "我了解了", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "取消", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.show();
        }
        else if (msg.equals("Dialog3")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕3");
            dialog.setMessage("基本訊息對話功能介紹");
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "我還尚未了解", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "我了解了", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "取消", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.show();
        }
        else if (msg.equals("Dialog4")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕4");
            dialog.setMessage("基本訊息對話功能介紹");
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "我還尚未了解", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "我了解了", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "取消", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.show();
        }
    }
}
