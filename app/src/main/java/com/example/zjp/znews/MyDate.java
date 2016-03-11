package com.example.zjp.znews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zjp on 16-3-11.
 */
public class MyDate {

    private Date date;

    public MyDate(Date date){
        this.date=date;
    }

    public String gettime(){
        String time="";
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy年MM月dd日 kk:mm");

        String before = dateFormat.format(date);
        String now = dateFormat.format(new java.util.Date());

        if(now.substring(0, 11).equals(before.substring(0,11))){
            time=before.substring(12,17);
        }
        else if(now.substring(0,4).equals(before.substring(0,4))){
            time=before.substring(5,11);
        }
        else time=before.substring(0,11);
        return time;
    }
}
