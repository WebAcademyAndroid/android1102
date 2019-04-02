package com.example.android9_1;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

public class StudentLoader extends AsyncTaskLoader<ArrayList<Student>> {
    private Context mContext;

    public StudentLoader(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    @Nullable
    @Override
    public ArrayList<Student> loadInBackground() {
        ArrayList<Student> students = new ArrayList<>();
        Cursor cursor = null;
        try {
            Uri uri = Uri.parse("content://com.example.android9/students");
            cursor = mContext.getContentResolver().query(uri, null, null, null, null);
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    Student student = new Student();
                    student.id = cursor.getLong(cursor.getColumnIndex(Student.COLUMN_ID));
                    student.firstName = cursor.getString(cursor.getColumnIndex(Student.COLUMN_FIRST_NAME));
                    student.lastName = cursor.getString(cursor.getColumnIndex(Student.COLUMN_LAST_NAME));
                    student.age = cursor.getInt(cursor.getColumnIndex(Student.COLUMN_AGE));

                    students.add(student);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return students;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
