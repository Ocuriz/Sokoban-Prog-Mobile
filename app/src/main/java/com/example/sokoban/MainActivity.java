package com.example.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.GameManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;
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
        Log.i("test", String.valueOf(plateauFinal[2][1].getType()));
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        char[][] temp = {
                            {'.', '.', '#', '#', '#', '#', '.', '.', '.'},
                            {'#', '#', '#','.', '.','#', '#', '#','#'},
                            {'#', '.', '.', '.', '.', '.', 'C', '.', '#'},
                            {'#', '.', '#', '.', '.', '#', 'C', '.', '#'},
                            {'#', '.', 'X', '.', 'X', '#', 'P', '.', '#'},
                            {'#', '#', '#', '#', '#', '#', '#', '#', '#'}
                        };

        initGame(temp);
    }
}
