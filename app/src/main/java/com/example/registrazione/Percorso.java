package com.example.registrazione;

import java.util.Date;

public class Percorso {
    private String regione;
    private String durata;
    private String dataPartenza;
    private String dataArrivo;

    public Percorso(String regione, String durata, String partenza, String arrivo){
        this.regione = regione;
        this.durata = durata;
        this.dataPartenza = partenza;
        this.dataArrivo = arrivo;
    }

    public String toString(){
        return regione + ", " + durata + ", " + dataPartenza + (dataArrivo == null ? "" : ", " + dataArrivo);
    }

    public String getRegione(){
        return regione;
    }

    public String getDurata(){
        return durata;
    }

    public String getDataPartenza() {
        return dataPartenza;
    }

    public String getDataArrivo() {
        return dataArrivo;
    }
}
