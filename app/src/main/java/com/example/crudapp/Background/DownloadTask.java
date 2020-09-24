package com.example.crudapp.Background;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.AsyncTask;

import com.example.crudapp.AsyncActivity;

public class DownloadTask extends AsyncTask<String , Integer , Void> {

    ProgressBar mProgressBar;
    Context mContext;
    public DownloadTask(ProgressBar progressBar, Context context){
        mProgressBar=progressBar;
        mContext=context;
    }
    protected void onPreExecute(){
        super.onPreExecute();
        mProgressBar.setVisibility(View.VISIBLE);

    }
    protected Void doInBackground(String... strings){
        try{
            for(int i=1;i<=20;i++){
                Thread.sleep(100);
                publishProgress(i*5);
            }
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
    }
    protected void onPostExecute(Void avoid){
        super.onPostExecute(avoid);
        mProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(mContext, "food cooked", Toast.LENGTH_SHORT).show();
    }
}
