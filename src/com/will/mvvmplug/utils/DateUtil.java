package com.will.mvvmplug.utils;

import com.twelvemonkeys.util.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    static public String getCurrentTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
