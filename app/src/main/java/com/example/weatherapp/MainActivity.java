package com.example.weatherapp;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    String CITY;

    String API ="83cf65e2261046164835634fd57b6f31";
    Button search;
    EditText etCity;
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    TextView city,temp,forecast,humidity,min_temp,sunrises,sunsets;
    DecimalFormat df = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            etCity = (EditText) findViewById(R.id.Your_city);

            search = (Button) findViewById(R.id.search);

            city = (TextView) findViewById(R.id.city);

            temp = (TextView) findViewById(R.id.temp);

            forecast = (TextView) findViewById(R.id.forecast);

            humidity = (TextView) findViewById(R.id.humidity);

            min_temp = (TextView) findViewById(R.id.min_temp);

            sunrises = (TextView) findViewById(R.id.sunrises);

            sunsets = (TextView) findViewById(R.id.sunsets);

        }

        public void getWeatherDetails(View view){
        String tempurl="";
        String cityname = etCity.getText().toString().trim();
        if(cityname.equals("")){
            Toast toast=Toast.makeText(getApplicationContext(),"Please Enter a City!",Toast.LENGTH_SHORT);
            toast.setMargin(50,50);
            toast.show();
        }
        else{
            tempurl = url +"?q=" + cityname + "&appid=" + API;
        }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }



}