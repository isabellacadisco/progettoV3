package com.example.registrazione;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<ListElement> {
    private LayoutInflater inflater;
    private Activity parentActivity;
    private ArrayList<Percorso> percorsi;

    public Adapter(Context context, Activity parentActivity, ArrayList<Percorso> percorsi) {
        this.inflater = LayoutInflater.from(context);
        this.parentActivity = parentActivity;
        this.percorsi = percorsi;
    }
    @NonNull
    @Override
    public ListElement onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_element, parent, false);
        return new ListElement(view, parentActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull ListElement holder, int position) {
        Percorso percorso = percorsi.get(position);
        holder.setPercorso(percorso);
    }

    @Override
    public int getItemCount() {
        return percorsi.size();
    }
}
