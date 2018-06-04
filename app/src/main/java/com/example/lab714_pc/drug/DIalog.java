package com.example.lab714_pc.drug;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by idmakers on 2018/6/3.
 */

public class DIalog  extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Intent intent00 = getIntent();
        String msg = intent00.getStringExtra("message");
        if (msg.equals("Dialog1")) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕1");
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
