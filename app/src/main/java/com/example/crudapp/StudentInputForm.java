package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.crudapp.R;
import com.example.crudapp.database.SAO;
import com.example.crudapp.database.StudentReaderHelper;

public class StudentInputForm extends AppCompatActivity {
    SAO sao=new SAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_input_form);
        countRecords();

    }
    public void countRecords(){
        int recordCount = new StudentReaderHelper(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount+ "record found");
    }
}