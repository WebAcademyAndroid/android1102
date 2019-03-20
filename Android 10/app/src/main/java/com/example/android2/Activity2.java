package com.example.android2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);


        TextView textView = findViewById(R.id.textView);
        textView.setText(text);


        Student student =  intent.getParcelableExtra(MainActivity.EXTRA_STUDENT);

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText(student.toString());
    }
}
