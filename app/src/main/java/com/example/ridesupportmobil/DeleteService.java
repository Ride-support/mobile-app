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
import com.example.ridesupportmobile.DeleteServiceMutation;

import org.jetbrains.annotations.NotNull;

public class DeleteService extends AppCompatActivity {

    private final String TAG = "DeleteService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_service);


        deleteServicesM();

    }


    private void deleteServicesM(){

        Button btnEliminar= (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener((view) -> {
            String idd_service = ((EditText) findViewById(R.id.txtID)).getText().toString();
            int ids = Integer.parseInt(idd_service);

            PortAG.setupApollo().mutate(
                    DeleteServiceMutation
                            .builder()
                            .service_id(ids)
                            .build())
                    .enqueue(new ApolloCall.Callback<DeleteServiceMutation.Data>() {

                        @Override
                        public void onResponse(@NotNull Response<DeleteServiceMutation.Data> response) {

                            Log.d(TAG, "Response: " + response.data().deleteServiceM());

                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {

                            Log.d(TAG, "Exception " + e.getMessage(), e);
                        }
                    });

            AlertDialog.Builder builder = new AlertDialog.Builder(DeleteService.this);

            builder.setMessage("Servicio eliminado");
            AlertDialog alertDialog = builder.create();

            alertDialog.show();
        });
    }

}
