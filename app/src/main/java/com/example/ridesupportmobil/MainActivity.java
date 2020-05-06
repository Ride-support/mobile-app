package com.example.ridesupportmobil;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.ridesupportmobile.RegisterServiceMutation;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_reg = (Button) findViewById(R.id.boton_register);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterService.class));
            }
        });


    }








}



