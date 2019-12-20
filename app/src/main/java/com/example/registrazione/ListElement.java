package com.example.registrazione;


import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ListElement extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView data;
    private TextView regione;
    private TextView durata;
    private Activity parentActivity;

    public ListElement(View itemView, Activity parentActivity){
        super(itemView);
        this.parentActivity = parentActivity;
        data = itemView.findViewById(R.id.dataTV);
        regione = itemView.findViewById(R.id.regioneTV);
        durata = itemView.findViewById(R.id.durataTV);
        itemView.setOnClickListener(this);
    }


    public void setPercorso(Percorso percorso) {
        data.setText(percorso.getDataPartenza());
        regione.setText(percorso.getRegione());
        durata.setText(percorso.getDurata());
    }

    @Override
    public void onClick(View v) {

    }
}
