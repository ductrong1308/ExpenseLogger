package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.DBEntries.ExpenseDetailsEntry;
import com.example.myapplication.DBEntries.ExpenseTypeEntry;
import com.example.myapplication.DBEntries.SettingsEntry;

import java.util.Set;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ExpenseLoggerDB.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_create_settings = "CREATE TABLE " + SettingsEntry.TABLE_NAME + " (" +
                SettingsEntry._ID + " INTEGER PRIMARY KEY," +
                SettingsEntry.COLUMN_NAME + " TEXT," +
                SettingsEntry.COLUMN_VALUE + " TEXT)";

        String sql_create_expense_details = "CREATE TABLE " + ExpenseDetailsEntry.TABLE_NAME + " (" +
                ExpenseDetailsEntry._ID + " INTEGER PRIMARY KEY," +
                ExpenseDetailsEntry.COLUMN_NAME + " TEXT," +
                ExpenseDetailsEntry.COLUMN_AMOUNT + " TEXT," +
                ExpenseDetailsEntry.COLUMN_DESCRIPTION + " TEXT," +
                ExpenseDetailsEntry.COLUMN_DATE + " DATE)";

        String sql_create_expense_type = "CREATE TABLE " + ExpenseTypeEntry.TABLE_NAME + " (" +
                ExpenseTypeEntry._ID + " INTEGER PRIMARY KEY," +
                ExpenseTypeEntry.COLUMN_NAME + " TEXT)";

        db.execSQL(sql_create_settings);
        db.execSQL(sql_create_expense_details);
        db.execSQL(sql_create_expense_type);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_delete_settings = "DROP TABLE IF EXISTS " + SettingsEntry.TABLE_NAME;
        String sql_delete_expense_details = "DROP TABLE IF EXISTS " + ExpenseDetailsEntry.TABLE_NAME;
        String sql_delete_expense_type = "DROP TABLE IF EXISTS " + ExpenseTypeEntry.TABLE_NAME;

        db.execSQL(sql_delete_settings);
        db.execSQL(sql_delete_expense_details);
        db.execSQL(sql_delete_expense_type);

        this.onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        this.onUpgrade(db, oldVersion, newVersion);
    }
}
