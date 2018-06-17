package com.example.lab714_pc.drug;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHelper extends SQLiteOpenHelper {


    private SQLiteDatabase db;

    public MyDBHelper(Context context, String name,
                      SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  MEDINFO " + "(_id INTEGER PRIMARY KEY  NOT NULL, "
                + "name VARCHAR , "
                + "method VAR,"
                + "amount  INTEGER,"
                + "day  INTEGER ,"
                + "morning LONG ,"
                + "noon LONG ,"
                + "night LONG ,"
                + "bf VAR ,"
                + "midnight LONG ,"
                + "DATE STRING ,"
                + "why STRING ,"
                + "stop STRING ,"
                + "tvTime TIME)");
        db.execSQL("CREATE TABLE IF NOT EXISTS" + " ALARM " + "(_id INTEGER PRIMARY KEY , " + "Aname VAR UNIQUE   , " + "Atime TIME)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor filList(long id) throws SQLException {
        Cursor cursor = db.query(
                "ALARM",
                new String[]{"_id", "Atime"},
                "_id =" + id,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();    //將指標移到第一筆資料
        }
        return cursor;
    }

    public int update(long rowId,String value) {
        ContentValues args = new ContentValues();

        args.put("Atime", value);

        return db.update("ALARM",    //資料表名稱
                args,                //VALUE
                "_id=" + rowId,            //WHERE
                null                //WHERE的參數
        );
    }

    public boolean isEmpty() {
        db.execSQL("CREATE TABLE IF NOT EXISTS" + " ALARM " + "(_id INTEGER PRIMARY KEY , " + "Aname VAR UNIQUE   , " + "Atime TIME)");
        Cursor cursor = db.query(
                "ALARM",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.getCount() == 0) {
            return false;    //將指標移到第一筆資料
        } else
            return true;
    }

    public Cursor ItemfilList(long id) throws SQLException {
        Cursor cursor = db.query(
                "MEDINFO",
                new String[]{"_id", "tvTime","method" ,"day","name","amount","stop","why","bf","morning","noon","night","midnight"},
                "_id =" + id,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();    //將指標移到第一筆資料
        }
        return cursor;
    }
    public Cursor ItemfilListName(String Name) throws SQLException {
        Cursor cursor = db.query(
                "MEDINFO",
                new String[]{"_id", "tvTime","method" ,"day","name","amount","stop","why","bf","morning","noon","night","midnight"},
                "name=Name",
                null,
                null,
                null,
                null,
                null
        );
        if (cursor != null) {
            cursor.moveToFirst();    //將指標移到第一筆資料
        }
        return cursor;
    }


    public int Itemupdate(long rowId, ContentValues value) {



        return db.update("MEDINFO",    //資料表名稱
                value,                //VALUE
                "_id=" + rowId,            //WHERE
                null                //WHERE的參數
        );
    }
    public int ItemCounter(String Name, ContentValues value) {



        return db.update("MEDINFO",    //資料表名稱
                value,                //VALUE
                "name=Name",            //WHERE
                null                //WHERE的參數
        );
    }
    public int ItemDel(long rowId){
        return db.delete("MEDINFO",    //資料表名稱
                             //VALUE
                "_id=" + rowId,            //WHERE
                null             );
    }

    public boolean ItemisEmpty() {
        db.execSQL("CREATE TABLE IF NOT EXISTS" + " MEDINFO "
                + "(_id INTEGER PRIMARY KEY , "
                + "name VARCHAR , "
                + "method VAR,"
                + "amount  VAR,"
                + "day  INTEGER ,"
                + "morning LONG ,"
                + "noon LONG ,"
                + "night LONG ,"
                + "midnight LONG ,"
                + "DATE STRING ,"
                + "why STRING ,"
                + "stop STRING ,"
                + "tvTime TIME)");
        Cursor cursor = db.query(
                "MEDINFO",
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.getCount() == 0) {
            return false;    //將指標移到第一筆資料
        } else
            return true;
    }


}




