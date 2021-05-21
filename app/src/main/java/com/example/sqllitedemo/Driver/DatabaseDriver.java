package com.example.sqllitedemo.Driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqllitedemo.Modal.Student;
import com.example.sqllitedemo.SQL.MySQLHelper;

import java.util.ArrayList;

public class DatabaseDriver
{
    private MySQLHelper mySQLHelper;
    private Context context;
    SQLiteDatabase database;

    public DatabaseDriver(Context context) {
        this.context = context;
        mySQLHelper = new MySQLHelper(context);
        database = mySQLHelper.getWritableDatabase();
    }

    public ArrayList<Student> getAllProducts()
    {
        ArrayList<Student> students = new ArrayList<>();

        //database.execSQL("SELECT * FROM student;");

        String[] columns = {"firstName", "lastName"};
        //String[] whereArgs = {"marks", "35"};
        Cursor cursor = database.query("student", columns, null, null, null, null, null);
        //similar to query like "SELECT firstName, lastName FROM student WHERE marks > 35"

        if(cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();       //cursor points to first record
            do
            {
                //do some processing
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int marks = cursor.getInt(cursor.getColumnIndex("marks"));
                String firstName = cursor.getString(cursor.getColumnIndex("firstName"));
                String lastName = cursor.getString(cursor.getColumnIndex("lastName"));

                Student student = new Student();
                student.setId(id);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setMarks(marks);

                students.add(student);

            }while(cursor.moveToNext());
        }

        return students;
    }

    public void insertStudent(Student s)
    {
        ContentValues values = new ContentValues();
        values.put("firstName", s.getFirstName());
        values.put("lastName", s.getLastName());
        values.put("marks", s.getMarks());

        long id = database.insert("student", null, values);
    }
}
