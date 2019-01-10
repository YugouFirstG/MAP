package com.ase.myapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MyOrientationListener implements SensorEventListener {
    private SensorManager mySensorManager;
    private Sensor mySensor;
    private Context myContext;
    private float lastX;
    private onOrientationListener myOrientationListener;
    public void start(){
        mySensorManager=(SensorManager) myContext.getSystemService(Context.SENSOR_SERVICE);
        if(mySensorManager!=null){
            mySensor=mySensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        }
        if(mySensor!=null){
            mySensorManager.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_UI);
        }
    }
    public void stop(){
        mySensorManager.unregisterListener(this);
    }
    public MyOrientationListener(Context myContext) {//方向传感器的一个构造器
        super();
        this.myContext = myContext;
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.sensor.getType()==Sensor.TYPE_ORIENTATION){
                float x=sensorEvent.values[SensorManager.DATA_X];
                if(Math.abs(x-lastX)>1.0){
                    if(myOrientationListener!=null){
                        myOrientationListener.onOrientationChanged(lastX);
                    }
                }
                lastX=x;
            }
    }
    public void setMyOrientationListener(onOrientationListener myOrientationListener) {
        this.myOrientationListener = myOrientationListener;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public interface onOrientationListener{

        void onOrientationChanged(float x);//回调的方法
    }
}
