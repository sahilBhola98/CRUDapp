package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WhoWroteIt extends AppCompatActivity {
    EditText editTextView;
    TextView authorTextView , titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_wrote_it);
        editTextView=findViewById(R.id.bookinput);
        authorTextView = findViewById(R.id.authorText);
        titleTextView = findViewById(R.id.titleText);
    }
    public void searchBooks(View view){
        String queryString = editTextView.getText().toString();
        new FetchBook(titleTextView,authorTextView).execute(queryString);
    }
}