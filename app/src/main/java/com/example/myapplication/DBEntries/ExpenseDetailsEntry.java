package com.example.myapplication.DBEntries;

        import android.provider.BaseColumns;

public class ExpenseDetailsEntry implements BaseColumns {
    public static final String TABLE_NAME = "expense_details";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_DESCRIPTION = "description";
}
