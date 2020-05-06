package com.example.ridesupportmobil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.ridesupportmobile.RegisterServiceMutation;

import org.jetbrains.annotations.NotNull;

public class RegisterService extends AppCompatActivity {

    private final String TAG = "RegisterService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_service);


        //deleteServicesM();

        createServicesM();

    }

    private void createServicesM(){

        Button btnRegistrar= (Button) findViewById(R.id.btnRegister);
        btnRegistrar.setOnClickListener((view) -> {


            String Service_id = ((EditText) findViewById(R.id.txtCompanyId)).getText().toString();
            int ids = Integer.parseInt(Service_id);
            String Service_shedule = ((EditText) findViewById(R.id.txtSheduleService)).getText().toString();
            String Service_name = ((EditText) findViewById(R.id.txtCompanyName)).getText().toString();
            String Service_type = ((EditText) findViewById(R.id.txtTypeService)).getText().toString();
            String Service_prices = ((EditText) findViewById(R.id.txtPricesService)).getText().toString();
            String Service_location = ((EditText) findViewById(R.id.txtCompanyLocation)).getText().toString();


            PortAG.setupApollo().mutate(
                    RegisterServiceMutation
                            .builder()
                            .company_id(ids)
                            .company_name(Service_name)
                            .company_location(Service_location)
                            .type_service(Service_type)
                            .prices_service(Service_prices)
                            .shedule_service(Service_shedule)
                            .build())
                    .enqueue(new ApolloCall.Callback<RegisterServiceMutation.Data>() {

                        @Override
                        public void onResponse(@NotNull Response<RegisterServiceMutation.Data> response) {

                            Log.d(TAG, "Response: " + response.data().toString());



                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {

                            Log.d(TAG, "Exception " + e.getMessage(), e);
                        }
                    });
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterService.this);

            builder.setMessage("Servicio registrado");
            AlertDialog alertDialog = builder.create();

            alertDialog.show();

        });
    }

}
