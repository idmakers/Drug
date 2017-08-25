package com.example.lab714_pc.drug;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Dialog;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button btadd ,btitem;
    private Context context  = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btadd = (Button) findViewById(R.id.addh);
        btadd.setOnClickListener(this);

    }
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case  R.id.addh:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,AddByHand.class);
                    startActivity(intent);
                    break;


        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
