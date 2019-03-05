package com.example.android2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.android2.extra.TEXT";
    public static final String EXTRA_STUDENT = "com.example.android2.extra.STUDENT";
    private static final int REQUEST_CODE_ACTIVITY_4 = 1;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.editText);
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra(EXTRA_TEXT, "Hello activity!");

                Student student = new Student("Ivan", "Ivanov", 22);
                intent.putExtra(EXTRA_STUDENT, student);

                startActivity(intent);
            }
            break;
            case R.id.button2: {
                Intent intent = new Intent(this, Activity3.class);
                startActivity(intent);
            }
            break;
            case R.id.button3: {
                String text = mEditText.getText().toString();
                if (!text.trim().isEmpty()) {
                    Intent intent = new Intent(this, Activity4.class);
                    intent.putExtra(EXTRA_TEXT, text);

                    startActivityForResult(intent, REQUEST_CODE_ACTIVITY_4);
                }
            }
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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_ACTIVITY_4){
            if(resultCode == RESULT_OK){
                String text = data.getStringExtra(EXTRA_TEXT);
                mEditText.setText(text);
            }
        }
    }
}
