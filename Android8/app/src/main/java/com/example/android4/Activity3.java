package com.example.android4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<Student> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        mListView = findViewById(R.id.listView);


        for (int i = 0; i < 50; i++) {
            items.add(new Student("Ivan" + i, "Ivanov" + i, i));
        }

        /*ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                items);*/

        final StudentAdapter adapter =
                new StudentAdapter(this, R.layout.student, items);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = items.get(position);
                Toast.makeText(Activity3.this, student.toString(), Toast.LENGTH_LONG).show();
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
