package com.example.sokoban.database;

public class Ligne {

    private int ligneId;
    private String contenuLigne;
    private int tableauId;
    private int numeroLigne;

    public Ligne()  {
    }

    public Ligne(String contenuLigne, int tableauId) {
        this.contenuLigne = contenuLigne;
        this.tableauId = tableauId;
    }

    public Ligne(int ligneId, String contenuLigne, int tableauId, int numeroLigne) {
        this.ligneId = ligneId;
        this.tableauId = tableauId;
        this.contenuLigne = contenuLigne;
        this.numeroLigne = numeroLigne;
    }

    public int getLigneId() {
        return ligneId;
    }

    public int getTableauId() {
        return tableauId;
    }

    public void setTableauId(int id) {
        this.tableauId = id;
    }

    public String getContenuLigne() {
        return contenuLigne;
    }

    public void setContenuLigne(String contenuLigne) {
        this.contenuLigne = contenuLigne;
    }


    public int getNumeroLigne() {
        return numeroLigne;
    }

    public void setNumeroLigne(int numero) {
        this.numeroLigne = numero;
    }
}
