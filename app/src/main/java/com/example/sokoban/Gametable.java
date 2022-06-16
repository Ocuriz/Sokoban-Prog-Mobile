package com.example.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Gametable extends AppCompatActivity {

    Case[] listeElement = new Case[54];
    Case[][] plateauFinal;
    Personnage joueur;
    GridView grid;
    ArrayList<Caisse> listeCaisses = new ArrayList<Caisse>();

    public void initGame(char[][] plateau){
        char[] ligne;
        ArrayList<String> lst = new ArrayList<String>();
        ArrayAdapter<String> adapter;
        plateauFinal = new Case[plateau.length][];
        grid = findViewById(R.id.idGV);
        int index = 0;
        int indexCaisse = 0;
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
            }
            plateauFinal[i] = finalLigne;
        }

        grid.setNumColumns(9);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        AdaptationGrid adaptationGrid = new AdaptationGrid(getApplicationContext(), listeElement, grid.getLayoutParams().width, grid.getLayoutParams().height);
        grid.setAdapter(adaptationGrid);

    }

    public void updateView(){
        grid.setNumColumns(9);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        AdaptationGrid adaptationGrid = new AdaptationGrid(getApplicationContext(), listeElement, grid.getLayoutParams().width, grid.getLayoutParams().height);
        grid.setAdapter(adaptationGrid);
    }

    public void goLeft(View view){

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
                        plateauFinal[yJoueur][xJoueur-1].setType("vide");
                    }
                }
            }
            if(plateauFinal[yJoueur][xJoueur-1].getType() != CaseType.BOITE){
                plateauFinal[yJoueur][xJoueur-1].setType("P");
                plateauFinal[yJoueur][xJoueur].setType("vide");
                joueur.setX(xJoueur-1);
            }
        }
        updateView();
    }

    public void goRight(View view){
        int xJoueur = joueur.getX();
        int yJoueur = joueur.getY();
        if( xJoueur+1 <= 8 && plateauFinal[yJoueur][xJoueur+1].getType() != CaseType.MUR){
            if(xJoueur+2 <=8 && plateauFinal[yJoueur][xJoueur+2].getType() != CaseType.MUR
                    && plateauFinal[yJoueur][xJoueur+2].getType() != CaseType.BOITE){
                for(Caisse c: listeCaisses){
                    if(c.getX() == xJoueur+1 && c.getY() == yJoueur){
                        c.setX(xJoueur+2);
                        plateauFinal[yJoueur][xJoueur+2].setType("C");
                        plateauFinal[yJoueur][xJoueur+1].setType("vide");
                    }
                }
            }
            if(plateauFinal[yJoueur][xJoueur+1].getType() != CaseType.BOITE){
                plateauFinal[yJoueur][xJoueur+1].setType("P");
                plateauFinal[yJoueur][xJoueur].setType("vide");
                joueur.setX(xJoueur+1);
            }
        }
        updateView();
    }

    public void goTop(View view){
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
                    }
                }
            }
            if(plateauFinal[yJoueur-1][xJoueur].getType() != CaseType.BOITE){
                plateauFinal[yJoueur-1][xJoueur].setType("P");
                plateauFinal[yJoueur][xJoueur].setType("vide");
                joueur.setY(yJoueur-1);
            }
        }
        updateView();
    }

    public void goBottom(View view){
        int xJoueur = joueur.getX();
        int yJoueur = joueur.getY();
        if( yJoueur+1 <= 5 && plateauFinal[yJoueur+1][xJoueur].getType() != CaseType.MUR){
            for(Caisse c: listeCaisses){
                if(c.getX() == xJoueur && c.getY() == yJoueur+1){
                    if(yJoueur+2<=5 && plateauFinal[yJoueur+2][xJoueur].getType() != CaseType.MUR){
                        c.setY(yJoueur+2);
                        plateauFinal[yJoueur+2][xJoueur].setType("C");
                        plateauFinal[yJoueur+1][xJoueur].setType("vide");
                    }
                }
            }
            if(plateauFinal[yJoueur+1][xJoueur].getType() != CaseType.BOITE) {
                plateauFinal[yJoueur + 1][xJoueur].setType("P");
                plateauFinal[yJoueur][xJoueur].setType("vide");
                joueur.setY(yJoueur + 1);
            }
        }
        updateView();
    }

    /*
        ..####...
        ###..####
        #.....C.#
        #.#..#C.#
        #.x.x#P.#
        #########
    */


    public char[][] getTableau() throws IOException, MalformedURLException {
        String urlAPI = "http://185.212.225.90/api/tableau/1";
        GetData data = new GetData();
        String resultData = "";
        JSONArray array = new JSONArray();
        String ligne = "";
        try {
            resultData = data.execute(urlAPI).get();
            JSONObject object = new JSONObject(resultData);
            array = object.getJSONArray("data");

            for(int i = 0; i < array.length(); i++){
                JSONObject ligneJSON = array.getJSONObject(i);

                ligne = ligneJSON.getString("ligne"+(i+1));
                Log.i("vv", String.valueOf(ligne));
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }



        char[][] temp = {
                {'.', '.', '#', '#', '#', '#', '.', '.', '.'},
                {'#', '#', '#', '.', '.', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '.', 'C', '.', '#'},
                {'#', '.', '#', '.', '.', '#', 'C', '.', '#'},
                {'#', '.', 'X', '.', 'X', '#', 'P', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };

        return temp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametable);
        char[][] tab = {};
        try {
            tab = getTableau();
            String[] temp = new String [6];

            initGame(tab);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}