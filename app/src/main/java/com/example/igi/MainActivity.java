package com.example.igi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.CAMERA;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button butLogin;
    Button butToSignUp;
    Button butInfo;
    String Tag = "IGI";


    /**
     * This function requests premissions
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new

                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO, CAMERA}, RequestPermissionCode);
    }
    public static final int RequestPermissionCode = 1;
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean CameraPermission = grantResults[2] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission && CameraPermission) {
                        Toast.makeText(MainActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Permission Denied",
                                Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(),
                CAMERA);
        return result == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(Tag,"started");

        if(!checkPermission()) {
            requestPermission();
        }

        setContentView(R.layout.activity_main);
        butLogin = (Button) findViewById(R.id.ButLogin);
        butToSignUp = (Button) findViewById(R.id.ButToSignUp);
        butInfo = (Button) findViewById(R.id.info_but);
        butLogin.setOnClickListener(this);
        butToSignUp.setOnClickListener(this);
        butInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == butLogin) {
            Intent intentLogin = new Intent(this, HomeScreen.class);
            startActivity(intentLogin);
            finish();
        }
        if (v == butToSignUp) {
            Intent intentLogin = new Intent(this, signup.class);
            startActivity(intentLogin);
            finish();
        }
        if (v == butInfo) {
            Intent intentDiscover = new Intent(this, InfoPage.class);
            startActivity(intentDiscover);
        }
    }
}

