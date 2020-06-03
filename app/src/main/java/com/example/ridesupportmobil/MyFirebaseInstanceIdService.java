package com.example.ridesupportmobil;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public static final String TAG = "NOTIFICACION";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String tokenF = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "TOKEN: "+ tokenF);
     }
}
