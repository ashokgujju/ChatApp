package com.ashok.chatapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ashok on 22/11/17.
 */

public class Util {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");

    public static Long getTimeStamp() {
        return new Date().getTime() / 1000;
    }

    public static String getTime(Long timestamp) {
        Date date = new Date(timestamp * 1000);
        return dateFormat.format(date);
    }
}