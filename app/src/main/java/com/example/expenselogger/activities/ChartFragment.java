package com.example.expenselogger.activities;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expenselogger.R;
import com.example.expenselogger.classes.MonthlyExpenseData;
import com.example.expenselogger.db.DBoperationSupport;
import com.example.expenselogger.utils.AppMessages;
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
import java.util.Calendar;

public class ChartFragment extends Fragment {

    SQLiteDatabase wdb;
    int userId = 1;
    BarChart barChart;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> labelNames;
    ArrayList<MonthlyExpenseData> monthlyExpenseData = new ArrayList<>();
    ArrayAdapter<String> spinnerArrayAdapter;
    ArrayList<String> years;
    String selectedYear;

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

        this.years = this.GetYearList();
        this.selectedYear = this.years.get(0);

        Spinner spinnerYears = (Spinner) getView().findViewById(R.id.spinnerYears);
        spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, years);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerYears.setAdapter(spinnerArrayAdapter);

        spinnerYears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedYear = years.get(position);
                RenderChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        RenderChart();
    }

    private void RenderChart() {
        // Query chard data from DB
        GetChartData(this.selectedYear);

        // Chart settings
        barEntries = new ArrayList<>();
        labelNames = new ArrayList<>();

        for (int i = 0; i < monthlyExpenseData.size(); i++) {
            String month = monthlyExpenseData.get(i).getMonth();
            double expense = monthlyExpenseData.get(i).getExpense();

            barEntries.add(new BarEntry(i, (float) expense));
            labelNames.add(month);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Monthly Expenses");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        Description description = new Description();
        description.setText("Months");
        barChart.setDescription(description);

        if (monthlyExpenseData.size() == 0) {
            barChart.clear();
            barChart.setNoDataText(AppMessages.NoDataFound);
        }
        else {
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

            barChart.animateY(1000);
        }

        barChart.invalidate();
    }

    private void GetChartData(String year) {
        try {
            monthlyExpenseData = DBoperationSupport.GetMonthlyExpenseData(year);
        } catch (Exception ex) {
            AppUtils.ShowErrorMessage(getContext(), AppMessages.AnErrorHasOccurred);
            ex.printStackTrace();
        }
    }

    private ArrayList<String> GetYearList() {
        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        int start = 2019;

        for (int i = thisYear; i >= start; i--) {
            years.add(Integer.toString(i));
        }

        return years;
    }
}
















