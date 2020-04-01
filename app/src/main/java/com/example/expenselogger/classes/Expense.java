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
        return this.createdDate.substring(0, 10);
    }

    public String GetCategory() {
        return this.category;
    }

    public String ToString() {
        String date = this.createdDate.substring(0, 10);
        String[] dateItem = date.split("-");
        String dateFormat = AppUtils.ToDateFormat(
                Integer.parseInt(dateItem[0]), Integer.parseInt(dateItem[1]) - 1, Integer.parseInt(dateItem[2]));

        return String.format("%17s %15s", dateFormat, AppUtils.FormatCurrency(null, this.amount));
    }
}
