package com.example.sensoractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private Sensor magnetometer;

    private TextView textViewAccelX, textViewAccelY, textViewAccelZ;
    private TextView textViewGyroX, textViewGyroY, textViewGyroZ;
    private TextView textViewMagX, textViewMagY, textViewMagZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Get a reference to the accelerometer, gyroscope and magnetometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Get a reference to the TextViews
        textViewAccelX = (TextView) findViewById(R.id.text_view_accel_x);
        textViewAccelY = (TextView) findViewById(R.id.text_view_accel_y);
        textViewAccelZ = (TextView) findViewById(R.id.text_view_accel_z);
        textViewGyroX = (TextView) findViewById(R.id.text_view_gyro_x);
        textViewGyroY = (TextView) findViewById(R.id.text_view_gyro_y);
        textViewGyroZ = (TextView) findViewById(R.id.text_view_gyro_z);
        textViewMagX = (TextView) findViewById(R.id.text_view_mag_x);
        textViewMagY = (TextView) findViewById(R.id.text_view_mag_y);
        textViewMagZ = (TextView) findViewById(R.id.text_view_mag_z);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the sensor listener
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // get sensor type
        int sensorType = event.sensor.getType();

        // change the text views
        if (sensorType == Sensor.TYPE_ACCELEROMETER) {
            textViewAccelX.setText(String.format("%.2f", x));
            textViewAccelY.setText(String.format("%.2f", y));
            textViewAccelZ.setText(String.format("%.2f", z));
        } else if (sensorType == Sensor.TYPE_GYROSCOPE) {
            textViewGyroX.setText(String.format("%.2f", x));
            textViewGyroY.setText(String.format("%.2f", y));
            textViewGyroZ.setText(String.format("%.2f", z));
        } else if (sensorType == Sensor.TYPE_MAGNETIC_FIELD) {
            textViewMagX.setText(String.format("%.2f", x));
            textViewMagY.setText(String.format("%.2f", y));
            textViewMagZ.setText(String.format("%.2f", z));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes here
    }
}