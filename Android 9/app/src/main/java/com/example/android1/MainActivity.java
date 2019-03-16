package com.example.android1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        /*findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("AAAAAAAAAA");
            }
        });*/
    }

    public void OnClick(View v) {
        switch (v.getId()){
            case R.id.button:
                textView.setText("AAAAAAAAAA");
                break;
            case R.id.button2:
                textView.setText("BBBBBBB");
                break;
        }

    }
}
