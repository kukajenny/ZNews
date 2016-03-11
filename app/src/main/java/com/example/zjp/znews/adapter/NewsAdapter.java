package com.example.zjp.znews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zjp.znews.MyDate;
import com.example.zjp.znews.R;
import com.example.zjp.znews.model.News_item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zjp on 16-3-10.
 */
public class NewsAdapter extends ArrayAdapter<News_item>{


    private int resource;
    public NewsAdapter(Context context, int resource, List<News_item> objects) {
        super(context, resource, objects);
        this.resource=resource;
    }

    @Override
    public View getView(int position,View converView,ViewGroup parent){
        News_item item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource, null);

        ImageView imageView = (ImageView)view.findViewById(R.id.list_item_imageview);
        TextView textView_tel = (TextView)view.findViewById(R.id.list_item_textview_tel);
        TextView textView_smsbody = (TextView)view.findViewById(R.id.list_item_textview_smsbody);
        TextView textView_date = (TextView)view.findViewById(R.id.list_item_textview_date);

        imageView.setImageBitmap(item.getBitmap());

        if(item.getName()==null){
            textView_tel.setText(item.getTelnum());
        }
        else textView_tel.setText(item.getName());
        textView_smsbody.setText(item.getSmsbody());


        textView_date.setText(item.getDate());
        return view;
    }

}
