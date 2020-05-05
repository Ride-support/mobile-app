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
import com.example.ridesupportmobile.RegisterServiceMutation;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_service);


        //deleteServicesM();

        createServicesM();

    }


    private void deleteServicesM(){

        Button btnEliminar= (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener((view) -> {
            String idd_service = ((EditText) findViewById(R.id.txtID)).getText().toString();
            int ids = Integer.parseInt(idd_service);

            port.setupApollo().mutate(
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
        });
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


            port.setupApollo().mutate(
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
        });
    }

}

class port {

    private static final String BASE_URL = "http://35.224.135.194:5000/graphql";

    public static ApolloClient setupApollo(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build();
    }

}

