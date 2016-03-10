package com.example.zjp.znews.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

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


    private List<News_item> newsList = new ArrayList<News_item>();
    private Map<String,Boolean> map = new HashMap<String, Boolean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        //readShortMessage();
        getSmsInPhone();

        NewsAdapter newsAdapter = new NewsAdapter(NewsListActivity.this,R.layout.news_list_listitem,newsList);
        ListView listView = (ListView) findViewById(R.id.news_list_listview);
        listView.setAdapter(newsAdapter);
    }

    public void readShortMessage() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://sms/outbox"), null, null, null, null);
        String msg = "";
        while(cursor.moveToNext()) {
            int phoneColumn = cursor.getColumnIndex("address");
            int smsColumn = cursor.getColumnIndex("body");
            msg += cursor.getString(phoneColumn) + ":" + cursor.getString(smsColumn) + "\n";
        }
        TextView textView=(TextView)findViewById(R.id.text);
        textView.setText(msg);
    }

    public void getSmsInPhone()
    {
        final String SMS_URI_ALL   = "content://sms/";
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_SEND  = "content://sms/sent";
        final String SMS_URI_DRAFT = "content://sms/draft";


        try{
            ContentResolver cr = getContentResolver();
            String[] projection = new String[]{"_id", "address", "person",
                    "body", "date", "type"};
            Uri uri = Uri.parse(SMS_URI_ALL);
            Cursor cur = cr.query(uri, projection, null, null, "date desc");

            if (cur.moveToFirst()) {
                String name;
                String phoneNumber;
                String smsbody;
                String date;
                String type;

                int nameColumn = cur.getColumnIndex("person");
                int phoneNumberColumn = cur.getColumnIndex("address");
                int smsbodyColumn = cur.getColumnIndex("body");
                int dateColumn = cur.getColumnIndex("date");
                int typeColumn = cur.getColumnIndex("type");

                do{

                    name = cur.getString(nameColumn);
                    phoneNumber = cur.getString(phoneNumberColumn);

                    if(map.get(phoneNumber))continue;
                    else map.put(phoneNumber,true);

                    smsbody = cur.getString(smsbodyColumn);

                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd KK:mm:ss");

                    Date d = new Date(Long.parseLong(cur.getString(dateColumn)));

                    int typeId = cur.getInt(typeColumn);

                    if(smsbody == null) smsbody = "";

                    News_item news_item=new News_item(name,phoneNumber,smsbody,d,typeId);

                    newsList.add(news_item);

                }while(cur.moveToNext());
            }

        } catch(SQLiteException ex) {
            LogUtil.d("SQLiteException in getSmsInPhone", ex.getMessage());
        }
    }
}
