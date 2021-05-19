package com.example.mytorch;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
Switch aSwitch;
TextView textView;
CameraManager cameraManager;
String cameraid,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch=findViewById(R.id.on_off_switch);
        textView=findViewById(R.id.tv_result);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                torch(isChecked);
            }
        });
    }

    private void torch(boolean isChecked) {

        try {
            cameraManager=(CameraManager)getSystemService(CAMERA_SERVICE);
            cameraid=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraid,isChecked);
            result=isChecked?"ON":"OFF";
            textView.setText(result);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}