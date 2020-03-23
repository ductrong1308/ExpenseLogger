package com.example.expenselogger.activities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expenselogger.R;
import com.example.expenselogger.classes.MonthlyExpenseData;
import com.example.expenselogger.db.DBoperationSupport;
import com.example.expenselogger.utils.AppUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ChartFragment extends Fragment {

    SQLiteDatabase wdb;
    int userId = 1;
    BarChart barChart;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> labelNames;
    ArrayList<MonthlyExpenseData> monthlyExpenseData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wdb = DBoperationSupport.getWritable(getActivity());
        this.userId = AppUtils.GetCurrentLoggedInUserId(getActivity());
        this.barChart = getView().findViewById(R.id.barChart);

        fillMonthlyExpense();

        barEntries = new ArrayList<>();
        labelNames = new ArrayList<>();

        for (int i = 0; i < monthlyExpenseData.size(); i++) {
            String month = monthlyExpenseData.get(i).getMonth();
            double expense = monthlyExpenseData.get(i).getExpense();

            barEntries.add(new BarEntry(i, (float)expense));
            labelNames.add(month);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Monthly Expenses");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        Description description = new Description();
        description.setText("Months");
        barChart.setDescription(description);

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);

        // Set XAis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelNames));

        // Set position of labels
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(labelNames.size());
        xAxis.setLabelRotationAngle(270);

        barChart.animateY(2000);
        barChart.invalidate();
    }

    private void fillMonthlyExpense() {
        monthlyExpenseData.clear();
        monthlyExpenseData.add(new MonthlyExpenseData("Jan", 100000));
        monthlyExpenseData.add(new MonthlyExpenseData("Feb", 200000));
        monthlyExpenseData.add(new MonthlyExpenseData("Mar", 300000));
        monthlyExpenseData.add(new MonthlyExpenseData("Apr", 400000));

        monthlyExpenseData.add(new MonthlyExpenseData("May", 100000));
        monthlyExpenseData.add(new MonthlyExpenseData("Jun", 200000));
        monthlyExpenseData.add(new MonthlyExpenseData("Jul", 300000));
        monthlyExpenseData.add(new MonthlyExpenseData("Aug", 400000));

        monthlyExpenseData.add(new MonthlyExpenseData("Sep", 400000));
        monthlyExpenseData.add(new MonthlyExpenseData("Oct", 100000));
        monthlyExpenseData.add(new MonthlyExpenseData("Nov", 200000));
        monthlyExpenseData.add(new MonthlyExpenseData("Dec", 300000));

    }
}
















