package com.example.android12;

import android.content.Context;
import android.content.SharedPreferences;

public class IntManager {
    public static int nextInt(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int count = preferences.getInt("id", 0);
        count++;

        if (count > 9999999) {
            count = 1;
        }

        editor.putInt("id", count);
        editor.apply();

        return count;
    }
}
