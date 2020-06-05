package com.example.ridesupportmobil;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventConsume extends AppCompatActivity {
    private TextView textEvent;
    private RequestQueue mQueue;
    private final String TAG = "service";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_consume);

        textEvent = findViewById(R.id.info_event);
        mQueue = Volley.newRequestQueue(this);
        jsonParse();

    }
    private void jsonParse(){
        String url = "http://100.25.150.207:3000/soapconsumer/respuesta.json";
        StringRequest request = new StringRequest
                (url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject js = null;
                try {
                    js = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "Response: "+ response);

                //JSONArray jsonArray = response.getJSONArray();

                //textEvent.setText(js.toString());
                String name = null;
                String date = null;
                String strDate =null;
                String description = null;
                try {
                    name = js.getString("name");
                    date = js.getString("event_start_date");

                    String sDate1="31/12/1998";
                    Date date1=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(date);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    strDate = dateFormat.format(date1);


                    description = js.getString("description");
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
                textEvent.setText("\nEvento: \n"+name+" "+"\n\nFecha del evento: \n"+strDate +" "+"\n\nDescripción: \n"+description);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            error.printStackTrace();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }


}
