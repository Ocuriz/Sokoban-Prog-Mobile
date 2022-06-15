package com.example.sokoban;

import android.widget.Switch;

public class Case {
    private int x;
    private int y;
    private CaseType type;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setType(String s) {
        switch (s) {
            case "#":
                this.type = CaseType.MUR;
                break;
            case "X":
                this.type = CaseType.DESTINATION;
                break;
            case "J":
                this.type = CaseType.JOUEUR;
                break;
            case "C":
                this.type = CaseType.BOITE;
                break;
            default:
                this.type = CaseType.VIDE;
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CaseType getType() {
        return type;
    }
}
