package com.example.expenselogger.utils;

import android.content.Context;
import android.text.Html;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class AppUtils {
    public static String DateFormat = "MMMM dd, yyyy";

    public static String ToDateFormat(int year, int month, int dayOfMonth) {
        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
        String formattedDate = new SimpleDateFormat(DateFormat).format(date);
        return formattedDate;
    }

    public static String ToDateFormatInDB(int year, int month, int dayOfMonth) {
        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
        String formattedDate = new SimpleDateFormat("yyyy-m-dd").format(date);
        return formattedDate;
    }

    public static String ToDateFormatInDB(Date date) {
        return ToDateFormatInDB(date.getYear(), date.getMonth(), date.getDay());
    }

    public static void ToastMessage(Context context, String message, boolean isError) {
        String textColor = "#4BB543";
        if (isError) {
            textColor = "#800000";
        }
        Toast toast = Toast.makeText(context, Html.fromHtml("<font color='" + textColor + "' ><b>"
                + message + "</b></font>"), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 170);
        toast.show();
    }

    public static void ShowErrorMessage(Context context, String message) {
        ToastMessage(context, message, true);
    }

    public static void ShowMessage(Context context, String message) {
        ToastMessage(context, message, false);
    }
}
