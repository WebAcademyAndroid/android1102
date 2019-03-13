package com.example.android4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class Activity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        Spinner spinner = findViewById(R.id.spinner);

        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            items.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.student,
                R.id.textViewFirstName,
                items);

        //adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinner.setAdapter(adapter);
    }
}
