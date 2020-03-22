package com.example.expenselogger.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.expenselogger.R;
import com.example.expenselogger.classes.Expense;

import java.util.ArrayList;

public class ExpensesCustomAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<Expense> expenses;

    public ExpensesCustomAdapter(Activity context, ArrayList<Expense> expenses) {
        super(context, R.layout.expenses_layout, expenses);

        this.context = context;
        this.expenses = expenses;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.expenses_layout, null, false);

        TextView row = (TextView) rowView.findViewById(R.id.expenseItem);
        row.setText(expenses.get(position).ToString());
        if(position % 2 != 0){
            row.setBackgroundResource(R.color.oddRow);
        }

        return rowView;
    }
}
