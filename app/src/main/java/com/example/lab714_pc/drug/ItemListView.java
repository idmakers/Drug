package com.example.lab714_pc.drug;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ItemListView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlistview);
        ListView list = (ListView) findViewById(R.id.list);
        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
        Cursor c = helper.getReadableDatabase().query("exp", null, null, null, null, null, null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.content_itemlistview,
                c,
                new String[] {"_id", "amount","day","method"},
                new int[] {R.id.item_id, R.id.item_amount, R.id.item_info, R.id.item_cdate}, 0);
                //new String[] {"name", "amount"},
                //new int[] {android.R.id.text1, android.R.id.text2},0);

        list.setAdapter(adapter);

        }


    }


