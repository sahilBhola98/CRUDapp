package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudapp.database.StudentForm;
import com.example.crudapp.database.StudentReaderHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickListener(View view) {
        studentPortal();
    }

    private void studentPortal() {
        Button btn = findViewById(R.id.buttonLogin);
        EditText nameText = findViewById(R.id.nameText);
        String name = nameText.getText().toString();
        Toast.makeText(this, "Welcome Admin" + name + "You can access portal", Toast.LENGTH_SHORT).show();
        Intent sIntent = new Intent(MainActivity.this, StudentForm.class);
        startActivity(sIntent);
    }

}