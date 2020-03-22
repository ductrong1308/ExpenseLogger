package com.example.expenselogger.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.expenselogger.R;
import com.example.expenselogger.classes.Expense;

import java.util.ArrayList;
import java.util.Arrays;

public class ExpensesCustomAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<Expense> expenses;

    Drawable imgEntertainment = this.getContext().getResources().getDrawable(R.drawable.entertainment);
    Drawable imgEducation = this.getContext().getResources().getDrawable(R.drawable.study);
    Drawable imgMeals = this.getContext().getResources().getDrawable(R.drawable.foods);
    Drawable imgTransportation = this.getContext().getResources().getDrawable(R.drawable.transportation);
    Drawable imgPets = this.getContext().getResources().getDrawable(R.drawable.pets);
    Drawable imgGeneral = this.getContext().getResources().getDrawable(R.drawable.general_expense);


    public ExpensesCustomAdapter(Activity context, ArrayList<Expense> expenses) {
        super(context, R.layout.expenses_item_layout, expenses);

        this.context = context;
        this.expenses = expenses;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.expenses_item_layout, null, false);

        TextView row = (TextView) rowView.findViewById(R.id.expenseItem);
        Expense item = expenses.get(position);
        row.setText(item.ToString());

        Drawable imgCategory = AppUtils.getImageByKeyWord(getContext(), item.GetCategory());
        imgCategory.setBounds(10, 0, 100, 100);
        row.setCompoundDrawables(imgCategory, null, null, null);

        if (position % 2 != 0) {
            row.setBackgroundResource(R.color.oddRow);
        }

        return rowView;
    }

}
