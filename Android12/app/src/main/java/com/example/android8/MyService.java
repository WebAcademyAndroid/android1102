package com.example.android8;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if (action.equals("ShowToast")) {
                String text = intent.getStringExtra("Text");
                Toast.makeText(this, text, Toast.LENGTH_LONG).show();


                PendingIntent pendingIntent = intent.getParcelableExtra("Pending");

                Intent result = new Intent();
                result.putExtra("Result", "Result text");

                try {
                    pendingIntent.send(this, Activity.RESULT_OK, result);
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void testBind(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Toast.makeText(this, "Service bind", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}
