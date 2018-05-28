package com.example.krzysztof.tcpphonemouse;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Sensor mySensor2;
    private SensorManager sensorManager;
    public static float[] orientations = new float[3];
    public static String IP;
    public static int PORT;
    public static boolean pressed=false;
    Thread thread= new Thread(new TCP());
    public TextView screen;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor2=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(this,mySensor2,sensorManager.SENSOR_DELAY_GAME);
        thread.start();
        screen=findViewById(R.id.screen);
        screen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        pressed = true;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        pressed=true;
                        break;

                    case MotionEvent.ACTION_UP:
                        pressed = false;
                        break;
                }
                return pressed;
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
      orientations=event.values.clone();


    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
