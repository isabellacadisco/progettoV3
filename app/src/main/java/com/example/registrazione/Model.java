package com.example.registrazione;

import java.util.ArrayList;

public class Model {

    private static final Model ourIstance = new Model();

    private ArrayList<Percorso> listPercorsi = null;

    public static Model getInstance() {
        return ourIstance;
    }

    public ArrayList<Percorso> getListPercorsi() { return listPercorsi;}

    private Model() {
        listPercorsi = new ArrayList<Percorso>();
    }

    public String get(int index){
        return listPercorsi.get(index).toString();
    }

    public void InitFakeData() {
        Percorso a = new Percorso("Lombardia", "3h 51min",  "27/08/2019", null);
        Percorso b = new Percorso("Piemonte", "4h 51min",  "27/08/2019", null);
        Percorso c = new Percorso("Basilicata", "3h 51min",  "27/08/2019", null);
        Percorso d = new Percorso("Campania", "3h 51min",  "27/08/2019", null);
        Percorso e = new Percorso("Val D'Aosta", "3h 51min",  "27/08/2019", null);

        listPercorsi.add(a);
        listPercorsi.add(b);
        listPercorsi.add(c);
        listPercorsi.add(d);
        listPercorsi.add(e);

    }
}

