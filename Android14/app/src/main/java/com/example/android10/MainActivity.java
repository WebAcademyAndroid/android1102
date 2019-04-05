package com.example.android10;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Student>> {

    private SaveTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mTask != null) {
            mTask.cancel(true);
        }
    }

    public void OnClick(View v) {
        editStudent(new Student());
    }

    @NonNull
    @Override
    public Loader<ArrayList<Student>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new StudentLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Student>> loader, ArrayList<Student> students) {
        Fragment1 fragment1 = Fragment1.newInstance(students);
        fragment1.setActionListener(new Fragment1.ActionListener() {
            @Override
            public void onClick(Student student) {
                editStudent(student);
            }

            @Override
            public void onLongClick(Student student) {

            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment1).commit();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Student>> loader) {

    }

    private void editStudent(Student student) {
        Fragment2 fragment2 = Fragment2.newInstance(student);
        fragment2.setActionListener(new Fragment2.ActionListener() {
            @Override
            public void save(Student student) {
                mTask = new SaveTask();
                mTask.execute(student);
            }

            @Override
            public void cancel() {
                //getSupportLoaderManager().initLoader(0, null, MainActivity.this);
            }
        });
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, fragment2).commit();
        fragment2.show(getSupportFragmentManager(), "dialog");
    }


    public class SaveTask extends AsyncTask<Student, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Student... students) {
            DataBaseHelper helper = new DataBaseHelper(MainActivity.this);
            Student student = students[0];
            return helper.save(student);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
        }
    }
}
