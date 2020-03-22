package com.example.expenselogger.utils;

import android.content.Context;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public abstract class SwipeToDelete extends ItemTouchHelper.SimpleCallback{
    private Context mContext;
    private int swipeDirs;

    public SwipeToDelete(int dragDirs, int swipeDirs,Context context) {
        super(dragDirs, swipeDirs);
        this.swipeDirs = swipeDirs;
        this.mContext = context;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }
}
