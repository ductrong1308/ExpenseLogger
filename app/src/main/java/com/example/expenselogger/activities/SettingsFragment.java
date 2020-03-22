package com.example.expenselogger.activities;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expenselogger.R;
import com.example.expenselogger.SharedPrefHandler;
import com.example.expenselogger.classes.Category;
import com.example.expenselogger.db.DBoperationSupport;
import com.example.expenselogger.utils.AppMessages;
import com.example.expenselogger.utils.AppUtils;
import com.example.expenselogger.utils.CategoryCustomAdapter;
import com.example.expenselogger.utils.SwipeToDelete;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsFragment extends Fragment {

    SQLiteDatabase wdb;
    String[] currencies;
    String selectedCurrency;
    int userId = 1;
    boolean isFirstVisit = true;
    ArrayAdapter<String> spinnerArrayAdapter;

    private RecyclerView recyclerView;
    private ArrayList<Category> categoriesDataset;
    private CategoryCustomAdapter categoryCustomAdapter;
    private LinearLayout theMainLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        wdb = DBoperationSupport.getWritable(getActivity());
        theMainLayout = getView().findViewById(R.id.theMainSettingLayout);

        Spinner spinnerCurrencies = (Spinner) getView().findViewById(R.id.spinnerCurrencies);
        Button buttonAdd = (Button)getView().findViewById(R.id.buttonAdd);

        this.currencies = getResources().getStringArray(R.array.currencies);
        this.userId = AppUtils.GetCurrentLoggedInUserId(getActivity());

        spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, currencies);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCurrencies.setAdapter(spinnerArrayAdapter);

        String userSelectedCurrency = DBoperationSupport.GetUserCurrency(userId);
        spinnerCurrencies.setSelection(Arrays.asList(currencies).indexOf(userSelectedCurrency));
        this.selectedCurrency = userSelectedCurrency;

        // RecyclerView
        recyclerView =(RecyclerView)getView().findViewById(R.id.recyclerViewCategories);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoriesDataset = DBoperationSupport.GetAllExpenseCategoriesByUser(userId);

        // specify an adapter (see also next example)
        categoryCustomAdapter = new CategoryCustomAdapter(categoriesDataset);
        recyclerView.setAdapter(categoryCustomAdapter);

        enableSwipeToDeleteAndUndo();

        spinnerCurrencies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!isFirstVisit){
                    try{
                        selectedCurrency = currencies[position];
                        DBoperationSupport.UpdateUserCurrency(userId, selectedCurrency);
                        SharedPrefHandler.storeData("CURRENCY", selectedCurrency, getActivity());
                        AppUtils.ShowMessage(getActivity(), AppMessages.CurrencySettingSaved);
                    }
                    catch (Exception ex){
                        AppUtils.ShowErrorMessage(getActivity(), AppMessages.AnErrorHasOccurred);
                    }
                }
                else {
                    isFirstVisit = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeToDelete swipeToDelete =
                new SwipeToDelete(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, getContext()) {

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
                Log.d("MainActivity","SwipeToDelete");

                final int position = viewHolder.getAdapterPosition();
                final Category itemToBeDeleted = categoryCustomAdapter.getData(position);

                categoryCustomAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(theMainLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);

                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        categoryCustomAdapter.restoreItem(itemToBeDeleted, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDelete);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }
}
