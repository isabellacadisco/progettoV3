package com.example.registrazione;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SchermataIniziale extends AppCompatActivity implements View.OnClickListener {

    RecyclerView list;
    Adapter percorsiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schermata_iniziale);

        setTitle("I miei percorsi");

        int idPrefs = getSharedPreferences("lemiecazzodipreferenze", 0).getInt("id", 7);
        Log.d("prima schermata", idPrefs + "");

        FloatingActionButton addPercorso = findViewById(R.id.addPercorsoButton);

        addPercorso.setOnClickListener(this);

        Model.getInstance().InitFakeData();

        notifyAdapter();

        Log.d("iniffffff", Model.getInstance().get(0).toString());



    }
    //tentantivo di disabilitare back button
    @Override
    public void onBackPressed() {
    // super.onBackPressed();
    // Not calling **super**, disables back button in current screen.
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Partenza.class);
        startActivity(intent);
    }

    public void notifyAdapter() {
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        percorsiAdapter = new Adapter(this, this, Model.getInstance().getListPercorsi());
        list.setAdapter(percorsiAdapter);
    }
}
