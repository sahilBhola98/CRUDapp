package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;
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
        DownloadTask downloadTask = new DownloadTask(progressBar,this);
        downloadTask.execute("www.imageurltobedownloaded.com");
    }


}