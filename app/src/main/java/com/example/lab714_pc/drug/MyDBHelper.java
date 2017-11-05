package com.example.lab714_pc.drug;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHelper extends SQLiteOpenHelper  {

    public MyDBHelper(Context context, String name,
      SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE  TABLE MEDINFO " +"(_id INTEGER PRIMARY KEY  NOT NULL, " + "name VARCHAR   , " +"method VAR,"+ "amount  INTEGER," +"day  INTEGER ," + "tvTime TIME)");
        db.execSQL("CREATE  TABLE ALARM " +"(_id INTEGER PRIMARY KEY  NOT NULL, " + "Aname VAR   , " +"Atime TIME)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
