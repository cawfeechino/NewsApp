package com.example.hifumi.newsapp;

import android.net.Network;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hifumi.newsapp.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.newsView);
        NewsTask task = new NewsTask();
        task.execute();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    class NewsTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids){
            try{
                results = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());
            } catch (Exception e){
                e.printStackTrace();
            }
            textView.setText(results);
            return null;
        }
    }
}
