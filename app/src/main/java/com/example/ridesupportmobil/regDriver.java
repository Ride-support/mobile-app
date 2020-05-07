package com.example.ridesupportmobil;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.ridesupportmobile.DeleteServiceMutation;
import com.example.ridesupportmobile.GetAllServicesQuery;
import com.apollographql.apollo.ApolloClient;
import com.example.ridesupportmobile.RegDriverMutation;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;


public class regDriver extends AppCompatActivity{

    private final String TAG = "regDriver";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        createDriver();

    }

    private void createDriver(){

        Button btnRegCommpany= (Button) findViewById(R.id.userGuardar);
        btnRegCommpany.setOnClickListener((view) -> {


            String Service_userEmail = ((EditText) findViewById(R.id.userEmail)).getText().toString();
            String Service_userPassword = ((EditText) findViewById(R.id.userContrasena)).getText().toString();
            String Service_userName = ((EditText) findViewById(R.id.userNombre)).getText().toString();
            String Service_userLastname = ((EditText) findViewById(R.id.userApellido)).getText().toString();
            String Service_userAge = ((EditText) findViewById(R.id.userEdad)).getText().toString();
            int ageint = Integer.parseInt(Service_userAge);
            String Service_userAddress = ((EditText) findViewById(R.id.userDireccion)).getText().toString();
            String Service_userPhone = ((EditText) findViewById(R.id.userTelefono)).getText().toString();
            int phoneint = Integer.parseInt(Service_userPhone);
            String Service_userVehicle = ((EditText) findViewById(R.id.userVehiculo)).getText().toString();


            PortAG.setupApollo().mutate(
                    RegDriverMutation
                            .builder()
                            .name(Service_userName)
                            .email(Service_userEmail)
                            .password(Service_userPassword)
                            .lastname(Service_userLastname)
                            .age(ageint)
                            .address(Service_userAddress)
                            .phone(phoneint)
                            .vehicle(Service_userVehicle)
                            .build())
                    .enqueue(new ApolloCall.Callback<RegDriverMutation.Data>() {

                        @Override
                        public void onResponse(@NotNull Response<RegDriverMutation.Data> response) {

                            Log.d(TAG, "Response: " + response.data());

                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {

                            Log.d(TAG, "Exception " + e.getMessage(), e);
                        }
                    });
        });
    }
}