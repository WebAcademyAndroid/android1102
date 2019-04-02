package com.example.android8;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class DataBaseService extends IntentService {

    private static final String ACTION_GET_STUDENTS = "com.example.android8.action.GET_STUDENTS";
    private static final String ACTION_SAVE_STUDENT = "com.example.android8.action.SAVE_STUDENT";

    public static final String EXTRA_STUDENT = "com.example.android8.extra.STUDENT";
    public static final String EXTRA_ID = "com.example.android8.extra.ID";
    public static final String EXTRA_STUDENTS = "com.example.android8.extra.STUDENTS";
    public static final String EXTRA_PENDING_INTENT = "com.example.android8.extra.PENDING_INTENT";

    public static final int REQUEST_CODE_GET_STUDENTS = 10;
    public static final int REQUEST_CODE_SAVE_STUDENT = 11;

    public DataBaseService() {
        super("DataBaseService");
    }

    public static void getStudents(AppCompatActivity activity) {
        Intent intent = new Intent(activity, DataBaseService.class);
        intent.setAction(ACTION_GET_STUDENTS);

        PendingIntent pendingIntent = activity.createPendingResult(REQUEST_CODE_GET_STUDENTS, intent, 0);
        intent.putExtra(EXTRA_PENDING_INTENT, pendingIntent);

        activity.startService(intent);
    }

    public static void saveStudent( Student student, AppCompatActivity activity) {
        Intent intent = new Intent(activity, DataBaseService.class);
        intent.setAction(ACTION_SAVE_STUDENT);

        PendingIntent pendingIntent = activity.createPendingResult(REQUEST_CODE_SAVE_STUDENT, intent, 0);
        intent.putExtra(EXTRA_PENDING_INTENT, pendingIntent);

        intent.putExtra(EXTRA_STUDENT, student);

        activity.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            PendingIntent pendingIntent = intent.getParcelableExtra(EXTRA_PENDING_INTENT);
            Intent result = new Intent();
            DataBaseHelper helper = new DataBaseHelper(this);


            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            final String action = intent.getAction();
            if (ACTION_GET_STUDENTS.equals(action)) {
                ArrayList<Student> students = helper.getStudents();
                result.putExtra(EXTRA_STUDENTS, students);
            } else if (ACTION_SAVE_STUDENT.equals(action)) {
                Student student = intent.getParcelableExtra(EXTRA_STUDENT);
                long id = helper.insert(student);
                result.putExtra(EXTRA_ID, id);
            }

            try {
                pendingIntent.send(this, Activity.RESULT_OK, result);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }
}
