package com.example.android12;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class StudentView extends RelativeLayout {
    private Student mStudent;
    private RequiredEditText mEditTextFirstName, mEditTextLastName, mEditTextAge;

    public StudentView(Context context) {
        super(context);
        init(context);
    }

    public StudentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_view, this);

        mEditTextFirstName = view.findViewById(R.id.editTextFirstName);
        mEditTextLastName = view.findViewById(R.id.editTexttLastName);
        mEditTextAge = view.findViewById(R.id.editTextAge);
    }

    public void clear() {
        mStudent = null;

        mEditTextFirstName.setText(null);
        mEditTextLastName.setText(null);
        mEditTextAge.setText(null);

        mEditTextFirstName.setError(null);
        mEditTextLastName.setError(null);
        mEditTextAge.setError(null);
    }

    public void set(Student student) {
        clear();
        mStudent = student;
        if (mStudent != null) {
            mEditTextFirstName.setText(mStudent.firstName);
            mEditTextLastName.setText(mStudent.lastName);
            mEditTextAge.setText(String.valueOf(mStudent.age));
        }
    }

    public Student get() {
        if (mStudent != null) {
            mStudent.firstName = mEditTextFirstName.getText().toString();
            mStudent.lastName = mEditTextLastName.getText().toString();
            mStudent.age = Integer.parseInt(mEditTextAge.getText().toString());
        }

        return mStudent;
    }

    public boolean validate() {
        return mEditTextFirstName.validate() & mEditTextLastName.validate() & mEditTextAge.validate();
    }
}
