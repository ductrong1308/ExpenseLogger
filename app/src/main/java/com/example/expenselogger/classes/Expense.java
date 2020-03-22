package com.example.expenselogger.classes;

import com.example.expenselogger.utils.AppUtils;

public class Expense {
    private int id;
    private String category;
    private String createdDate;
    private double amount;

    public Expense(int id, String category, String createdDate, double amount) {
        this.id = id;
        this.category = category;
        this.createdDate = createdDate;
        this.amount = amount;
    }

    public int GetId() {
        return this.id;
    }

    public double GetAmount() {
        return this.amount;
    }

    public String GetCreatedDate() {
        return this.createdDate;
    }

    public String GetCategory() {
        return this.category;
    }

    public String ToString() {
        return String.format("%-16s | %10s | %s", this.category,
                AppUtils.FormatCurrency(null, this.amount), this.createdDate.substring(0, 10));
    }
}
