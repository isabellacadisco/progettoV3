package com.example.registrazione;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText numero;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        numero = findViewById(R.id.editTextNumber);

        Button addNumber = findViewById(R.id.buttonConfirmNumber);
        addNumber.setOnClickListener(this);
        Log.d("main",getSharedPreferences("lemiecazzodipreferenze",0).getInt("id",7) + "");

        //controllo di non essere già loggato, se lo sono passo direttamente a schermata iniziale
        if(getSharedPreferences("lemiecazzodipreferenze",0).getInt("id", 7) != 7){
            Intent intent = new Intent(this, SchermataIniziale.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        String numberToAdd = numero.getText().toString();
        Log.d("Bottone", "esisto e devo mandare: " + numberToAdd);

        //aggiungere Volley

        //istanzio request queue
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://159.149.182.237:3000/addNumero";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("numero", numberToAdd);
        } catch (JSONException e) {
            Log.d("main", "non costruisce oggetto json");
        }

        Log.d("main", " " +jsonBody);
        JsonObjectRequest request = new JsonObjectRequest(
                url,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("main", "ok"+ response.toString());

                        int Id = 0;
                        try {
                            Id = response.getInt("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("main", " "+Id);
                        SharedPreferences prefs = getSharedPreferences("lemiecazzodipreferenze",0);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("id",Id);
                        editor.apply();

                        Log.d("main", "ho messo id in sp");

                        Intent intent = new Intent(context, SchermataIniziale.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("main","no ok" + error.toString());
                    }
                }
        );

        queue.add(request);

    }

}
