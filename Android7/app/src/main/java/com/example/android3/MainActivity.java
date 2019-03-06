package com.example.android3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View view = findViewById(R.id.button10);
        registerForContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action1:
                Toast.makeText(this, "action 1", Toast.LENGTH_LONG).show();
                break;
            case R.id.action2:
                Toast.makeText(this, "action 2", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Toast.makeText(this, "Hello toast", Toast.LENGTH_LONG).show();
                break;
            case R.id.button2: {
                Toast toast = Toast.makeText(this, "Hello toast", Toast.LENGTH_LONG);

                toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
                toast.show();
            }
            break;
            case R.id.button3: {
                Toast toast = Toast.makeText(this, "Hello toast", Toast.LENGTH_LONG);

                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.mipmap.ic_launcher);

                ViewGroup linearLayout = (ViewGroup) toast.getView();
                linearLayout.addView(imageView, 0);

                toast.show();
            }
            break;
            case R.id.button4: {
                Toast toast = new Toast(this);
                toast.setGravity(Gravity.BOTTOM, 0, 0);

                View view = getLayoutInflater().inflate(R.layout.toast, null);
                TextView textView = view.findViewById(R.id.textView);
                textView.setText("My toast");

                toast.setView(view);
                toast.show();
            }
            break;
            case R.id.button5: {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                Notification notification = new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("Ticker")
                        .setContentTitle("Title")
                        .setContentText("Text")
                        .setWhen(System.currentTimeMillis())
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentIntent(pendingIntent)
                        .build();

                notificationManager.notify(1, notification);
            }
            break;
            case R.id.button6:
                new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Toast.makeText(MainActivity.this, hourOfDay + ":" + minute,
                                        Toast.LENGTH_LONG).show();
                            }
                        },
                        60, 22220, true).show();
                break;
            case R.id.button7:
                ProgressDialog dialog = new ProgressDialog(this);
                dialog.setMessage("Wait...");
                dialog.setCancelable(false);
                dialog.show();


                dialog.dismiss();
                break;
            case R.id.button8:
                break;
            case R.id.button9:
                PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.button2));
                popupMenu.inflate(R.menu.main);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.show();
                break;
            case R.id.button10:
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action1:
                Toast.makeText(this, "action 1", Toast.LENGTH_LONG).show();
                break;
            case R.id.action2:
                Toast.makeText(this, "action 2", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }
}
