package com.example.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.GameManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public void redirectToGametable(View v) {
        Intent i = new Intent(MainActivity.this, Gametable.class);
        startActivity(i);
    }

    public void initGame(char[][] plateau){
        char[] ligne;
        Case[][] plateauFinal = new Case[plateau.length][];
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
        //Log.i("test", String.valueOf(plateauFinal[2][1].getType()));
    }
    /*goLeft(View v){
        if("à compléter" > 0 && .type != CaseType.MUR){
            player.setX(player.getX() - 1);
        }
    }*/

    /*goRight(View v){
        if("à compléter" < .numColumns && .type != CaseType.MUR){
            player.setX(player.getX() + 1);
        }
    }*/

    /*goTop(View v){
        if("à compléter" > 0 && .type != CaseType.MUR){
            player.setY(player.getY() - 1);
        }
    }*/

    /*goBack(View v){
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

    public char[][] getTableau() throws IOException, MalformedURLException{
        URL url = new URL("http://127.0.0.1:5000/api/tableau/1");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer json = new StringBuffer();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
            Log.i("cc", line);
        }
        reader.close();

        Log.i("hello", String.valueOf(json));

        char[][] temp = {
                {'.', '.', '#', '#', '#', '#', '.', '.', '.'},
                {'#', '#', '#','.', '.','#', '#', '#','#'},
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        initGame(tab);
    }
}
