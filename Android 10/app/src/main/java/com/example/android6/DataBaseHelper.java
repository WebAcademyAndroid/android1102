package com.example.android6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, "MyDB1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Student.TABLE_NAME + " ("
                + Student.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Student.COLUMN_FIRST_NAME + " TEXT NOT NULL,"
                + Student.COLUMN_LAST_NAME + " TEXT NOT NULL,"
                + Student.COLUMN_AGE + " INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(Student student) {
        long id = 0;
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put(Student.COLUMN_FIRST_NAME, student.firstName);
            values.put(Student.COLUMN_LAST_NAME, student.lastName);
            values.put(Student.COLUMN_AGE, student.age);

            id = db.insert(Student.TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public boolean update(Student student) {
        boolean updated = false;
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put(Student.COLUMN_FIRST_NAME, student.firstName);
            values.put(Student.COLUMN_LAST_NAME, student.lastName);
            values.put(Student.COLUMN_AGE, student.age);

            updated = db.update(Student.TABLE_NAME, values, Student.COLUMN_ID + "=" + student.id, null) == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }

    public ArrayList<Student> getStudents() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;
        ArrayList<Student> students = new ArrayList<>();

        try {
            cursor = db.query(Student.TABLE_NAME, null, null, null, null, null, null);
            if(cursor.moveToFirst()){
                while (!cursor.isAfterLast()){
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
}
