package com.example.zjp.znews.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zjp.znews.Getsms;
import com.example.zjp.znews.MyDate;
import com.example.zjp.znews.R;
import com.example.zjp.znews.adapter.NewsAdapter;
import com.example.zjp.znews.model.News_item;
import com.example.zjp.znews.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zjp on 16-3-10.
 */
public class NewsListActivity extends AppCompatActivity {

    private final int Get_all_list=1;
    private List<News_item> newsList = new ArrayList<News_item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        //readShortMessage();
        Getsms getsms = new Getsms(this,Get_all_list,null);
        newsList = getsms.getSmsInPhone();


        NewsAdapter newsAdapter = new NewsAdapter(NewsListActivity.this,R.layout.news_list_listitem,newsList);
        ListView listView = (ListView) findViewById(R.id.news_list_listview);
        listView.setAdapter(newsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News_item news_item = newsList.get(position);
                Intent intent = new Intent(NewsListActivity.this, NewsShowActivity.class);
                intent.putExtra("telnum", news_item.getTelnum());
                startActivity(intent);

            }
        });
    }


}
