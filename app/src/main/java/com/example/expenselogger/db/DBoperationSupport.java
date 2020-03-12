package com.example.expenselogger.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DBoperationSupport {

    private static SQLiteDatabase wdb = null;
    private static DBHelper db = null;

    public static SQLiteDatabase getWritable(Context context) {
        if (db == null) {
            db = new DBHelper(context);
        }

        if (wdb == null) {
            wdb = db.getWritableDatabase();
        }

        return wdb;
    }

    public static void displayAll(TableLayout theView, SQLiteDatabase wdb, Context context, String selectQuery) {

        theView.removeAllViews(); // to remove previously created rows
        try {
            Cursor cursor = wdb.rawQuery(selectQuery, null); // 2nd arg is for where clause. For joining tables, don't use it

            String[] columnNames = cursor.getColumnNames();

            // looping through all rows and adding to list
            if (cursor != null) {
                cursor.moveToFirst();
                TextView data;
                TableRow row;

                // create the header
                row = new TableRow(context);
                for (int i = 0; i < columnNames.length; i++) {
                    row.setPadding(2, 2, 2, 2);
                    data = new TextView(context);
                    data.setTypeface(Typeface.DEFAULT_BOLD);
                    data.setText(columnNames[i]);
                    row.addView(data);
                }
                theView.addView(row);

                int cnt = 0;
                do {
                    row = new TableRow(context);      // creates 1 row at a time
                    // left, top, right, bottom
                    row.setPadding(2, 2, 2, 2);

                    for (int x = 0; x < cursor.getColumnCount(); x++) {
                        data = new TextView(context);  // creates TextView dynamically
                        if (x == 0) {                           // variable x represents the column, x == 0 means first column
                            data.setTypeface(Typeface.DEFAULT_BOLD);
                            data.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        data.setText(cursor.getString(x));
                        row.addView(data);                      // adds each column to the row
                    }
                    theView.addView(row);                       // adds the new row to the table
                } while (cursor.moveToNext());

                theView.setStretchAllColumns(true);
                cursor.close();
            }
        } catch (Exception ex) {
        }
    }

    public static void close() {
        if (db != null)
            db.close();
    }
}
