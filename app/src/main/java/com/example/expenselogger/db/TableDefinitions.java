package com.example.expenselogger.db;

public class TableDefinitions {

    public static final String SQL_CREATE_USERS =
            "CREATE TABLE Users (id integer primary key, firstName text, lastName text, " +
                    "emailAddress text, password text )";
    public static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS Users ";

    public static final String SQL_CREATE_EXPENSES =
            "CREATE TABLE Expenses (id integer primary key, createdDate text, spendOn text,  userId integer, " +
                    "FOREIGN KEY(userId) REFERENCES Users(id) on delete cascade)";
    public static final String SQL_DELETE_EXPENSES =
            "DROP TABLE IF EXISTS Expenses ";

    public static final String SQL_CREATE_CATEGORIES =
            "CREATE TABLE Categories (id integer primary key, categoryName text, userId integer, " +
                    "FOREIGN KEY(userId) REFERENCES Users(id) on delete cascade) ";
    public static final String SQL_DELETE_CATEGORIES =
            "DROP TABLE IF EXISTS Categories ";

    public static final String SQL_CREATE_SETTINGS =
            "CREATE TABLE Settings (id integer primary key, name text, value text, userId integer, " +
                    "FOREIGN KEY(userId) REFERENCES Users(id) on delete cascade) ";
    public static final String SQL_DELETE_SETTINGS =
            "DROP TABLE IF EXISTS Settings ";
}