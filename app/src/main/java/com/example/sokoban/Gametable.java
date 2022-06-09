package com.example.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Gametable extends AppCompatActivity {

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
        Log.i("test", String.valueOf(plateauFinal[2][1].getType()));
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DataBaseHelper db = new DataBaseHelper();
        char[][] temp = {
                {'.', '.', '#', '#', '#', '#', '.', '.', '.'},
                {'#', '#', '#', '.', '.', '#', '#', '#', '#'},
                {'#', '.', '.', '.', '.', '.', 'C', '.', '#'},
                {'#', '.', '#', '.', '.', '#', 'C', '.', '#'},
                {'#', '.', 'X', '.', 'X', '#', 'P', '.', '#'},
                {'#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
        initGame(temp);
    }
}