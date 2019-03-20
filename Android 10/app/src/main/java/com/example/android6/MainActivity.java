package com.example.android6;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View v) {

        DataBaseHelper helper = new DataBaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.button: {

                long id = helper.insert(new Student("Ivan","Ivanov",44));
                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_LONG).show();
            }
            break;
            case R.id.button2: {
                ContentValues values = new ContentValues();
                values.put(Student.COLUMN_AGE, 20);

                int count = db.update(Student.TABLE_NAME, values, Student.COLUMN_ID + "=1", null);
                Toast.makeText(this, String.valueOf(count), Toast.LENGTH_LONG).show();
            }
            break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
            case R.id.button9:
                break;
            case R.id.button10:
                break;
        }
    }
}
