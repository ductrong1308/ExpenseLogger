package com.example.expenselogger.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.expenselogger.R;
import com.example.expenselogger.SharedPrefHandler;
import com.example.expenselogger.db.DBoperationSupport;
import com.example.expenselogger.utils.AppUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    SQLiteDatabase wdb;

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

        String userIdInSharedPref = SharedPrefHandler.getData("USERID", getActivity());
        int userId = 1;

        if(userIdInSharedPref != null && userIdInSharedPref.length() != 0){
            userId = Integer.parseInt(userIdInSharedPref);
        }
        ArrayList<String> categories =
                DBoperationSupport.GetCategoriesByUserId(wdb, userId);

        Spinner spinner = (Spinner) getView().findViewById(R.id.spinnerCategory);
        Button buttonDatePicker = (Button) getView().findViewById(R.id.buttonDatePicker);
        TextView textViewDate = (TextView) getView().findViewById(R.id.textViewDate);

        textViewDate.setText(new SimpleDateFormat(AppUtils.DateFormat).format(new Date()));

        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories );
        spinner.setAdapter(spinnerArrayAdapter);

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private  void showDatePickerDialog(){
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                TextView textViewDate = (TextView) getView().findViewById(R.id.textViewDate);
                textViewDate.setText(AppUtils.ToDateFormat(year, month, dayOfMonth));
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                listener,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
