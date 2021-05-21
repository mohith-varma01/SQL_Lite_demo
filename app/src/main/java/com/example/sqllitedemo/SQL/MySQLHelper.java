package com.example.sqllitedemo.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqllitedemo.Schema.StudentSchema;

public class MySQLHelper extends SQLiteOpenHelper
{
    public MySQLHelper(Context context)
    {
        super(context, "student", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)     //to create database for the very first time
    {
        db.execSQL("CREATE TABLE " + StudentSchema.Student._tableName + " (" + StudentSchema.Student._id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + StudentSchema.Student._firstName + " VARCHAR(50), lastName VARCHAR(50), marks INT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
