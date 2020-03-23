package com.example.expenselogger.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.expenselogger.R;
import com.example.expenselogger.SharedPrefHandler;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AppUtils {
    public static String DateFormat = "dd MMMM yyyy";
    public static String DateFormatDB = "yyyy-MM-dd";

    public static ArrayList<String> EntertainmentKeywords = new ArrayList<String>(
            Arrays.asList("movies", "films", "sports", "football", "stadium",
                    "ticket", "shopping", "entertainment", "relax", "theatre")
    );

    public static ArrayList<String> EducationKeywords = new ArrayList<String>(
            Arrays.asList("books", "tuition fee", "binders", "pens", "pencils", "library", "education", "college", "university")
    );

    public static ArrayList<String> MealsKeywords = new ArrayList<String>(
            Arrays.asList("meals", "breakfast", "lunch", "dinner", "snack", "coffee", "cafe",
                    "bread", "smoothie", "tea", "milk", "eat", "restaurant")
    );

    public static ArrayList<String> TransportationKeywords = new ArrayList<String>(
            Arrays.asList("cars", "fuels", "bus", "train", "airplane", "plane", "travels", "transportation")
    );

    public static ArrayList<String> PetsKeywords = new ArrayList<String>(
            Arrays.asList("cats", "dogs", "veterinary", "pets")
    );

    public static ArrayList<String> Months = new ArrayList<>(
            Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    );

    public static Drawable getImageByKeyWord(Context context, String category) {
        category = category.toLowerCase();
        if (EntertainmentKeywords.contains(category)) {
            return context.getResources().getDrawable(R.drawable.entertainment);
        } else if (EducationKeywords.contains(category)) {
            return context.getResources().getDrawable(R.drawable.study);
        } else if (MealsKeywords.contains(category)) {
            return context.getResources().getDrawable(R.drawable.foods);
        } else if (TransportationKeywords.contains(category)) {
            return context.getResources().getDrawable(R.drawable.transportation);
        } else if (PetsKeywords.contains(category)) {
            return context.getResources().getDrawable(R.drawable.pets);
        } else {
            return context.getResources().getDrawable(R.drawable.general_expense);
        }
    }

    public static int GetCurrentLoggedInUserId(Context context){
        int userId = 1;
        String userIdInSharedPref = SharedPrefHandler.getData("USERID", context);
        if (userIdInSharedPref != null && userIdInSharedPref.length() != 0) {
            userId = Integer.parseInt(userIdInSharedPref);
        }

        return  userId;
    }

    public static  String ToDateFormat(Date date) {
        return new SimpleDateFormat(DateFormat).format(date);
    }

    public static String ToDateFormat(int year, int month, int dayOfMonth) {
        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
        String formattedDate = new SimpleDateFormat(DateFormat).format(date);
        return formattedDate;
    }

    public static String ToDateFormatInDB(int year, int month, int dayOfMonth) {
        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
        String formattedDate = new SimpleDateFormat(DateFormatDB).format(date);
        return formattedDate;
    }

    public static String ToDateFormatInDB(Date date) {
        return new SimpleDateFormat(DateFormatDB).format(date);
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

    public static String ToDateFormatFromString(String dateInString){
        SimpleDateFormat formatter = new SimpleDateFormat(DateFormatDB);
        String dateToDisplay = "";
        try {
            Date date = formatter.parse(dateInString);
            dateToDisplay = ToDateFormat(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateToDisplay;
    }

    public static String FormatCurrency(Context context, double money){
        String currency = SharedPrefHandler.getData("CURRENCY", context);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "CA"));
        switch (currency){
            case "CAD":
                break;
            case "USD":
                numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
                break;
            case "GBP":
                numberFormat = NumberFormat.getCurrencyInstance(new Locale("en", "GB"));
                break;
            case "EUR":
                numberFormat = NumberFormat.getCurrencyInstance(new Locale("fr", "FR"));
                break;
            case "CNY":
                numberFormat = NumberFormat.getCurrencyInstance(new Locale("zh", "CN"));
                break;
            case "JPY":
                numberFormat = NumberFormat.getCurrencyInstance(new Locale("ja", "JP"));
                break;
        }

        return numberFormat.format(money);
    }

    public static void ShowErrorMessage(Context context, String message) {
        ToastMessage(context, message, true);
    }

    public static void ShowMessage(Context context, String message) {
        ToastMessage(context, message, false);
    }
}
