package com.example.expenselogger.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.expenselogger.R;
import com.example.expenselogger.classes.Expense;
import com.example.expenselogger.db.DBoperationSupport;
import com.example.expenselogger.utils.AppUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.function.DoubleBinaryOperator;

public class EditExpenseDialog extends AppCompatDialogFragment {

    Expense expenseDetails;
    String createdDate;
    String selectedCategory;
    SQLiteDatabase wdb;
    ArrayList<String> categories;
    int userId;

    Spinner spinner;
    Button buttonDatePicker;
    EditText textBoxAmount;
    TextView textViewDate;
    boolean isFirstVisit = true;

    View view;
    Fragment parentView;
    EditExpenseDialogListener listener;

    public EditExpenseDialog(int userId, Expense expenseDetails, Fragment parentView) {
        this.expenseDetails = expenseDetails;
        this.parentView = parentView;
        this.userId = userId;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.layout_edit_expense, null);

        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = getActivity().getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);

        view.setMinimumHeight((int) (displayRectangle.height() * 0.5f));

        builder.setView(view)
                .setTitle("")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing, just close the dialog.
                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBoperationSupport.UpdateExpense(
                                expenseDetails.GetId(), selectedCategory,
                                Double.parseDouble(textBoxAmount.getText().toString()),
                                createdDate);
                        listener.onActionDone();
                    }
                })
                .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBoperationSupport.DeleteExpense(expenseDetails.GetId());
                        listener.onActionDone();
                    }
                });

        return builder.create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wdb = DBoperationSupport.getWritable(getActivity());

        spinner = (Spinner) view.findViewById(R.id.spinnerCategoryEditDialog);
        buttonDatePicker = (Button) view.findViewById(R.id.buttonDatePickerEditDialog);
        textBoxAmount = (EditText) view.findViewById(R.id.textboxAmountEditDialog);
        textViewDate = (TextView) view.findViewById(R.id.textViewDateEditDialog);

        categories = DBoperationSupport.GetCategoriesByUserId(userId);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, categories);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        textBoxAmount.setText(Double.toString(expenseDetails.GetAmount()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstVisit) {
                    String userSelectedCategory = expenseDetails.GetCategory();
                    spinner.setSelection(categories.indexOf(userSelectedCategory));
                    selectedCategory = userSelectedCategory;
                    isFirstVisit = false;
                } else {
                    selectedCategory = categories.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String[] dateInString = expenseDetails.GetCreatedDate().split("-");
        textViewDate.setText(AppUtils.ToDateFormat(
                Integer.parseInt(dateInString[0]),
                Integer.parseInt(dateInString[1]),
                Integer.parseInt(dateInString[2])));
        createdDate = expenseDetails.GetCreatedDate();

        buttonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (EditExpenseDialogListener) parentView;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public interface EditExpenseDialogListener {
        void onActionDone();
    }
}




















