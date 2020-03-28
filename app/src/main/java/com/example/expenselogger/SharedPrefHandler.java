package com.example.expenselogger;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class SharedPrefHandler {
    private static String prefName = "ExpenseLoggerSharedPrefs";

    // Context.MODE_PRIVATE means that only this application can access it
    private static SharedPreferences sharedPref = null;

    public static SharedPreferences getSharedPref(Context context) {
        if (sharedPref == null) {
            sharedPref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        }

        return sharedPref;
    }

    public static void storeData(String key, String value, Context context) {
        // check if key and value exist
        if ((key.length() > 0) && (value.length() > 0)) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public static String getData(String key, Context context) {
        // check if key exists
        String value = "";
        if ((key.length() > 0)) {
            String defaultValue = "";
            value = sharedPref.getString(key, defaultValue);
        }

        return value;
    }

    public static void clearSharedPref(){
        sharedPref.edit().remove("USERID").commit();
        sharedPref.edit().remove("USERFNAME").commit();
        sharedPref.edit().remove("USERLNAME").commit();
        sharedPref.edit().remove("CURRENCY").commit();
    }
}
