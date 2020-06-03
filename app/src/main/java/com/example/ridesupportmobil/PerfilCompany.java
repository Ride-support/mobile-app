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
    private TextView na;
    private TextView add;
    private TextView em;
    private TextView ciudad;
    private TextView pho;
    private TextView man;



    private Button queryButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_company);

        na = (TextView) findViewById(R.id.namequery);
        add = (TextView) findViewById(R.id.dirquery);
        em = (TextView) findViewById(R.id.emailquery);
        ciudad = (TextView) findViewById(R.id.cityquery);
        pho = (TextView) findViewById(R.id.phonequery);
        man = (TextView) findViewById(R.id.managerquery);
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

                            na.setText("Nombre:  Car company");
                            add.setText("Dirección:  Carrera 5 #22a -56");
                            em.setText("Email:  empresa@gmail.com");
                            ciudad.setText("Ciudad:  Bogotá");
                            man.setText("Administrador:  Juan Perez");
                            pho.setText("Teléfono:  3193108929");




                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {

                            Log.d(TAG, "Exception " + e.getMessage(), e);
                        }

                    });



    }

}



