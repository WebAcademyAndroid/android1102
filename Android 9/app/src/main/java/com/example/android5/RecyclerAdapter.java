package com.example.android5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.StudentViewHolder> {

    private ArrayList<Student> mStudents;
    private LayoutInflater mInflater;
    private int mPosition = -1;

    public RecyclerAdapter(Context context, ArrayList<Student> students) {
        mStudents = students;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.student, viewGroup, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, final int i) {
        final Student student = mStudents.get(i);
        studentViewHolder.set(student);

        RadioButton radioButton = studentViewHolder.itemView.findViewById(R.id.radioButton);
        if(i == mPosition){
            radioButton.setChecked(true);
        }else{
            radioButton.setChecked(false);
        }

        studentViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(student);
                }
            }
        });

        studentViewHolder.itemView.findViewById(R.id.radioButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prev = mPosition;
                mPosition = i;

                if(prev >= 0){
                    notifyItemChanged(prev);
                }
            }
        });
    }

    public int getCheckedPosition(){
        return mPosition;
    }

    public interface ClickListener {
        public void onClick(Student student);
    }

    private ClickListener mListener;

    public void setOnClickListener(ClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewFirstName, mTextViewLastName, mTextViewAge;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewFirstName = itemView.findViewById(R.id.textViewFirstName);
            mTextViewLastName = itemView.findViewById(R.id.textViewLastName);
            mTextViewAge = itemView.findViewById(R.id.textViewAge);
        }

        public void set(Student student) {
            mTextViewFirstName.setText(student.firstName);
            mTextViewLastName.setText(student.lastName);
            mTextViewAge.setText(String.valueOf(student.age));
        }
    }


}
