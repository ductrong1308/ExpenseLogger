package com.example.expenselogger.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expenselogger.R;
import com.example.expenselogger.SharedPrefHandler;
import com.example.expenselogger.db.DBoperationSupport;
import com.example.expenselogger.utils.AppMessages;
import com.example.expenselogger.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment {

    SQLiteDatabase wdb;
    ArrayList<String> categories;
    String selectedCategory;
    String createdDate;
    int userId = 1;
    ArrayAdapter<String> spinnerArrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        wdb = DBoperationSupport.getWritable(getActivity());

        this.userId = AppUtils.GetCurrentLoggedInUserId(getActivity());
        categories = DBoperationSupport.GetCategoriesByUserId(userId);
        selectedCategory = categories.get(0);

        final Spinner spinner = (Spinner) getView().findViewById(R.id.spinnerCategory);
        Button buttonDatePicker = (Button) getView().findViewById(R.id.buttonDatePicker);
        Button buttonAdd = (Button) getView().findViewById(R.id.buttonAdd);
        final EditText textBoxAmount = (EditText) getView().findViewById(R.id.textboxAmount);
        final TextView textViewDate = (TextView) getView().findViewById(R.id.textViewDate);

        Date today = new Date();
        textViewDate.setText(new SimpleDateFormat(AppUtils.DateFormat).format(today));
        this.createdDate = AppUtils.ToDateFormatInDB(today);

        spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, categories);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = categories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (textBoxAmount.getText().length() == 0) {
                        AppUtils.ShowErrorMessage(getActivity(), AppMessages.AmountIsRequired);
                    } else {
                        String insertNewExpenseQuery =
                                "INSERT INTO Expenses (id, createdDate, category, amount, userId) VALUES (null, '"
                                        + createdDate + "', '" + selectedCategory + "', "
                                        + textBoxAmount.getText().toString() + ", " + userId + ")";
                        wdb.execSQL(insertNewExpenseQuery);

                        AppUtils.ShowMessage(getActivity(), AppMessages.ExpenseAdded);
                        ClearData(textBoxAmount, textViewDate, spinner);
                    }
                } catch (Exception ex) {
                    AppUtils.ShowErrorMessage(getActivity(), AppMessages.AnErrorHasOccurred);
                    ex.printStackTrace();
                }
            }
        });
    }

    private void ClearData(EditText textBoxAmount, TextView textViewDate, Spinner spinner){
        Date today = new Date();
        textBoxAmount.setText("");
        textViewDate.setText(new SimpleDateFormat(AppUtils.DateFormat).format(today));
        spinner.setSelection(0);
        this.createdDate = AppUtils.ToDateFormatInDB(today);

        // Hide soft keyboard.
        View view = getView();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                TextView textViewDate = (TextView) getView().findViewById(R.id.textViewDate);
                textViewDate.setText(AppUtils.ToDateFormat(year, month, dayOfMonth));
                createdDate = AppUtils.ToDateFormatInDB(year, month, dayOfMonth);
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
}
