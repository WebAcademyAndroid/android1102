package com.example.android9;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

public class StudentLoader extends AsyncTaskLoader<ArrayList<Student>> {
    private DataBaseHelper mHelper;

    public StudentLoader(@NonNull Context context) {
        super(context);
        mHelper = new DataBaseHelper(context);
    }

    @Nullable
    @Override
    public ArrayList<Student> loadInBackground() {
        return mHelper.getStudents();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
