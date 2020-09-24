package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crudapp.Background.DownloadTask;

public class AsyncActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        progressBar = findViewById(R.id.progressBarDownload);



    }
    public void taskClick(View view){
        Intent serviceIntent = new Intent(AsyncActivity.this,MusicService.class);
        switch(view.getId()){
            case R.id.buttonTask: DownloadTask downloadTask = new DownloadTask(progressBar,this);
                downloadTask.execute("www.imageurltobedownloaded.com");
                break;
            case R.id.buttonStart:
                startService(serviceIntent);
                break;
            case R.id.buttonStop:
                stopService(serviceIntent);
                break;
        }

    }


}