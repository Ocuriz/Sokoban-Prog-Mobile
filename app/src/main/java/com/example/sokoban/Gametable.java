package com.example.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Gametable extends AppCompatActivity {

    Case[] listeElement = new Case[54];
    Case[][] plateauFinal;
    char[][] plateauActuel = {};
    ArrayList<Case> destinationCases = new ArrayList<Case>();
    Personnage joueur;
    GridView grid;
    int level = 1;
    ArrayList<Caisse> listeCaisses = new ArrayList<Caisse>();

    public void initGame(char[][] plateau){
        plateauActuel = plateau;
        TextView levelText = findViewById(R.id.level);
        levelText.setText("Level " + String.valueOf(level));
        char[] ligne;
        ArrayList<String> lst = new ArrayList<String>();
        ArrayAdapter<String> adapter;
        plateauFinal = new Case[plateau.length][];
        grid = findViewById(R.id.idGV);
        int index = 0;
        for(int i = 0; i < plateau.length; i++){
            ligne = plateau[i];
            Case[] finalLigne = new Case[ligne.length];
            for(int j = 0; j < ligne.length; j++){
                Case element = new Case(j,i);
                element.setType(String.valueOf(ligne[j]));
                finalLigne[j] = element;
                listeElement[index] = element;
                index++;
                if(element.getType() == CaseType.JOUEUR){
                    joueur = new Personnage(element.getX(), element.getY());
                }
                if(element.getType() == CaseType.BOITE){
                    listeCaisses.add(new Caisse(element.getX(), element.getY()));
                }
                if(element.getType() == CaseType.DESTINATION){
                    destinationCases.add(element);
                }
            }
            plateauFinal[i] = finalLigne;
        }

        grid.setNumColumns(9);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        AdaptationGrid adaptationGrid = new AdaptationGrid(getApplicationContext(), listeElement, grid.getLayoutParams().width, grid.getLayoutParams().height);
        grid.setAdapter(adaptationGrid);

    }

    public boolean itsWin(){
        boolean victoire = true;
        for(Case d: destinationCases){
            if(plateauFinal[d.getY()][d.getX()].getType() != CaseType.BOITE){
                victoire = false;
            }
        }
        return victoire;
    }

    public void updateView() throws IOException {
        if(itsWin()){
            if(level < 3){
                level++;
                listeCaisses.clear();
                destinationCases.clear();
                setContentView(R.layout.gametable);
                initGame(getTableau());
            }
            else{
                TextView levelText = findViewById(R.id.level);
                levelText.setText("Fini");
            }
        }
        grid.setNumColumns(9);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        AdaptationGrid adaptationGrid = new AdaptationGrid(getApplicationContext(), listeElement, grid.getLayoutParams().width, grid.getLayoutParams().height);
        grid.setAdapter(adaptationGrid);
    }

    public void goLeft(View view) throws IOException {

        int xJoueur = joueur.getX();
        int yJoueur = joueur.getY();
        Caisse caisse;
        if( xJoueur-1 >= 0 && plateauFinal[yJoueur][xJoueur-1].getType() != CaseType.MUR){
            if(xJoueur-2 >=0 && plateauFinal[yJoueur][xJoueur-2].getType() != CaseType.MUR
            && plateauFinal[yJoueur][xJoueur-2].getType() != CaseType.BOITE){
                for(Caisse c: listeCaisses){
                    if(c.getX() == xJoueur-1 && c.getY() == yJoueur){
                        c.setX(xJoueur-2);
                        plateauFinal[yJoueur][xJoueur-2].setType("C");
                        if(destinationCases.contains(plateauFinal[yJoueur][xJoueur-1])){
                            plateauFinal[yJoueur][xJoueur-1].setType("x");
                        }
                        else {
                            plateauFinal[yJoueur][xJoueur-1].setType("vide");
                        }
                    }
                }
            }
            if(plateauFinal[yJoueur][xJoueur-1].getType() != CaseType.BOITE){
                plateauFinal[yJoueur][xJoueur-1].setType("P");
                if(destinationCases.contains(plateauFinal[yJoueur][xJoueur])){
                    plateauFinal[yJoueur][xJoueur].setType("x");
                }
                else {
                    plateauFinal[yJoueur][xJoueur].setType("vide");
                }
                joueur.setX(xJoueur-1);
            }
        }
        updateView();
    }

    public void goRight(View view) throws IOException {
        int xJoueur = joueur.getX();
        int yJoueur = joueur.getY();
        if( xJoueur+1 <= 8 && plateauFinal[yJoueur][xJoueur+1].getType() != CaseType.MUR){
            if(xJoueur+2 <=8 && plateauFinal[yJoueur][xJoueur+2].getType() != CaseType.MUR
                    && plateauFinal[yJoueur][xJoueur+2].getType() != CaseType.BOITE){
                for(Caisse c: listeCaisses){
                    if(c.getX() == xJoueur+1 && c.getY() == yJoueur){
                        c.setX(xJoueur+2);
                        plateauFinal[yJoueur][xJoueur+2].setType("C");
                        if(destinationCases.contains(plateauFinal[yJoueur][xJoueur+1])){
                            plateauFinal[yJoueur][xJoueur+1].setType("x");
                        }
                        else {
                            plateauFinal[yJoueur][xJoueur+1].setType("vide");
                        }
                    }
                }
            }
            if(plateauFinal[yJoueur][xJoueur+1].getType() != CaseType.BOITE){
                plateauFinal[yJoueur][xJoueur+1].setType("P");
                if(destinationCases.contains(plateauFinal[yJoueur][xJoueur])){
                    plateauFinal[yJoueur][xJoueur].setType("x");
                }
                else {
                    plateauFinal[yJoueur][xJoueur].setType("vide");
                }
                joueur.setX(xJoueur+1);
            }
        }
        updateView();
    }

    public void goTop(View view) throws IOException {
        int xJoueur = joueur.getX();
        int yJoueur = joueur.getY();
        if( yJoueur-1 >= 0 && plateauFinal[yJoueur-1][xJoueur].getType() != CaseType.MUR){
            if(yJoueur-2 >=0 && plateauFinal[yJoueur-2][xJoueur].getType() != CaseType.MUR
                    && plateauFinal[yJoueur-2][xJoueur].getType() != CaseType.BOITE){
                for(Caisse c: listeCaisses){
                    if(c.getX() == xJoueur && c.getY() == yJoueur-1){
                        c.setY(yJoueur-2);
                        plateauFinal[yJoueur-2][xJoueur].setType("C");
                        plateauFinal[yJoueur-1][xJoueur].setType("vide");
                        if(destinationCases.contains(plateauFinal[yJoueur-1][xJoueur])){
                            plateauFinal[yJoueur-1][xJoueur].setType("x");
                        }
                        else {
                            plateauFinal[yJoueur-1][xJoueur].setType("vide");
                        }
                    }
                }
            }
            if(plateauFinal[yJoueur-1][xJoueur].getType() != CaseType.BOITE){
                plateauFinal[yJoueur-1][xJoueur].setType("P");
                if(destinationCases.contains(plateauFinal[yJoueur-1][xJoueur])){
                    plateauFinal[yJoueur][xJoueur].setType("x");
                }
                else {
                    plateauFinal[yJoueur][xJoueur].setType("vide");
                }
                joueur.setY(yJoueur-1);
            }
        }
        updateView();
    }

    public void goBottom(View view) throws IOException {
        int xJoueur = joueur.getX();
        int yJoueur = joueur.getY();
        if( yJoueur+1 <= 5 && plateauFinal[yJoueur+1][xJoueur].getType() != CaseType.MUR){
            for(Caisse c: listeCaisses){
                if(c.getX() == xJoueur && c.getY() == yJoueur+1){
                    if(yJoueur+2<=5 && plateauFinal[yJoueur+2][xJoueur].getType() != CaseType.MUR){
                        c.setY(yJoueur+2);
                        plateauFinal[yJoueur+2][xJoueur].setType("C");
                        if(destinationCases.contains(plateauFinal[yJoueur+1][xJoueur])){
                            plateauFinal[yJoueur+1][xJoueur].setType("x");
                        }
                        else {
                            plateauFinal[yJoueur+1][xJoueur].setType("vide");
                        }
                    }
                }
            }
            if(plateauFinal[yJoueur+1][xJoueur].getType() != CaseType.BOITE) {
                plateauFinal[yJoueur + 1][xJoueur].setType("P");
                if(destinationCases.contains(plateauFinal[yJoueur][xJoueur])){
                    plateauFinal[yJoueur][xJoueur].setType("x");
                }
                else {
                    plateauFinal[yJoueur][xJoueur].setType("vide");
                }
                joueur.setY(yJoueur + 1);
            }
        }
        updateView();
    }

    public void retry(View view){
        setContentView(R.layout.gametable);
        initGame(plateauActuel);
    }


    public char[][] getTableau() throws IOException, MalformedURLException {
        String urlAPI = "http://185.212.225.90/api/tableau/" + String.valueOf(level);
        GetData data = new GetData();
        String resultData = "";
        JSONArray array = new JSONArray();
        String ligne = "";
        char[][] plateau = null;
        try {
            resultData = data.execute(urlAPI).get();
            JSONObject object = new JSONObject(resultData);
            array = object.getJSONArray("data");
            plateau = new char[6][9];

            for(int i = 0; i < array.length(); i++){
                JSONObject ligneJSON = array.getJSONObject(i);

                ligne = ligneJSON.getString("ligne"+(i+1));
                for(int j= 0; j < ligne.length(); j++){
                    plateau[i][j] = ligne.charAt(j);
                }
                Log.i("vv", String.valueOf(ligne));
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        System.out.println(plateau);
        return plateau;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametable);
        char[][] tab = {};
        try {
            tab = getTableau();
            initGame(tab);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}