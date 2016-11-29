package com.example.cho.pocketmongocho;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

public class ManboService extends Service implements SensorEventListener{
    int count = 0;
    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;

    private float x, y, z;
    private static final int SHAKE_THRESHOLD = 250;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    MainValues mv;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("onCreate", "IN");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mv = MainValues.getInstance();
        count = mv.getStep();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i("onStartCommand", "IN");
        if (accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "IN");
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            mv.setStep(0);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.i("onSensorChanged", "IN");
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);

            if (gabOfTime > 100) { //  gap of time of step count
                Log.i("onSensorChanged_IF", "FIRST_IF_IN");
                lastTime = currentTime;

                x = event.values[0];
                y = event.values[1];
                z = event.values[2];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Log.i("onSensorChanged_IF", "SECOND_IF_IN");
                    Intent myFilteredResponse = new Intent("make.a.yong.manbo");

                    count++;
                    mv.setStep(count);
                    mv.setSpeed(7);

                    if (mv.getWalk() == 0){
                        mv.setWalk(1);
                    }
                    else if (mv.getWalk() == 1){
                        mv.setWalk(2);
                    }
                    else if (mv.getWalk() == 2){
                        mv.setWalk(1);
                    }

                    String msg = mv.getWalk() + "";
                    myFilteredResponse.putExtra("stepService", msg);

                    sendBroadcast(myFilteredResponse);
                }
                else{
                    if (mv.getSpeed() > 0){
                        mv.subSpeed();
                    }
                    else {
                        Intent myFilteredResponse = new Intent("make.a.yong.manbo");

                        mv.setWalk(0);

                        String msg = mv.getWalk() + "";
                        myFilteredResponse.putExtra("stepService", msg);

                        sendBroadcast(myFilteredResponse);
                    }
                }

                lastX = event.values[0];
                lastY = event.values[1];
                lastZ = event.values[2];
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
