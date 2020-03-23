package com.example.expenselogger.db;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.util.Pair;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expenselogger.SharedPrefHandler;
import com.example.expenselogger.activities.LoginActivity;
import com.example.expenselogger.activities.MainActivity;
import com.example.expenselogger.classes.Category;
import com.example.expenselogger.classes.Expense;
import com.example.expenselogger.classes.MonthlyExpenseData;
import com.example.expenselogger.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

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

    public static ArrayList<String> GetCategoriesByUserId(int userId) {
        ArrayList<String> categories = new ArrayList<String>();
        String query = "SELECT categoryName FROM Categories WHERE userID = " + userId + " ORDER BY id ASC";
        Cursor cursor = wdb.rawQuery(query, null);

        int size = cursor.getCount();
        if (size != 0) {
            while (cursor.moveToNext()) {
                categories.add(cursor.getString(cursor.getColumnIndex("categoryName")));
            }
        }
        cursor.close();

        return categories;
    }

    public static Pair<ArrayList<Expense>, Double> GetExpenseByDate(int userId, String fromDate, String toDate) {
        double sum = 0;
        ArrayList<Expense> expenses = new ArrayList<Expense>();
        String query =
                "SELECT * FROM Expenses WHERE userID = " + userId
                        + " AND createdDate BETWEEN '" + fromDate + "' AND '" + toDate
                        + "' ORDER BY date(createdDate) DESC, id DESC";
        Cursor cursor = wdb.rawQuery(query, null);

        int size = cursor.getCount();
        if (size > 0) {
            while (cursor.moveToNext()) {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                double amount = cursor.getDouble(cursor.getColumnIndex("amount"));
                String createdDate = cursor.getString(cursor.getColumnIndex("createdDate"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                Expense expense = new Expense(id, category, createdDate, amount);
                sum += amount;

                expenses.add(expense);
            }
        }
        cursor.close();

        return new Pair<ArrayList<Expense>, Double>(expenses, sum);
    }

    public static String GetUserCurrency(int userId) {
        String currencySettingName = "CAD";
        String query = "SELECT * FROM Settings WHERE userId = " + userId;
        Cursor cursor = wdb.rawQuery(query, null);
        int size = cursor.getCount();

        if (size != 0) {
            cursor.moveToFirst();
            String value = cursor.getString(cursor.getColumnIndex("value"));
            if (value.length() > 0) {
                currencySettingName = value;
            }
        }
        cursor.close();

        return currencySettingName;
    }

    public static void UpdateUserCurrency(int userId, String newValue) {
        String query = "UPDATE Settings SET value = '" + newValue
                + "' WHERE name = 'Currency' AND userId = " + userId;
        wdb.execSQL(query);
    }

    public static void DeleteExpense(int expenseId) {
        String query = "DELETE FROM Expenses WHERE id = " + expenseId;
        wdb.execSQL(query);
    }

    public static void UpdateExpense(int expenseId, String category, double amount, String date) {
        String query = "UPDATE Expenses SET category = '" + category + "', amount = "
                + amount + ", createdDate = '" + date + "' WHERE id = " + expenseId;
        wdb.execSQL(query);
    }

    public static ArrayList<Category> GetAllExpenseCategoriesByUser(int userId) {
        ArrayList<Category> categories = new ArrayList<Category>();
        String query = "SELECT * FROM Categories WHERE userId = " + userId + " ORDER BY id ASC";
        Cursor cursor = wdb.rawQuery(query, null);

        int size = cursor.getCount();
        if (size > 0) {
            while (cursor.moveToNext()) {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String categoryName = cursor.getString(cursor.getColumnIndex("categoryName"));
                Category category = new Category(id, categoryName);

                categories.add(category);
            }
            cursor.close();
        }

        return categories;
    }

    public static void AddExpenseCategory(int userId, String categoryName) {
        String query = "INSERT INTO categories (id, categoryName, userId) VALUES (null, '"
                + categoryName + "', " + userId + " )";
        wdb.execSQL(query);
    }

    public static void RemoveExpenseCategory(int categoryId) {
        String query = "DELETE FROM Categories WHERE id = " + categoryId;
        wdb.execSQL(query);
    }

    public static ArrayList<MonthlyExpenseData> GetMonthlyExpenseData(String year) {
        ArrayList<MonthlyExpenseData> monthlyExpenseData = new ArrayList<>();
        String query =
                "SELECT SUM(amount) AS 'totalExpense', strftime('%m', createdDate) as 'month' " +
                        "FROM Expenses " +
                        "WHERE strftime('%Y', createdDate) = '" + year + "' " +
                        "GROUP BY strftime('%m', createdDate) " +
                        "ORDER BY month ASC";

        Cursor cursor = wdb.rawQuery(query, null);

        int size = cursor.getCount();
        if (size > 0) {
            while (cursor.moveToNext()) {
                int month = Integer.parseInt(cursor.getString(cursor.getColumnIndex("month")));
                double amount = cursor.getDouble(cursor.getColumnIndex("totalExpense"));
                String monthName = AppUtils.Months.get(month - 1);
                MonthlyExpenseData newData = new MonthlyExpenseData(monthName, amount);
                monthlyExpenseData.add(newData);
            }
        }
        cursor.close();

        return monthlyExpenseData;
    }


    public static void close() {
        if (db != null)
            db.close();
    }
}
