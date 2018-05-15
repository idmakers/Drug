package com.example.lab714_pc.drug;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.lab714_pc.drug.R.id.add;
import static com.example.lab714_pc.drug.R.id.befaf;
import static com.example.lab714_pc.drug.R.id.del;
import static com.example.lab714_pc.drug.R.id.time;
import static com.example.lab714_pc.drug.R.id.updateL;

public class Update extends AddByHand
        implements View.OnClickListener{


    private EditText amount;
    private MyDBHelper helper;
    private EditText Mname;
    private EditText method ,afbf;
    private EditText day;
    private EditText tvTime;
    private Button btTime, update,btAf,deleteItem;
    private long name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        findViews();
        tvTime = (EditText)findViewById(R.id.time_eat);
        afbf = (EditText)findViewById(R.id.eat);
        btTime = (Button) findViewById(R.id.time);
        btTime.setOnClickListener(this);
        btAf = (Button) findViewById(R.id.befaf);
        btAf.setOnClickListener(this);
        update = (Button) findViewById(R.id.updateL);
        update.setOnClickListener(this);
        btadd = (Button) findViewById(R.id.addh);
        btadd.setOnClickListener(onClickListener);
        deleteItem = (Button) findViewById(R.id.del);
        deleteItem.setOnClickListener(this);
        btitem = (Button) findViewById(R.id.item);
        btitem.setOnClickListener(onClickListener);
        btalarm = (Button) findViewById(R.id.alarm);
        btalarm.setOnClickListener(onClickListener);
        btalarmL = (Button) findViewById(R.id.QRcode);
        btalarmL.setOnClickListener(onClickListener);
        btnotify = (Button) findViewById(R.id.Notification);
        btnotify.setOnClickListener(onClickListener);
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
        helper = new MyDBHelper(this,"expense.db",null,1);
        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("msg");
        Log.w("id", "long"+ id);
        name = extras.getLong("msg");

        if(helper.ItemisEmpty()){
            Cursor cursor = helper.ItemfilList(name);

//            Log.w("text", "setTect " + cursor.getString(1));
//            Log.w("text", "setTect " + cursor.getString(2));
//            Log.w("text", "setTect " + cursor.getString(3));
//            Log.w("text", "setTect " + cursor.getString(4));
//           // Log.w("text", "setTect " + cursor.getString());
            Mname.setText(cursor.getString(4));
            amount.setText(cursor.getString(5));
            tvTime.setText(cursor.getString(1));
            day.setText(cursor.getString(3));
            method.setText(cursor.getString(2));





        }




    }
    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case updateL:
                add();
                break;
            case del:
                Delete();
                Log.w("msg","del");
                break;

            case time:
                new AlertDialog.Builder(this)

                        .setTitle("時間")
                        .setItems(R.array.item, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] Item = getResources().getStringArray(
                                        R.array.item
                                );

                                tvTime.setText(Item[which]);
                            }
                        }).create().show();
                break;
            case befaf:
                new AlertDialog.Builder(this)


                        .setTitle("飯前飯後")
                        .setItems(R.array.dialog_rise, new DialogInterface.OnClickListener() {


                            @Override

                            public void onClick(DialogInterface dialog, int which) {
                                String[] AF = getResources().getStringArray(
                                        R.array.dialog_rise
                                );
                                afbf.setText(AF[which]);


                            }
                        }).create().show();


        }
    }
    public void add() {
        String mname = Mname.getText().toString();
        String mmethod = method.getText().toString();
        String mtime = tvTime.getText().toString();
        int mday = Integer.parseInt(day.getText().toString());
        String mamount = amount.getText().toString();
        ContentValues values = new ContentValues();
        values.put("name", mname);
        values.put("method", mmethod);
        values.put("amount", mamount);
        values.put("day", mday);
        values.put("tvTime", mtime);
        long id = helper.getWritableDatabase().insert("MEDINFO", null, values);
        Log.d("ADD", id + "" + mtime + "");
        if (helper.isEmpty()) {
            Cursor morn = helper.filList(1);
            Cursor noo = helper.filList(2);
            Cursor ni = helper.filList(3);
            Cursor mid = helper.filList(4);
            if (tvTime.getText().toString().equals("早上")) {
                alarm(morn.getString(1));
            } else if (tvTime.getText().toString().equals("中午")) {
                alarm(noo.getString(1));
            } else if (tvTime.getText().toString().equals("晚上")) {
                alarm(ni.getString(1));
            } else if (tvTime.getText().toString().equals("睡前")) {
                alarm(mid.getString(1));
            } else if (tvTime.getText().toString().equals("早上/晚上")) {
                alarm(morn.getString(1));
                alarm(ni.getString(1));

            } else if (tvTime.getText().toString().equals("早上/中午/晚上")) {
                alarm(morn.getString(1));
                alarm(noo.getString(1));
                alarm(ni.getString(1));
            } else if (tvTime.getText().toString().equals("早上/中午/晚上/睡前")) {
                alarm(morn.getString(1));
                alarm(noo.getString(1));
                alarm(ni.getString(1));
                alarm(mid.getString(1));
            }
        } else {
            id = helper.Itemupdate(name, values);
            intent.setClass(this, Base.class);
            startActivity(intent);

        }
    }
    public void Delete(){
        helper.getWritableDatabase();
        helper.ItemDel(name);
        intent.setClass(this, Base.class);
        startActivity(intent);
    }
    private void findViews() {
        Mname = (EditText) findViewById(R.id.name);
        amount = (EditText) findViewById(R.id.amount);
        method = (EditText) findViewById(R.id.method);
        day = (EditText) findViewById(R.id.day);
        afbf = (EditText)findViewById(R.id.eat);
        tvTime = (EditText)findViewById(R.id.time_eat);
        update = (Button)findViewById(R.id.updateL);
        deleteItem =(Button)findViewById(R.id.del);
    }



}
