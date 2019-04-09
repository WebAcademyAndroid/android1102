package com.example.android11;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyButton extends RelativeLayout {
    public MyButton(Context context) {
        super(context);
        init(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_button, this);

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyButton, 0, 0);

            String text = array.getString(R.styleable.MyButton_butonText);
            int img = array.getResourceId(R.styleable.MyButton_buttonImg, 0);

            if (text != null) {
                TextView textView = view.findViewById(R.id.textView);
                textView.setText(text);
            }
            if (img > 0) {
                ImageView imageView = view.findViewById(R.id.imageView);
                imageView.setImageResource(img);
            }
        }
    }
}
