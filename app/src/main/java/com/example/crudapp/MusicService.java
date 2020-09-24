package com.example.crudapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicService extends Service {
public static final String TAG = MusicService.class.getSimpleName();
public MusicService(){

}
    @Nullable
    @Override
    public void onCreate(){
        super.onCreate();
        Log.i(TAG,"service created");
        
    }
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG,"service destroyed");
    }
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
