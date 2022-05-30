package com.example.sokoban;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Plateau_Manager";

    // Table name: Note.
    private static final String TABLE_PLATEAU = "Plateau";

    private static final String COLUMN_TABLEAU_ID ="Tableau_Id";
    private static final String COLUMN_TABLEAU_LVL ="Tableau_level";
    private static final String COLUMN_NUMBER_ORDER = "Tableau_Ordre";

    private static final String TABLE_LIGNE = "Ligne";
    private static final String COLUMN_LIGNE_ID = "Ligne_ID";
    private static final String COLUMN_FK_TABLEAU_ID = "ligne_Tableau_Id";
    private static final String COLUMN_CONTENU_LIGNE = "Ligne_Contenu";
    private static final String COLUMN_NUMERO_LIGNE = "Ligne_Numero";

    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String scriptTable = "CREATE TABLE " + TABLE_PLATEAU + "("
                + COLUMN_TABLEAU_ID + " INTEGER PRIMARY KEY," + COLUMN_TABLEAU_LVL + " TEXT,"
                + COLUMN_NUMBER_ORDER + "INTERGER" + ")";

        db.execSQL(scriptTable);

        String scriptRow = "CREATE TABLE " + TABLE_LIGNE + "("
                + COLUMN_LIGNE_ID + " INTEGER PRIMARY KEY," + COLUMN_FK_TABLEAU_ID + "INTEGER,"
                + COLUMN_CONTENU_LIGNE + "INTERGER," + COLUMN_NUMERO_LIGNE + "INTERGER " + ")";

        db.execSQL(scriptRow);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*public char[][] getNote(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);
        SQLiteDatabase db = this.getReadableDatabase();

        return [];
    }*/
}
