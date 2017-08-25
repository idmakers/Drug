package com.example.lab714_pc.drug;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

public class ItemListView extends AppCompatActivity {
    private MyDBHelper helper = new MyDBHelper(this,"expense.db",null,1);
    /*SQLiteDatabase DB = helper.getReadableDatabase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_itemlistview);
            ListView listview = (ListView) findViewById(R.id.list_item);
            Cursor cursor = DB.rawQuery("SELECT_ID FROM main exp",null);
            while (cursor != null && cursor.getCount()>=0 ){
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,
                        cursor,new String[]{"name","method"},new int[]{android.R.id.text1, android.R.id.text2}, 0);
                listview.setAdapter(adapter);
            }

            }
*/

}
