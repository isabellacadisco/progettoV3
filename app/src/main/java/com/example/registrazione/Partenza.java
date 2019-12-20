package com.example.registrazione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;


public class Partenza extends AppCompatActivity implements TextWatcher {
    private MapView mapView;
    private static final String[] PLACES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Partenza");


        Mapbox.getInstance(this, "pk.eyJ1IjoiaXNhYmVsbGFjYWRpc2NvIiwiYSI6ImNrMzZqaXNibTAyN20zb21yN3RmMmV4bngifQ.dj2r34d7xd1zGMVJzeJlEA");
        setContentView(R.layout.activity_partenza);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, PLACES);
        AutoCompleteTextView textView = findViewById(R.id.autoCompleteTextView);
        textView.setAdapter(adapter);
        textView.addTextChangedListener(this); //partenza è la classe che implementa l'interfaccia
        //sono dentro partenza, quindi this

        //textView.robaToccata
        //scoprire come selezionare roba dell'autocomple

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments.


                    }
                });
            }
        });


        Log.d("navigazione", "sono nella partenza");
    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("textWatcher", s + "");
        //volley ricerca luoghi

        //istanzio Array per risultati ricerca
        final ArrayList<String> res = new ArrayList<String>();



        //istanzio request queue
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+s+".json?access_token=pk.eyJ1IjoibWF0dGZpY2tlIiwiYSI6ImNqNnM2YmFoNzAwcTMzM214NTB1NHdwbnoifQ.Or19S7KmYPHW8YjRz82v6g&cachebuster=1576770472966&autocomplete=true&language=it";
        JSONObject jsonBody = new JSONObject();


        JsonObjectRequest request = new JsonObjectRequest(
                GET,
                url,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("partenza", "ok"+ response.toString());

                        try {
                            Log.d("partenza", "ok"+ response.getJSONArray("features"));
                            JSONArray resultsJSON = response.getJSONArray("features");


                            /*   List<Student> list = new ArrayList<>();
                                try {
                                    JSONArray studentsJSON = serverResponse.getJSONArray("students");
                                    for (int i = 0; i < studentsJSON.length(); i++) {
                                        JSONObject studentJSON = studentsJSON.getJSONObject(i);
                                        Student student = new Student(studentJSON);
                                        list.add(student);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return list;
                            }

                            */

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("partenza","no ok" + error.toString());
                    }
                }

        )
        {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };



        queue.add(request);



        // array con place_name_it per aggiornare countries + array con tutte le info
        //col res della volley aggiorno l'array countries (countries = qualcosa)
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}


//int idPrefs = getSharedPreferences("lemiecazzodipreferenze", 0).getInt("id", 7);
