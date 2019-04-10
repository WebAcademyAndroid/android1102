package com.example.android12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extra = intent.getExtras();
        int id = extra.getInt("id", 0);
        Toast.makeText(context, String.valueOf(id), Toast.LENGTH_LONG).show();
    }
}
