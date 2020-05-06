package com.example.ridesupportmobil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.ridesupportmobile.AuthCompanyMutation;
import com.example.ridesupportmobile.AuthDriverMutation;

import org.jetbrains.annotations.NotNull;

public class LoginDriver extends AppCompatActivity {

    private static final String TAG = "LoginDriver";

    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.login);
        RadioButton driverLoginBtt = (RadioButton) findViewById(R.id.driver_login);
        RadioButton companyLoginBtt = (RadioButton) findViewById(R.id.company_login);
        if (driverLoginBtt.isChecked()){
            loginDriver();
        }else{
            loginCompany();
        }


    }

    private void loginDriver(){
        Button btnLogin = (Button) findViewById(R.id.boton_iniciar_sesion);
        btnLogin.setOnClickListener((view)->{
            String username = ((EditText) findViewById(R.id.input_usuario)).getText().toString();
            String password = ((EditText)findViewById(R.id.input_contrasena)).getText().toString();

            PortAG.setupApollo().mutate(
                    AuthDriverMutation
                            .builder()
                            .driver_email(username)
                            .driver_password(password)
                            .build())
                    .enqueue(new ApolloCall.Callback<AuthDriverMutation.Data>() {

                        @Override
                        public void onResponse(@NotNull Response<AuthDriverMutation.Data> response) {

                            Log.d(TAG, "Response: " + response.data().toString());
                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {
                            Log.d(TAG, "Exception " + e.getMessage(), e);
                        }
                    });
        });
    }

    private void loginCompany(){
        Button btnLogin = (Button) findViewById(R.id.boton_iniciar_sesion);
        btnLogin.setOnClickListener((view)->{
            String email = ((EditText) findViewById(R.id.input_usuario)).getText().toString();
            String password = ((EditText)findViewById(R.id.input_contrasena)).getText().toString();

            PortAG.setupApollo().mutate(
                    AuthCompanyMutation
                            .builder()
                            .company_email(email)
                            .company_password(password)
                            .build())
                    .enqueue(new ApolloCall.Callback<AuthCompanyMutation.Data>() {

                        @Override
                        public void onResponse(@NotNull Response<AuthCompanyMutation.Data> response) {

                            Log.d(TAG, "Response: " + response.data().toString());
                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {
                            Log.d(TAG, "Exception " + e.getMessage(), e);
                        }
                    });
        });
    }
}
