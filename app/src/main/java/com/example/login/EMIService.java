package com.example.login;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class EMIService extends Service {
    public EMIService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public double CalculateEMI(double principalAmount, double rate, int tenure) {
            return principalAmount*(rate*(Math.pow(1+rate,tenure))/(rate*((Math.pow(1+rate,tenure))-1)));
        }
    };
}