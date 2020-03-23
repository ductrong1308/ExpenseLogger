package com.example.expenselogger.classes;

public class MonthlyExpenseData {
    private double expense;
    private String month;

    public MonthlyExpenseData(String month, double expense){
        this.expense = expense;
        this.month = month;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
