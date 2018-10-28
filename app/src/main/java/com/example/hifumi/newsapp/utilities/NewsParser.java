package com.example.hifumi.newsapp.utilities;

import com.example.hifumi.newsapp.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsParser {
    public static ArrayList<News> makeNewsList(String jsonResult){
        ArrayList<News> newsList = new ArrayList<>();
        try{
            JSONObject main = new JSONObject(jsonResult);
            JSONArray list = main.getJSONArray("articles");

            for(int i = 0; i < list.length(); i++){
                JSONObject item = list.getJSONObject(i);
                newsList.add(new News(item.getString("author"), item.getString("title"), item.getString("description"), item.getString("url"), item.getString("urlToImage"), item.getString("publishedAt")));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return newsList;
    }
}
