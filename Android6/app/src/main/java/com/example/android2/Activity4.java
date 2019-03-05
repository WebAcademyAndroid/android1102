package com.example.android2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Activity4 extends AppCompatActivity {

    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        mEditText = findViewById(R.id.editText2);
        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        mEditText.setText(text);

        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = mEditText.getText().toString();

                if (!res.trim().isEmpty()) {
                    Intent resIntent = new Intent();
                    resIntent.putExtra(MainActivity.EXTRA_TEXT, res);

                    setResult(RESULT_OK, resIntent);
                    finish();
                }
            }
        });
    }
}
