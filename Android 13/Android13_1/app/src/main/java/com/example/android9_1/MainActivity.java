package com.example.android9_1;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Student>> {

    private ListView mListView;
    private ArrayList<Student> mStudents = new ArrayList<>();
    private ArrayAdapter<Student> mAdapter;

    private SaveTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mStudents);
        mListView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(0, null, this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask = new SaveTask();
                mTask.execute(new Student("Petro","Petrov",44));
            }
        });


        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService myService = ((MyService.MyBinder)service).getService();
                myService.saveStudent(new Student(), new MyService.IStudent() {
                    @Override
                    public void onSave(Boolean success) {

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mTask != null){
            mTask.cancel(true);
        }
    }

    @NonNull
    @Override
    public Loader<ArrayList<Student>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new StudentLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Student>> loader, ArrayList<Student> students) {
        mStudents.clear();
        mStudents.addAll(students);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Student>> loader) {

    }


    public class SaveTask extends AsyncTask<Student, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Student... students) {
            Student student = students[0];
            ContentValues values = new ContentValues();
            values.put(Student.COLUMN_FIRST_NAME, student.firstName);
            values.put(Student.COLUMN_LAST_NAME, student.lastName);
            values.put(Student.COLUMN_AGE, student.age);

            try {
                Uri uri = Uri.parse("content://com.example.android9/students");
                Uri res = getContentResolver().insert(uri, values);

                return res != null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);

            if(success){
                getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
            }else{
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        }
    }
}
