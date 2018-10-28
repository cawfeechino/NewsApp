package com.example.hifumi.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hifumi.newsapp.models.News;
import com.example.hifumi.newsapp.utilities.NetworkUtils;
import com.example.hifumi.newsapp.utilities.NewsParser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String results;
    private Toolbar tbar;
    private ArrayList<News> news = new ArrayList<>();
    private RecyclerView newsView;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbar = findViewById(R.id.tbar);
        newsView = findViewById(R.id.newsView);
        newsAdapter = new NewsAdapter(this, news);
        newsView.setAdapter(newsAdapter);
        newsView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(tbar);
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
                fillWithNews(results);
        }
        return super.onOptionsItemSelected(item);
    }

    public void fillWithNews(String newsJson){
        news = NewsParser.makeNewsList(newsJson);
        newsAdapter.newsList = news;
        newsAdapter.notifyDataSetChanged();
    }
}
