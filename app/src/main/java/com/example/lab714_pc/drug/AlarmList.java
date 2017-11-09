package com.example.lab714_pc.drug;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class AlarmList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlistview);
        ListView list = (ListView) findViewById(R.id.list);
        MyDBHelper helper = new MyDBHelper(this, "expense.db", null, 1);
        Cursor c = helper.getReadableDatabase().query("ALARM", null, null,null,null,null,null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.content_alarmlistview,
                c,

              new String[] {"_id","Aname", "Atime"},
              new int[] {R.id.item_id, R.id.item_info, R.id.item_cdate}, 0);

        list.setAdapter(adapter);

    }


}
