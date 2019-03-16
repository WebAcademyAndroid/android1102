package com.example.android5;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseExpandableListAdapter {

    private ArrayList<Group> mGroups;
    private LayoutInflater mInflater;

    public StudentAdapter(Context context, ArrayList<Group> groups) {
        mGroups = groups;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).students != null ? mGroups.get(groupPosition).students.length : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).students[childPosition];
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.group, null);
        Group group = (Group) getGroup(groupPosition);

        ((TextView) convertView.findViewById(R.id.textViewGroupName)).setText(group.name);

        View view = convertView.findViewById(R.id.indicator);
        if (group.students.length == 0) {
            //view.setBackgroundColor(Color.GRAY);
            view.setVisibility(View.INVISIBLE);
        } else if (isExpanded) {
            view.setBackgroundColor(Color.RED);
        } else {
            view.setBackgroundColor(Color.GREEN);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.student, null);
        Student student = (Student) getChild(groupPosition, childPosition);

        ((TextView) convertView.findViewById(R.id.textViewFirstName)).setText(student.firstName);
        ((TextView) convertView.findViewById(R.id.textViewLastName)).setText(student.lastName);
        ((TextView) convertView.findViewById(R.id.textViewAge)).setText(String.valueOf(student.age));

        return convertView;
    }


    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
