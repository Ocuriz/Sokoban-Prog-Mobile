package com.example.sokoban.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Plateau_Manager";

    // Table name: Note.
    private static final String TABLE_TABLEAU = "Plateau";

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
        String scriptTable = "CREATE TABLE " + TABLE_TABLEAU + "("
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

    public Tableau getTableau(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TABLEAU, new String[] { COLUMN_TABLEAU_ID,
                        COLUMN_TABLEAU_LVL, COLUMN_NUMBER_ORDER }, COLUMN_TABLEAU_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Tableau tableau = new Tableau(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        return tableau;
    }

    public List<Ligne> getTableauLigne(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TABLEAU, new String[] { COLUMN_TABLEAU_ID,
                        COLUMN_TABLEAU_LVL, COLUMN_NUMBER_ORDER }, COLUMN_TABLEAU_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Tableau tableau = getTableau(id);

        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LIGNE + "WHERE" + COLUMN_LIGNE_ID + "=" + tableau.getTableauId();
        List<Ligne> ligneList = new ArrayList<Ligne>();
        cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ligne ligne = new Ligne();
                ligne.setTableauId(Integer.parseInt(cursor.getString(1)));
                ligne.setContenuLigne(cursor.getString(2));
                ligne.setNumeroLigne(Integer.parseInt(cursor.getString(3)));
                ligneList.add(ligne);
            } while (cursor.moveToNext());
        }

        return ligneList;
    }
}
