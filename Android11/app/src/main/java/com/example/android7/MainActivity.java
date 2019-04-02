package com.example.android7;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme1);

        setContentView(R.layout.activity_main);
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("KEY", "text");
                editor.commit();
            }
            break;
            case R.id.button2: {
                SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("KEY", "text");
                editor.commit();
            }
            break;
            case R.id.button3: {
                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                String text = preferences.getString("KEY", "");
                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            }
            break;
            case R.id.button4:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.button5: {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                String pref = preferences.getString("edit_text_preference_1", "");
                Toast.makeText(this, pref, Toast.LENGTH_LONG).show();
            }
            break;
            case R.id.button6:
                saveInternalFile("MyFile.txt", "File data");
                break;
            case R.id.button7: {
                String data = readInternalFile("MyFile.txt");
                Toast.makeText(this, data, Toast.LENGTH_LONG).show();
            }
            break;
            case R.id.button8:
                if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File folder =
                                new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/MyFolder");

                        saveExternalFile(folder, "MyFile.txt", "File data");
                    } else {
                        Toast.makeText(this, "No external storage", Toast.LENGTH_LONG).show();
                    }
                } else {
                    checkPermissions();
                }
                break;
            case R.id.button9:
                if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File folder =
                                new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/MyFolder");

                        String data = readExternalFile(folder,"MyFile.txt");
                        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "No external storage", Toast.LENGTH_LONG).show();
                    }
                } else {
                    checkPermissions();
                }
                break;
            case R.id.button10:
                break;
        }
    }


    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            requestPermissions(permissions, 0);
        }
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private void saveExternalFile(File folder, String fileName, String data) {
        try {
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(folder, fileName);
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));

            writer.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readExternalFile(File folder, String fileName) {
        try {
            File file = new File(folder, fileName);
            if(file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                StringBuilder builder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                return builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void saveInternalFile(String fileName, String data) {
        try {
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE)));

            writer.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readInternalFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(fileName)));

            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
