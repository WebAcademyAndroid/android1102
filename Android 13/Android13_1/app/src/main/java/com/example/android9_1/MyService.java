package com.example.android9_1;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
       return new MyBinder();
    }


    public class MyBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    public void saveStudent(Student student, IStudent listener){
        new SaveTask(listener).execute(student);
    }

    public interface IStudent{
        void onSave(Boolean success);
    }

    public class SaveTask extends AsyncTask<Student, Void, Boolean> {

        private IStudent mListener;

        public SaveTask(IStudent listener){
            mListener = listener;
        }

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

            if(mListener != null){
                mListener.onSave(success);
            }
        }
    }
}
