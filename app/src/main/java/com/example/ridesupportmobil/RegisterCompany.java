package com.example.ridesupportmobil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.ridesupportmobile.RegisterCompanyMutation;
import com.example.ridesupportmobile.RegisterServiceMutation;

import org.jetbrains.annotations.NotNull;

public class RegisterCompany extends AppCompatActivity {

    private final String TAG = "RegisterCompany";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_company);



        createCompany();

    }

    private void createCompany(){

        Button Registrar= (Button) findViewById(R.id.btnCom);

        Registrar.setOnClickListener((view) -> {


            String company_email = ((EditText) findViewById(R.id.email)).getText().toString();
            String company_password = ((EditText) findViewById(R.id.contrasena)).getText().toString();
            String company_name = ((EditText) findViewById(R.id.nombre)).getText().toString();
            String company_city = ((EditText) findViewById(R.id.ciudad)).getText().toString();
            String company_address = ((EditText) findViewById(R.id.direccion)).getText().toString();
            String company_phone = ((EditText) findViewById(R.id.telefono)).getText().toString();
            int phone = Integer.parseInt(company_phone);
            String company_manager = ((EditText) findViewById(R.id.encargado)).getText().toString();

            PortAG.setupApollo().mutate(
                    RegisterCompanyMutation
                            .builder()
                            .email(company_email)
                            .password(company_password)
                            .name(company_name)
                            .city(company_city)
                            .address(company_address)
                            .phone(phone)
                            .manager(company_manager)
                            .build())
                    .enqueue(new ApolloCall.Callback<RegisterCompanyMutation.Data>() {

                        @Override
                        public void onResponse(@NotNull Response<RegisterCompanyMutation.Data> response) {

                            Log.d(TAG, "Response: " + response.data());

                            /*AlertDialog.Builder builder = new AlertDialog.Builder(RegisterCompany.this);
                            builder.setMessage("Su empresa ha sido registrada");
                            AlertDialog alertDialog = builder.create();


                            alertDialog.show();*/

                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {

                            Log.d(TAG, "Exception " + e.getMessage(), e);
                        }
                    });

            /*
            Button btn_reg = (Button) findViewById(R.id.boton_register);
            btn_reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(RegisterCompany.this,Login.class));
                }
            });*/


        });



    }

}


