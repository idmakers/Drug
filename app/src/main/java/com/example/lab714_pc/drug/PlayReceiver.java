package com.example.lab714_pc.drug;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by idmakers on 2017/9/23.
 */

public class PlayReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle bData = intent.getExtras();
        if(bData.get("msg").equals("play_hskay"))
        {


        }
    }
}