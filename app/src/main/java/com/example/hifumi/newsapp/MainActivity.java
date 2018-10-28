package com.example.hifumi.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hifumi.newsapp.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private TextView newsView;
    private String results;
    private Toolbar tbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbar = findViewById(R.id.tbar);
        setSupportActionBar(tbar);
        newsView = findViewById(R.id.textView);
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
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.getNews:
                newsView.setText(results);
        }
        return super.onOptionsItemSelected(item);
    }
}
