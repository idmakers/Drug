package com.example.lab714_pc.drug;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

/**
 * Created by idmakers on 2017/10/26.
 */


public class PlayReceiver extends BroadcastReceiver {

    private SoundPool sp;
    private int sourceid;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bData = intent.getExtras();

        if (bData.get("msg").equals("play_voice")) {
            sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
            sourceid = sp.load(context, R.raw.test, 1);

            playSounds(1, context);
        }

    }

    public void playSounds(int repeatTime, Context context) {
        AudioManager am = (AudioManager) context.getApplicationContext()
                .getSystemService(Context.AUDIO_SERVICE);
        // 獲取最大音量
        float audMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 獲取目前音量
        float audCurrentVolumn = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        // 左右聲道值範圍為 0.0 - 1.0
        float volRatio = audCurrentVolumn / audMaxVolumn;
        // 下面參數分別為播放音頻，左聲道,右聲道，設置優先級，重撥次數，速率(速率最低0.5，最高為2，1代表正常速度)
        sp.play(sourceid, volRatio, volRatio, 1, repeatTime, 1);
    }
}