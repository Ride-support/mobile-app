package com.example.ridesupportmobil;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class PerfilCompany extends AppCompatActivity {

    private final String TAG = "PerfilCompany";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_company);

        Button btn_ini = (Button) findViewById(R.id.btn_reg);
        btn_ini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerfilCompany.this,RegisterService.class));
            }
        });

    }

}



