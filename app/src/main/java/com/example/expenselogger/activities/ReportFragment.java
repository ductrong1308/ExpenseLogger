package com.example.expenselogger.activities;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expenselogger.R;
import com.example.expenselogger.SharedPrefHandler;
import com.example.expenselogger.classes.Expense;
import com.example.expenselogger.db.DBoperationSupport;
import com.example.expenselogger.utils.AppMessages;
import com.example.expenselogger.utils.AppUtils;
import com.example.expenselogger.utils.ExpensesCustomAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReportFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    SQLiteDatabase wdb;
    ArrayList<Expense> expenseData;
    String fromDate;
    String toDate;
    Date today = new Date();
    String[] filterBy;
    String selectedFilterBy;
    int userId = 1;
    double sumExpense = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wdb = DBoperationSupport.getWritable(getActivity());

        Spinner spinnerFilterBy = (Spinner) getView().findViewById(R.id.spinnerFilterBy);
        TextView textViewFromDate = (TextView) getView().findViewById(R.id.textViewFromDate);
        TextView textViewToDate = (TextView) getView().findViewById(R.id.textViewToDate);
        Button buttonFromDate = (Button) getView().findViewById(R.id.buttonFromDate);
        Button buttonToDate = (Button) getView().findViewById(R.id.buttonToDate);
        Button buttonView = (Button) getView().findViewById(R.id.buttonView);

        getView().findViewById(R.id.layoutTotalSpent).setVisibility(View.INVISIBLE);
        textViewFromDate.setText(AppUtils.ToDateFormat(today));
        textViewToDate.setText(AppUtils.ToDateFormat(today));
        this.fromDate = AppUtils.ToDateFormatInDB(today);
        this.toDate = AppUtils.ToDateFormatInDB(today);

        this.filterBy = getResources().getStringArray(R.array.filterBy);
        this.selectedFilterBy = filterBy[0];

        String userIdInSharedPref = SharedPrefHandler.getData("USERID", getActivity());
        if (userIdInSharedPref != null && userIdInSharedPref.length() != 0) {
            userId = Integer.parseInt(userIdInSharedPref);
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, filterBy);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerFilterBy.setAdapter(spinnerArrayAdapter);

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Pair<ArrayList<Expense>, Double> data = DBoperationSupport.GetExpenseByDate(userId, fromDate, toDate);
                    expenseData = data.first;
                    sumExpense = data.second;

                    if(expenseData.size() == 0){
                        getView().findViewById(R.id.layoutTotalSpent).setVisibility(View.INVISIBLE);
                        sumExpense = 0;
                        AppUtils.ShowMessage(getActivity(), AppMessages.NoDataFound);
                    }
                    else {
                        getView().findViewById(R.id.layoutTotalSpent).setVisibility(View.VISIBLE);
                        TextView totalSpent = (TextView) getView().findViewById(R.id.textViewTotalSpent);

                        totalSpent.setText(AppUtils.FormatCurrency(sumExpense));
                    }
                }
                catch (Exception ex){
                    AppUtils.ShowErrorMessage(getActivity(), AppMessages.AnErrorHasOccurred);
                }


                ExpensesCustomAdapter adapter = new ExpensesCustomAdapter(getActivity(), expenseData);
                ListView list = (ListView) getView().findViewById(R.id.expenseListView);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(getActivity(), expenseData[i], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buttonFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(1);
            }
        });

        buttonToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(2);
            }
        });
    }

    private void showDatePickerDialog(final int datePickerOrder) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                switch (datePickerOrder) {
                    case 1:
                        TextView textViewFromDate = (TextView) getView().findViewById(R.id.textViewFromDate);
                        textViewFromDate.setText(AppUtils.ToDateFormat(year, month, dayOfMonth));
                        fromDate = AppUtils.ToDateFormatInDB(year, month, dayOfMonth);
                        break;
                    case 2:
                        TextView textViewToDate = (TextView) getView().findViewById(R.id.textViewToDate);
                        textViewToDate.setText(AppUtils.ToDateFormat(year, month, dayOfMonth));
                        toDate = AppUtils.ToDateFormatInDB(year, month, dayOfMonth);
                        break;
                }
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                listener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.selectedFilterBy = this.filterBy[position];

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
