package com.example.android11;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RequiredEditText mEditText1, mEditText2, mEditText3;
    private StudentView mStudentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText1 = findViewById(R.id.editTextFirstName);
        mEditText2 = findViewById(R.id.editTexttLastName);
        mEditText3 = findViewById(R.id.editTextAge);
        mStudentView = findViewById(R.id.studentView);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText1.validate() & mEditText2.validate() & mEditText3.validate()) {
                    Toast.makeText(MainActivity.this, "Validated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "NOT Validated !!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStudentView.set(new Student("Ivan", "Ivanov", 33));
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStudentView.validate()) {
                    Student student = mStudentView.get();
                    Toast.makeText(MainActivity.this, student.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        if(savedInstanceState != null){
            if(savedInstanceState.containsKey("Student")){
                Student student = savedInstanceState.getParcelable("Student");
                mStudentView.set(student);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Student student = mStudentView.get();
        if(student != null){
            outState.putParcelable("Student", student);
        }
        super.onSaveInstanceState(outState);
    }
}
