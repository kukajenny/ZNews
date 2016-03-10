package com.example.zjp.znews.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.zjp.znews.R;

import java.util.Date;

/**
 * Created by zjp on 16-3-10.
 */
public class News_item {

    private Bitmap bitmap;

    private String name;

    private String telnum;

    private String smsbody;

    private Date date;

    private int typeId;

    public News_item(String name,String telnum,String smsbody,Date date,int typeId){
        this.bitmap =bitmap;
        this.name=name;
        this.telnum=telnum;
        this.smsbody=smsbody;
        this.date=date;
        this.typeId =typeId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getName() {
        return name;
    }

    public String getTelnum() {
        return telnum;
    }

    public String getSmsbody() {
        return smsbody;
    }

    public Date getDate() {
        return date;
    }

    public int getTypeId() {
        return typeId;
    }
}
