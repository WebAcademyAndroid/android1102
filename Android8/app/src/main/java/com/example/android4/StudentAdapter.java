package com.example.android4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {

    private ArrayList<Student> mStudents;
    private int mResource;
    private LayoutInflater mInflater;

    public StudentAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);

        mStudents = objects;
        mResource = resource;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mResource, null);

        Student student = mStudents.get(position);

        ((TextView) convertView.findViewById(R.id.textViewFirstName)).setText(student.firstName);
        ((TextView) convertView.findViewById(R.id.textViewLastName)).setText(student.lastName);
        ((TextView) convertView.findViewById(R.id.textViewAge)).setText(String.valueOf(student.age));

        return convertView;
    }
}
