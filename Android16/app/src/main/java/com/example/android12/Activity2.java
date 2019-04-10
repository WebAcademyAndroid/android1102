package com.example.android12;

import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private StudentView mStudentView;
    private DataBaseHelper mHelper = new DataBaseHelper(this);
    private Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mStudentView = findViewById(R.id.studentView);

        long id = getIntent().getExtras().getLong("id");
        mStudent = mHelper.getStudent(id);

        mStudentView.set(mStudent);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStudentView.validate()) {
                    mStudent = mStudentView.get();

                    mHelper.save(mStudent);

                    ArrayList<Widget> widgets = mHelper.getWidgets(mStudent.id);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(Activity2.this);
                    for(Widget w: widgets){
                        AppWidget2.updateAppWidget(Activity2.this, appWidgetManager, w.idWidget);
                    }

                    finish();
                }
            }
        });
    }
}
