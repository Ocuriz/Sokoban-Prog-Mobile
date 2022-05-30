package com.example.sokoban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    public void initGame(char[][] plateau){
        char[] line;
        for(int i = 0; i < plateau.length; i++){
            line = plateau[i];
            for(int j = 0; j < line.length; j++){
                Case element = new Case(i,j);
            }
        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}