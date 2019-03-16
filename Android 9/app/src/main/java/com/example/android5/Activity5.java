package com.example.android5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity5 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Student> mStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mStudents = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            mStudents.add(new Student("Ivan " + i, "Ivanov " + i, i));
        }

        final RecyclerAdapter adapter = new RecyclerAdapter(this, mStudents);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new RecyclerAdapter.ClickListener() {
            @Override
            public void onClick(Student student) {
                Toast.makeText(Activity5.this, student.toString(), Toast.LENGTH_LONG).show();
            }
        });


        ItemTouchHelper.SimpleCallback itemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();

                Student student = mStudents.get(fromPos);
                mStudents.remove(fromPos);
                mStudents.add(toPos, student);

                adapter.notifyItemMoved(fromPos, toPos);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                mStudents.remove(fromPos);
                adapter.notifyItemRemoved(fromPos);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
