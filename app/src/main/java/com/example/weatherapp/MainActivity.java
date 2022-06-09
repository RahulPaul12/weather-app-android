package com.example.weatherapp;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
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
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String CITY="London";

    String API = "83cf65e2261046164835634fd57b6f31";
    Button search;
    EditText etCity;
    //private final String url = "https://api.openweathermap.org/data/2.5/weather";
    TextView city, temp, forecast, humidity, min_temp, sunrises, sunsets;
    //DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {


            etCity = (EditText) findViewById(R.id.Your_city);

            search = (Button) findViewById(R.id.search);

            city = (TextView) findViewById(R.id.city);

            temp = (TextView) findViewById(R.id.temp);

            forecast = (TextView) findViewById(R.id.forecast);

            humidity = (TextView) findViewById(R.id.humidity);

            min_temp = (TextView) findViewById(R.id.min_temp);

            sunrises = (TextView) findViewById(R.id.sunrises);

            sunsets = (TextView) findViewById(R.id.sunsets);
            //CITY = etCity.getText().toString();

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CITY = etCity.getText().toString();
                    new weatherTask().execute();
                }
            });

        }

        }

        class weatherTask extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

            }

            protected String doInBackground(String... args) {
                //String tempurl = "";
                String response=null;
                if(CITY.equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter a City Name!" , Toast.LENGTH_SHORT).show();
                    return response;
                }

                else
                {                response = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + CITY +"&units=metric&appid=" + API);
                                 return response;
                }



            }


            protected void onPostExecute(String result) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    JSONObject main = jsonObj.getJSONObject("main");
                    JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);
                    JSONObject sys = jsonObj.getJSONObject("sys");
                    JSONObject wind = jsonObj.getJSONObject("wind");
                    String tempar = main.getString("temp") + "°C";
                    String Humidity = main.getString("humidity");
                    String cityName = jsonObj.getString("name");
                    String Wind = wind.getString("speed")+"Kmh";
                    Long sunrise = sys.getLong("sunrise");
                    Long sunset = sys.getLong("sunset");
                    String Fore = weather.getString("description");
                    Long rise = sys.getLong("sunrise");
                    String Sunrise = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(rise * 1000));
                    Long set = sys.getLong("sunset");
                    String Sunset = new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(set * 1000));
                    city.setText(cityName);
                    temp.setText(tempar);
                    forecast.setText(Fore);
                    humidity.setText(Humidity);
                    min_temp.setText(Wind);
                    sunrises.setText(Sunrise);
                    sunsets.setText(Sunset);
                } catch (JSONException e) {

                    Toast.makeText(MainActivity.this, "Error:" + e.toString(), Toast.LENGTH_SHORT).show();
                }


            }


        }
    }
