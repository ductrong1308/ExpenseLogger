package com.example.expenselogger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class AppUtils {
    public static String DateFormat = "MMMM dd, yyyy";

    public static String ToDateFormat(int year, int month, int dayOfMonth){
        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
        String formattedDate = new SimpleDateFormat(DateFormat).format(date);
        return formattedDate;
    }

    public static String ToDateFormatInDB(int year, int month, int dayOfMonth){
        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
        String formattedDate = new SimpleDateFormat("yyyy-mm-dddd").format(date);
        return formattedDate;
    }
}
