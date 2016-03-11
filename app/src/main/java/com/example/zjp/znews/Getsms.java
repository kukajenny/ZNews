package com.example.zjp.znews;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

import com.example.zjp.znews.model.News_item;
import com.example.zjp.znews.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 16-3-11.
 */
public class Getsms {

    private Context context;
    private String item_telnum;
    private int List_type;
    private Map<String,Boolean> map = new HashMap<String, Boolean>();
    private final int Get_all_list=1;
    private final int Get_item_list=2;
    private List<News_item> newsList = new ArrayList<News_item>();

    public Getsms(Context context,int type_id,String item_telnum){
        this.context=context;
        this.List_type =type_id;
        this.item_telnum = item_telnum;
    }

    public List getSmsInPhone()
    {
        final String SMS_URI_ALL   = "content://sms/";
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_SEND  = "content://sms/sent";
        final String SMS_URI_DRAFT = "content://sms/draft";


        try{
            ContentResolver cr = context.getContentResolver();
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

                    if(List_type ==Get_all_list){
                        if(map.containsKey(phoneNumber) && map.get(phoneNumber))continue;
                        else map.put(phoneNumber,true);
                    }
                    else if(List_type==Get_item_list){
                        if(!phoneNumber.equals(item_telnum))continue;
                    }

                    smsbody = cur.getString(smsbodyColumn);

                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd KK:mm:ss");

                    Date d = new Date(Long.parseLong(cur.getString(dateColumn)));

                    int typeId = cur.getInt(typeColumn);

                    if(smsbody == null) smsbody = "";

                    News_item news_item=new News_item(name,phoneNumber,smsbody,new MyDate(d).gettime(),typeId);

                    newsList.add(news_item);

                    LogUtil.d("mytype",typeId+"");

                }while(cur.moveToNext());

            }

        } catch(SQLiteException ex) {
            LogUtil.d("SQLiteException in getSmsInPhone", ex.getMessage());
        }
        return newsList;
    }

}
