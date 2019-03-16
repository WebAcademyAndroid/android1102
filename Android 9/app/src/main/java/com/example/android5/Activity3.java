package com.example.android5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Activity3 extends AppCompatActivity {

    private ExpandableListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        mListView = findViewById(R.id.listView);

        ArrayList<HashMap<String, String>> groups = new ArrayList<>();
        ArrayList<ArrayList<HashMap<String, String>>> children = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            HashMap<String, String> group = new HashMap<>();
            group.put("Name", "Group " + i);
            groups.add(group);

            ArrayList<HashMap<String, String>> groupChildren = new ArrayList<>();
            for (int j = 0; j < random.nextInt(10); j++) {
                HashMap<String, String> child = new HashMap<>();
                child.put("Name", "Ivan " + j);
                groupChildren.add(child);
            }
            children.add(groupChildren);
        }

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groups,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"Name"},
                new int[]{android.R.id.text1},
                children,
                android.R.layout.simple_list_item_1,
                new String[]{"Name"},
                new int[]{android.R.id.text1});
        mListView.setAdapter(adapter);
    }
}
