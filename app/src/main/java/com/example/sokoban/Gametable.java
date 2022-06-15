package com.example.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Gametable extends AppCompatActivity {

    public void initGame(char[][] plateau){
        Case[] listeElement = new Case[54];
        char[] ligne;
        Case[][] plateauFinal = new Case[plateau.length][];
        GridView grid = findViewById(R.id.test);
        ArrayList<String> lst = new ArrayList<String>();
        ArrayAdapter<String> adapter;
        for(int i = 0; i < plateau.length; i++){
            ligne = plateau[i];
            Case[] finalLigne = new Case[ligne.length];
            for(int j = 0; j < ligne.length; j++){
                Case element = new Case(i,j);
                element.setType(String.valueOf(ligne[j]));
                finalLigne[j] = element;

            }
            plateauFinal[i] = finalLigne;
        }
        int index = 0;
        for(int k=0; k < plateauFinal.length; k++){
            for(int l=0; l < plateauFinal[k].length; l++){
                Log.i("CC", String.valueOf(plateauFinal[k].length));
                listeElement[index] = plateauFinal[k][l];
                index++;
            }
        }

        grid.setNumColumns(9);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        AdaptationGrid adaptationGrid = new AdaptationGrid(getApplicationContext(), listeElement, displayMetrics.widthPixels);
        grid.setAdapter(adaptationGrid);

    }
    /*goLeft(){
        if("à compléter" > 0 && .type != CaseType.MUR){
            player.setX(player.getX() - 1);
        }
    }*/

    /*goRight(){
        if("à compléter" < .numColumns && .type != CaseType.MUR){
            player.setX(player.getX() + 1);
        }
    }*/

    /*goTop(){
        if("à compléter" > 0 && .type != CaseType.MUR){
            player.setY(player.getY() - 1);
        }
    }*/

    /*goBack(){
        if("à compléter" < .numColumns && .type != CaseType.MUR){
            player.setY(player.getY() - 1);
        }
    }*/

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
        setContentView(R.layout.activity_main);
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