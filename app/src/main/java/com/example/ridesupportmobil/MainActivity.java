package com.example.ridesupportmobil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.ridesupportmobile.GetAllServicesQuery;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allServicesM();

    }

    private void allServicesM(){

        prueba.setupApollo().query(
                GetAllServicesQuery
                        .builder()

                        .build())
                .enqueue(new ApolloCall.Callback<GetAllServicesQuery.Data>() {

                    @Override
                    public void onResponse(@NotNull Response<GetAllServicesQuery.Data> response) {

                        Log.d(TAG, "Response: " + response.data().allServicesM());
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                        Log.d(TAG, "Exception " + e.getMessage(), e);
                    }
                });
    }
}
