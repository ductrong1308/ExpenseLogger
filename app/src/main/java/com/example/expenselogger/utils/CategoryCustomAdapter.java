package com.example.expenselogger.utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.expenselogger.R;
import com.example.expenselogger.classes.Category;

import java.util.ArrayList;

public class CategoryCustomAdapter extends RecyclerView.Adapter<CategoryCustomAdapter.MyViewHolder> {

    private ArrayList<Category> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CategoryCustomAdapter(ArrayList<Category> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.categories_item_layout, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(listItem);
        Log.d("MyAdapter", "onCreateViewHolder.");
        return viewHolder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tView.setText(mDataset.get(position).getCategoryName());

        // clickable by row
        holder.theLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // =========================
    public void addItem(Category data, int position) {
        mDataset.add(position, data);
        this.notifyDataSetChanged();
    }

    public void restoreItem(Category data, int position) {
        addItem(data, position);
    }

    public void removeItem(int position) {
        mDataset.remove(position);
        this.notifyDataSetChanged();
    }

    public Category getData(int position) {
        return mDataset.get(position);
    }

    // ===============================================================

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tView;
        public LinearLayout theLayout;

        public MyViewHolder(View v) {
            super(v);
            Log.d("MyViewHolder", "Constructor");
            tView = (TextView) v.findViewById(R.id.textViewCategoryItem);
            theLayout = (LinearLayout) v.findViewById(R.id.categoryItemLinearLayout);
        }
    }
}
