package com.example.android4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity4 extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        mListView = findViewById(R.id.listView);
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            items.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                items);
        mListView.setAdapter(adapter);

        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = mListView.getCheckedItemPosition();
                //Toast.makeText(Activity4.this, String.valueOf(position), Toast.LENGTH_LONG).show();

                StringBuilder sb = new StringBuilder();
                SparseBooleanArray arr = mListView.getCheckedItemPositions();
                for (int i = 0; i < arr.size(); i++) {
                    if (arr.get(i)) {
                        sb.append(String.valueOf(i));
                        sb.append(" ");
                    }
                }

                Toast.makeText(Activity4.this, sb, Toast.LENGTH_LONG).show();
            }
        });
    }
}
