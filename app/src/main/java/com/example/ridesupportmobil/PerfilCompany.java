package com.example.ridesupportmobil;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.json.JsonDataException;
import com.example.ridesupportmobile.RegDriverMutation;
import com.example.ridesupportmobile.ServiceByIdQuery;
import org.jetbrains.annotations.NotNull;

import org.json.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.jar.Attributes;


public class PerfilCompany extends AppCompatActivity {

    private final String TAG = "PerfilCompany";
    private TextView textBox;
    private Button queryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_company);

        textBox = (TextView) findViewById(R.id.namequery);
        queryButton = (Button) findViewById(R.id.querybt);

        Button btn_ini = (Button) findViewById(R.id.btn_reg);
        btn_ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerfilCompany.this,RegisterService.class));
            }
        });

    getServiceById();

    }

    private void getServiceById(){

            PortAG.setupApollo().query(
                    ServiceByIdQuery
                            .builder()
                            .service_id(5)
                            .build())
                    .enqueue(new ApolloCall.Callback<ServiceByIdQuery.Data>() {

                        @Override
                        public void onResponse(@NotNull Response<ServiceByIdQuery.Data> response) {


                            Log.d(TAG, "Response: "+ response.data().toString());

                            textBox.setText(response.data().serviceByIdM().Name());


                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {

                            Log.d(TAG, "Exception " + e.getMessage(), e);
                        }

                    });



    }

}



