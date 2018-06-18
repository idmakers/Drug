package com.example.lab714_pc.drug;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by idmakers on 2018/6/3.
 */

public class DIalog  extends AppCompatActivity {
    MediaPlayer mPlayer = new MediaPlayer();
    public PendingIntent pi;
    public  Intent intent11;
    Intent intent = new Intent();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Intent intent00 = getIntent();
        mPlayer = MediaPlayer.create(this, R.raw.test); // in 2nd param u have to pass your desire ringtone
        //mPlayer.prepare();
        //mPlayer.start();
        long itemid;
        LinearLayout linearLayoutMain = new LinearLayout(this);//自定义一个布局文件
        linearLayoutMain.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ListView listView = new ListView(this);//this为获取当前的上下文
        listView.setFadingEdgeLength(0);
//        ListView.LayoutParams lp = (ListView.LayoutParams) listView.getLayoutParams();
//        lp.width = 300;
//        ListView.setLayoutParams(lp);

        String msg = intent00.getStringExtra("message");
        if (msg.equals("Dialog1")) {

            final MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
            final Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM  MEDINFO WHERE tvTime ='早' and stop = '1' and  bf='前' ORDER BY name  ", null);
//            final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                    R.layout.content_listname,
//                    c,
//                    new String[]{"name"},
//                    new int[]{R.id.name}, 0);
            final ArrayList<String> mArrayList = new ArrayList<String>();
            if (cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    for(int i = 0; i < cursor.getCount(); i +=cursor.getCount()){
                        mArrayList.add(cursor.getString(i+1));
                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
            final CharSequence charSequence [] = new CharSequence[mArrayList.size()];
            for(int i = 0, size = mArrayList.size(); i < size; i++){
                charSequence[i] = String.valueOf(mArrayList.get(i));
            }



            //new String[] {"name", "amount"},
            //new int[] {android.R.id.text1, android.R.id.text2},0);


//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position,
//                                        long id) {
//                    CheckBox  itemCheckbox = (CheckBox)findViewById(R.id.check);
//                    itemCheckbox.setOnCheckedChangeListener();
//
//                }
//            });


            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕1");

            Log.w("msg", "123 ");


            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "未服用", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setMultiChoiceItems(charSequence,null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if(isChecked) {
                        Log.w("msg",""+charSequence[which]);
                        Cursor cursor =helper.ItemfilListName(""+charSequence[which]);
                        long id = cursor.getLong(0);
                        Log.w("msg",""+id);
                        int amount = cursor.getInt(5);
                        ContentValues values = new ContentValues();
                        values.put("amount", amount-1);
                        helper.getWritableDatabase();
                        helper.Itemupdate(id, values);
                        Toast.makeText(DIalog.this,""+cursor.getInt(5),Toast.LENGTH_LONG).show();
                        cursor.close();
                    }
                    else{
                        Toast.makeText(DIalog.this,"unchecked"+charSequence[which],Toast.LENGTH_LONG).show();
                    }

                }
            });


            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    mPlayer.stop();
                    mPlayer.release();
                    Toast.makeText(DIalog.this, "已服用", Toast.LENGTH_SHORT).show();
                    intent.setClass(DIalog.this, Base.class);
                    startActivity(intent);
                }

            });
            dialog.setNeutralButton("延後提醒", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "提醒服務已延後", Toast.LENGTH_SHORT).show();
                    alarmCancel(1, 1);

                }

            });
            dialog.show();
        }
            else if (msg.equals("Dialog2")) {
             final   MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
             final   Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM  MEDINFO WHERE tvTime ='中' and stop='1'  and bf='前'ORDER BY name  ", null);
//                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                        R.layout.content_listname,
//                        c,
//                        new String[]{"name"},
//                        new int[]{R.id.name}, 0);
                //new String[] {"name", "amount"},
                //new int[] {android.R.id.text1, android.R.id.text2},0);

            final ArrayList<String> mArrayList = new ArrayList<String>();
            if (cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    for(int i = 0; i < cursor.getCount(); i +=cursor.getCount()){
                        mArrayList.add(cursor.getString(i+1));
                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
            final CharSequence charSequence [] = new CharSequence[mArrayList.size()];
            for(int i = 0, size = mArrayList.size(); i < size; i++){
                charSequence[i] = String.valueOf(mArrayList.get(i));
            }




            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕2");
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "未服用", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setMultiChoiceItems(charSequence,null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if(isChecked) {
                        Log.w("msg",""+charSequence[which]);
                        Cursor cursor =helper.ItemfilListName(""+charSequence[which]);
                        long id = cursor.getLong(0);
                        Log.w("msg",""+id);
                        int amount = cursor.getInt(5);
                        ContentValues values = new ContentValues();
                        values.put("amount", amount-1);
                        helper.getWritableDatabase();
                        helper.Itemupdate(id, values);
                        Toast.makeText(DIalog.this,""+cursor.getInt(5),Toast.LENGTH_LONG).show();
                        cursor.close();
                    }
                    else{
                        Toast.makeText(DIalog.this,"unchecked"+charSequence[which],Toast.LENGTH_LONG).show();
                    }

                }
            });
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    mPlayer.stop();
                    mPlayer.release();
                    Toast.makeText(DIalog.this, "已服用", Toast.LENGTH_SHORT).show();
                    intent.setClass(DIalog.this, Base.class);
                    startActivity(intent);
                }

            });
                dialog.setNeutralButton("延後提醒", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(DIalog.this, "提醒服務已延後", Toast.LENGTH_SHORT).show();
                        alarmCancel(2, 1);

                    }

                });
                dialog.show();
            }
         else if (msg.equals("Dialog3")) {
            final MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
            final Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM  MEDINFO WHERE tvTime ='晚' and stop='1' and bf='前'  ORDER BY name  ", null);
            final ArrayList<String> mArrayList = new ArrayList<String>();
            if (cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    for(int i = 0; i < cursor.getCount(); i +=cursor.getCount()){
                        mArrayList.add(cursor.getString(i+1));
                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
            final CharSequence charSequence [] = new CharSequence[mArrayList.size()];
            for(int i = 0, size = mArrayList.size(); i < size; i++){
                charSequence[i] = String.valueOf(mArrayList.get(i));
            }


            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕3");
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "未服用", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setMultiChoiceItems(charSequence,null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            if (isChecked) {
                                Log.w("msg", "" + charSequence[which]);
                                Cursor cursor = helper.ItemfilListName("" + charSequence[which]);
                                long id = cursor.getLong(0);
                                Log.w("msg", "" + id);
                                int amount = cursor.getInt(5);
                                ContentValues values = new ContentValues();
                                values.put("amount", amount - 1);
                                helper.getWritableDatabase();
                                helper.Itemupdate(id, values);
                                Toast.makeText(DIalog.this, "" + cursor.getInt(5), Toast.LENGTH_LONG).show();
                                cursor.close();
                            } else {
                                Toast.makeText(DIalog.this, "unchecked" + charSequence[which], Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    mPlayer.stop();
                    mPlayer.release();
                    Toast.makeText(DIalog.this, "已服用", Toast.LENGTH_SHORT).show();
                    intent.setClass(DIalog.this, Base.class);
                    startActivity(intent);
                }

            });
            dialog.setNeutralButton("延後提醒", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "提醒服務已延後", Toast.LENGTH_SHORT).show();
                    alarmCancel(3,1);

                }

            });
            dialog.show();
        } else if (msg.equals("Dialog4")) {
           final MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
           final Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM  MEDINFO WHERE tvTime ='睡' and stop='1' ORDER BY name  ", null);

            final ArrayList<String> mArrayList = new ArrayList<String>();
            //new int[] {android.R.id.text1, android.R.id.text2},0);
            if (cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    for(int i = 0; i < cursor.getCount(); i +=cursor.getCount()){
                        mArrayList.add(cursor.getString(i+1));
                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
            final CharSequence charSequence [] = new CharSequence[mArrayList.size()];
            for(int i = 0, size = mArrayList.size(); i < size; i++){
                charSequence[i] = String.valueOf(mArrayList.get(i));
            }


            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕4");
            dialog.setMultiChoiceItems(charSequence,null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if(isChecked) {
                        Log.w("msg",""+charSequence[which]);
                        Cursor cursor =helper.ItemfilListName(""+charSequence[which]);
                        long id = cursor.getLong(0);
                        Log.w("msg",""+id);
                        int amount = cursor.getInt(5);
                        ContentValues values = new ContentValues();
                        values.put("amount", amount-1);
                        helper.getWritableDatabase();
                        helper.Itemupdate(id, values);
                        Toast.makeText(DIalog.this,""+cursor.getInt(5),Toast.LENGTH_LONG).show();
                        cursor.close();
                    }
                    else{
                        Toast.makeText(DIalog.this,"unchecked"+charSequence[which],Toast.LENGTH_LONG).show();
                    }

                }
            });


            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    mPlayer.stop();
                    mPlayer.release();
                    Toast.makeText(DIalog.this, "已服用", Toast.LENGTH_SHORT).show();
                    intent.setClass(DIalog.this, Base.class);
                    startActivity(intent);
                }

            });
            dialog.setNeutralButton("延後提醒", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "提醒服務已延後", Toast.LENGTH_SHORT).show();
                    alarmCancel(4,1);

                }

            });
            dialog.show();
        }
        else if (msg.equals("Dialog5")) {

            final MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
            Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM  MEDINFO WHERE tvTime ='早' and stop = '1' and  bf='後' ORDER BY name  ", null);

            //new int[] {android.R.id.text1, android.R.id.text2},0);


//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position,
//                                        long id) {
//                    CheckBox  itemCheckbox = (CheckBox)findViewById(R.id.check);
//                    itemCheckbox.setOnCheckedChangeListener();
//
//                }
//            });


            final ArrayList<String> mArrayList = new ArrayList<String>();
            if (cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    for(int i = 0; i < cursor.getCount(); i +=cursor.getCount()){
                        mArrayList.add(cursor.getString(i+1));
                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
            final CharSequence charSequence [] = new CharSequence[mArrayList.size()];
            for(int i = 0, size = mArrayList.size(); i < size; i++){
                charSequence[i] = String.valueOf(mArrayList.get(i));
            }

            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕5");
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "未服用", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setMultiChoiceItems(charSequence,null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if(isChecked) {
                        Log.w("msg",""+charSequence[which]);
                        Cursor cursor =helper.ItemfilListName(""+charSequence[which]);
                        long id = cursor.getLong(0);
                        Log.w("msg",""+id);
                        int amount = cursor.getInt(5);
                        ContentValues values = new ContentValues();
                        values.put("amount", amount-1);
                        helper.getWritableDatabase();
                        helper.Itemupdate(id, values);
                        Toast.makeText(DIalog.this,""+cursor.getInt(5),Toast.LENGTH_LONG).show();
                        cursor.close();
                    }
                    else{
                        Toast.makeText(DIalog.this,"unchecked"+charSequence[which],Toast.LENGTH_LONG).show();
                    }

                }
            });


            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    mPlayer.stop();
                    mPlayer.release();
                    Toast.makeText(DIalog.this, "已服用", Toast.LENGTH_SHORT).show();
                    intent.setClass(DIalog.this, Base.class);
                    startActivity(intent);
                }

            });
            dialog.setNeutralButton("延後提醒", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "提醒服務已延後", Toast.LENGTH_SHORT).show();
                    alarmCancel(1,2);

                }

            });
            dialog.show();
        }
        else if (msg.equals("Dialog6")) {

            final MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
            Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM  MEDINFO WHERE tvTime ='' and stop = '1' and  bf='後' ORDER BY name  ", null);


            //new String[] {"name", "amount"},       new int[]{R.id.name}, 0);
            //new int[] {android.R.id.text1, android.R.id.text2},0);


//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position,
//                                        long id) {
//                    CheckBox  itemCheckbox = (CheckBox)findViewById(R.id.check);
//                    itemCheckbox.setOnCheckedChangeListener();
//
//                }
//            });

            final ArrayList<String> mArrayList = new ArrayList<String>();
            if (cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    for(int i = 0; i < cursor.getCount(); i +=cursor.getCount()){
                        mArrayList.add(cursor.getString(i+1));
                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
            final CharSequence charSequence [] = new CharSequence[mArrayList.size()];
            for(int i = 0, size = mArrayList.size(); i < size; i++){
                charSequence[i] = String.valueOf(mArrayList.get(i));
            }


            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕6");
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "未服用", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setMultiChoiceItems(charSequence,null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if(isChecked) {
                        Log.w("msg",""+charSequence[which]);
                        Cursor cursor =helper.ItemfilListName(""+charSequence[which]);
                        long id = cursor.getLong(0);
                        Log.w("msg",""+id);
                        int amount = cursor.getInt(5);
                        ContentValues values = new ContentValues();
                        values.put("amount", amount-1);
                        helper.getWritableDatabase();
                        helper.Itemupdate(id, values);
                        Toast.makeText(DIalog.this,""+cursor.getInt(5),Toast.LENGTH_LONG).show();
                        cursor.close();
                    }
                    else{
                        Toast.makeText(DIalog.this,"unchecked"+charSequence[which],Toast.LENGTH_LONG).show();
                    }

                }
            });


            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    mPlayer.stop();
                    mPlayer.release();
                    Toast.makeText(DIalog.this, "已服用", Toast.LENGTH_SHORT).show();
                    intent.setClass(DIalog.this, Base.class);
                    startActivity(intent);
                }

            });
            dialog.setNeutralButton("延後提醒", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "提醒服務已延後", Toast.LENGTH_SHORT).show();
                    alarmCancel(2,2);

                }

            });
            dialog.show();
        }
        else if (msg.equals("Dialog7")) {

            final MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
            Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM  MEDINFO WHERE tvTime ='晚' and stop = '1' and  bf='後' ORDER BY name  ", null);

//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position,
//                                        long id) {
//                    CheckBox  itemCheckbox = (CheckBox)findViewById(R.id.check);
//                    itemCheckbox.setOnCheckedChangeListener();
//
//                }
//            });
            final ArrayList<String> mArrayList = new ArrayList<String>();
            if (cursor != null && cursor.getCount()>0){
                cursor.moveToFirst();
                do{
                    for(int i = 0; i < cursor.getCount(); i +=cursor.getCount()){
                        mArrayList.add(cursor.getString(i+1));
                    }
                }while(cursor.moveToNext());
            }
            cursor.close();
            final CharSequence charSequence [] = new CharSequence[mArrayList.size()];
            for(int i = 0, size = mArrayList.size(); i < size; i++){
                charSequence[i] = String.valueOf(mArrayList.get(i));
            }



            AlertDialog.Builder dialog = new AlertDialog.Builder(DIalog.this);
            dialog.setTitle("基本訊息對話按鈕7");
            dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "未服用", Toast.LENGTH_SHORT).show();
                }

            });
            dialog.setMultiChoiceItems(charSequence,null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if(isChecked) {
                        Log.w("msg",""+charSequence[which]);
                        Cursor cursor =helper.ItemfilListName(""+charSequence[which]);
                        long id = cursor.getLong(0);
                        Log.w("msg",""+id);
                        int amount = cursor.getInt(5);
                        ContentValues values = new ContentValues();
                        values.put("amount", amount-1);
                        helper.getWritableDatabase();
                        helper.Itemupdate(id, values);
                        Toast.makeText(DIalog.this,""+cursor.getInt(5),Toast.LENGTH_LONG).show();
                        cursor.close();
                    }
                    else{
                        Toast.makeText(DIalog.this,"unchecked"+charSequence[which],Toast.LENGTH_LONG).show();
                    }

                }
            });


            dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    mPlayer.stop();
                    mPlayer.release();
                    Toast.makeText(DIalog.this, "已服用", Toast.LENGTH_SHORT).show();
                    intent.setClass(DIalog.this, Base.class);
                    startActivity(intent);
                }

            });
            dialog.setNeutralButton("延後提醒", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    Toast.makeText(DIalog.this, "提醒服務已延後", Toast.LENGTH_SHORT).show();
                    alarmCancel(3,2);

                }

            });
            dialog.show();
        }

    }
    public   void alarmCancel(int id,int tag){
        intent11 = new Intent(getApplicationContext(), PlayReceiver.class);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        intent11.putExtra("msg", "play_voice"+id+tag);
        pi = PendingIntent.getBroadcast(this, id+tag, intent11,PendingIntent.FLAG_UPDATE_CURRENT);

        // Cancel alarms
        try {
            am.setExact(AlarmManager.RTC_WAKEUP, 30000, pi);
        } catch (Exception e) {
            Log.w("MSG", "AlarmManager update was not canceled. " + e.toString());
        }
    }


}
