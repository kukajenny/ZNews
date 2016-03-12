package com.example.zjp.znews.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zjp.znews.Getsms;
import com.example.zjp.znews.R;
import com.example.zjp.znews.adapter.NewsAdapter;
import com.example.zjp.znews.model.News_item;
import com.example.zjp.znews.util.LogUtil;
import com.mxn.soul.slidingcard_core.ContainerView;
import com.mxn.soul.slidingcard_core.SlidingCard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zjp on 16-3-10.
 */
public class NewsShowActivity extends AppCompatActivity implements ContainerView.ContainerInterface{

    private ContainerView contentView;
    private List<News_item> news_List ;
    private final int Get_item_list=2;
    private TextView textView_telnum;
    private TextView textView_smsbody;
    private TextView textView_time;
    private TextView textView_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_show);
        contentView = (ContainerView) findViewById(R.id.contentview);

        initData() ;


    }
    private void initData(){


        Intent intent = getIntent();
        String telnum = intent.getStringExtra("telnum");

        Getsms getsms = new Getsms(this,Get_item_list,telnum);
        news_List = getsms.getSmsInPhone();
        contentView.initCardView(this, R.layout.sliding_card_item, R.id
                .sliding_card_content_view);

    }

    @Override
    public void initCard(SlidingCard card, int index) {

        News_item news_item = news_List.get(index);

        if (news_item != null) {

            textView_telnum = (TextView) card.findViewById(R.id.card_item_textview_telnum);
            textView_smsbody = (TextView)card.findViewById(R.id.card_item_textview_smsbody);
            textView_time = (TextView) card.findViewById(R.id.card_item_textview_time);
            textView_type = (TextView) card.findViewById(R.id.card_item_textview_type);
            LogUtil.d("mytype",news_item.getTelnum()+textView_telnum+textView_smsbody);
            textView_telnum.setText(news_item.getTelnum());
            textView_smsbody.setText(news_item.getSmsbody());
            textView_time.setText(news_item.getDate());

        }
    }

    @Override
    public void exChangeCard() {
        News_item item = news_List.get(0);
        news_List.remove(0);
        news_List.add(item);
    }
}
