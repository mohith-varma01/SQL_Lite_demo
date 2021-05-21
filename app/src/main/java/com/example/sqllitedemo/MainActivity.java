package com.example.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.sqllitedemo.Driver.DatabaseDriver;
import com.example.sqllitedemo.Modal.Student;
import com.example.sqllitedemo.SQL.MySQLHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseDriver databaseDriver;

    private EditText firstName;
    private EditText lastName;
    private EditText marks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseDriver = new DatabaseDriver(this);
        bindViews();
    }

    public void OnClickAddStudent(View view)
    {
        Student student = new Student();
        student.setFirstName(firstName.getText().toString());
        student.setLastName(lastName.getText().toString());
        student.setMarks(Integer.parseInt(marks.getText().toString()));

        databaseDriver.insertStudent(student);

        resetFields();
    }

    public void OnClickPrint(View view)
    {
        ArrayList<Student> students = databaseDriver.getAllProducts();

        System.out.println("Your database has " + students.size() + " students");
        for(int i = 0; i < students.size();i++)
        {
            System.out.println(students.get(i).getFirstName() + " " + students.get(i).getLastName());
        }
    }

    private void bindViews()
    {
        firstName = findViewById(R.id.editText_firstName);
        lastName = findViewById(R.id.editText_lastName);
        marks = findViewById(R.id.editText_marks);
    }

    private void resetFields()
    {
        firstName.setText("");
        lastName.setText("");
        marks.setText("");
    }
}