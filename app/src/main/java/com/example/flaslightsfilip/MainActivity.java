package com.example.flaslightsfilip;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CAMERA = 100;
    Camera camera;
    boolean isOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isOn = false;
        ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA }, REQUEST_CODE_CAMERA);


        final Button button = findViewById(R.id.tombol1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOn) {
                    turnOff();
                } else {
                    turnOn();
                }
                isOn = !isOn;
                if (isOn) {
                    button.setText("Turn Off");
                } else {
                    button.setText("Turn ON");
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                finish();
            }
        }
    }

    private void turnOff() {
        camera.stopPreview();
        camera.release();
    }

    private void turnOn() {
        camera = Camera.open();
        Camera.Parameters params = camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(params);
        camera.startPreview();
    }
}
