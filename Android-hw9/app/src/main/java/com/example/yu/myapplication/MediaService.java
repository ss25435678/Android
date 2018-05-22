package com.example.yu.myapplication;

import android.support.annotation.Nullable;
import android.os.IBinder;
import android.media.MediaPlayer;
import android.content.Intent;
import android.app.Service;

public class MediaService extends Service {
    private MediaPlayer player;
    @Override
    @Nullable
    public IBinder onBind(Intent intent)
    {
        return  null;
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        player.stop();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int start)
    {
        player = MediaPlayer.create(this,R.raw.hwmusic);
        player.start();
        return super.onStartCommand(intent,flags,start);
    }
}
