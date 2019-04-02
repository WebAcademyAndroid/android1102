package com.example.android8;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyService myService;

    private ListView mListView;
    private ArrayList<Student> mStudents;
    private ArrayAdapter<Student> mAdapter;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);

        mStudents = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                mStudents);
        mListView.setAdapter(mAdapter);


    }

    private void getStudents() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        DataBaseService.getStudents(this);
    }


    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                Intent intent = new Intent(this, MyService.class);
                intent.setAction("ShowToast");
                intent.putExtra("Text", "Hello service!");

                PendingIntent pendingIntent = createPendingResult(1, intent, 0);
                intent.putExtra("Pending", pendingIntent);

                startService(intent);
            }
            break;
            case R.id.button2: {
                ServiceConnection connection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        myService = ((MyService.MyBinder) service).getService();
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        myService = null;
                    }
                };

                Intent intent = new Intent(this, MyService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);

                if (myService != null) {
                    myService.testBind();
                }
            }
            break;
            case R.id.button3:
                DataBaseService.saveStudent(new Student("Ivan", "Ivanov", 33), this);
                break;
            case R.id.button4:
                getStudents();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String text = data.getStringExtra("Result");
                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == DataBaseService.REQUEST_CODE_GET_STUDENTS) {
            ArrayList<Student> students = data.getParcelableArrayListExtra(DataBaseService.EXTRA_STUDENTS);
            mStudents.clear();
            mStudents.addAll(students);
            mAdapter.notifyDataSetChanged();

            if(mDialog != null){
                mDialog.dismiss();
            }
        } else if (requestCode == DataBaseService.REQUEST_CODE_SAVE_STUDENT) {
            long id = data.getLongExtra(DataBaseService.EXTRA_ID, 0);
            Toast.makeText(this, String.valueOf(id), Toast.LENGTH_LONG).show();

            getStudents();
        }
    }
}
