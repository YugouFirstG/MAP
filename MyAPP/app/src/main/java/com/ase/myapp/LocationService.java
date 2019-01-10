package com.ase.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class LocationService extends Service {
    public LocationService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(LocationService.this,"onCreate",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(LocationService.this,"onCreate",Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
