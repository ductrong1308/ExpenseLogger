package com.example.expenselogger.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.expenselogger.db.TableDefinitions;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ExpenseLoggerDB.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //  called after the database connection has been configured
    //  and after the database schema has been created, upgraded or downgraded as necessary
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableDefinitions.SQL_CREATE_USERS);
        db.execSQL(TableDefinitions.SQL_CREATE_CATEGORIES);
        db.execSQL(TableDefinitions.SQL_CREATE_EXPENSES);
        db.execSQL(TableDefinitions.SQL_CREATE_SETTINGS);
        // Create seed data
        CreateSeedData(db);
        Log.d("MyDB", "onCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TableDefinitions.SQL_DELETE_EXPENSES);
        db.execSQL(TableDefinitions.SQL_DELETE_SETTINGS);
        db.execSQL(TableDefinitions.SQL_DELETE_CATEGORIES);
        db.execSQL(TableDefinitions.SQL_DELETE_USERS);
        Log.d("MyDB", "OnUpgrade");
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyDB", "onDowngrade");
        onUpgrade(db, oldVersion, newVersion);
    }

    private void CreateSeedData(SQLiteDatabase db){
        db.execSQL(SeedData.SQL_INSERT_USERS);
        db.execSQL(SeedData.SQL_INSERT_SETTINGS);
        db.execSQL(SeedData.SQL_INSERT_CATEGORIES);
        db.execSQL(SeedData.SQL_INSERT_EXPENSES);
    }
}
